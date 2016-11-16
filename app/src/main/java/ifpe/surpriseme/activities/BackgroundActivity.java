package ifpe.surpriseme.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import ifpe.surpriseme.adapters.ImageAdapter;
import ifpe.surpriseme.flickrApi.FlickrManager;
import ifpe.surpriseme.flickrApi.FlickrManager.GetThumbnailsThread;
import ifpe.surpriseme.models.ImageContener;

public class BackgroundActivity extends Activity
{

    public final String LAST_IMAGE = "lastImage";
    public UIHandler uihandler;
    public ImageAdapter imgAdapter;
    private ArrayList<ImageContener> imageList;

    // UI
    private Button downloadPhotos;
    private ImageView currentImage;
    private String currentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uihandler = new UIHandler();
    }

    public void changeCurrentBackground(ImageView currentImage, String currentTag) {
        this.currentImage = currentImage;
        this.currentTag = currentTag;
        imageList = (ArrayList<ImageContener>) getLastNonConfigurationInstance();

        if (imageList != null) {
            imgAdapter = new ImageAdapter(getApplicationContext(), imageList);
            ArrayList<ImageContener> ic = imgAdapter.getImageContener();
            int lastImage = ic.size() - 1;

            imgAdapter.notifyDataSetChanged();
            Bitmap photo = ic.get(lastImage).getPhoto();

            if (photo == null)
                new GetLargePhotoThread(ic.get(lastImage), uihandler).start();
            else
                currentImage.setImageBitmap(ic.get(lastImage).photo);
        }
    }

    /**
     * Saving information about images
     */
    @Override
    public Object onRetainNonConfigurationInstance() {
        if (imgAdapter != null)
            return this.imgAdapter.getImageContener();
        else
            return null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    /**
     * @author michalu
     *         <p/>
     *         Downloading a larger photo using Thread
     */
    public class GetLargePhotoThread extends Thread {
        ImageContener ic;
        UIHandler uih;

        public GetLargePhotoThread(ImageContener ic, UIHandler uih) {
            this.ic = ic;
            this.uih = uih;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            if (ic.getPhoto() == null) {
                ic.setPhoto(FlickrManager.getImage(ic));
            }
            Bitmap bmp = ic.getPhoto();
            if (ic.getPhoto() != null) {
                Message msg = Message.obtain(uih, UIHandler.ID_SHOW_IMAGE);
                msg.obj = bmp;
                uih.sendMessage(msg);
            }
        }
    }

    /**
     * Runnable to get metadata from Flickr API
     */
    Runnable getMetadata = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            String tag = currentTag.trim();
            if (tag != null && tag.length() >= 3)
                FlickrManager.searchImagesByTag(uihandler, getApplicationContext(), tag);
        }
    };

    /**
     * @author michalu
     *         <p/>
     *         UI Handler to handle messages from threads
     */
    public class UIHandler extends Handler {
        public static final int ID_METADATA_DOWNLOADED = 0;
        public static final int ID_SHOW_IMAGE = 1;
        public static final int ID_UPDATE_ADAPTER = 2;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ID_METADATA_DOWNLOADED:
                    // Set of information required to download thumbnails is
                    // available now
                    if (msg.obj != null) {
                        imageList = (ArrayList<ImageContener>) msg.obj;
                        imgAdapter = new ImageAdapter(getApplicationContext(), imageList);

                        for (int i = 0; i < imgAdapter.getCount(); i++) {
                            new GetThumbnailsThread(uihandler, imgAdapter.getImageContener().get(i)).start();
                        }
                    }
                    break;
                case ID_SHOW_IMAGE:
                    // Display large image
                    if (msg.obj != null) {
                        currentImage.setImageBitmap((Bitmap) msg.obj);
                        currentImage.setVisibility(View.VISIBLE);
                    }
                    break;
                case ID_UPDATE_ADAPTER:
                    // Update adapter with thumnails
                    imgAdapter.notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    }

    OnItemClickListener onThumbClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
            // Get large image of selected thumnail
            new GetLargePhotoThread(imageList.get(position), uihandler).start();
        }
    };

    /**
     * to get metadata from Flickr API
     */
    OnClickListener onSearchButtonListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            imgAdapter.imageContener = new ArrayList<ImageContener>();
            currentImage.setVisibility(View.INVISIBLE);
            new Thread(getMetadata).start();
        }
    };

}