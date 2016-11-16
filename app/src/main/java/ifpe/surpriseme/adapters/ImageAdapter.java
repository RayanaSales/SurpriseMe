package ifpe.surpriseme.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import ifpe.surpriseme.R;
import ifpe.surpriseme.models.ImageContener;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private int defaultItemBackground;
    public ArrayList<ImageContener> imageContener;

    public ArrayList<ImageContener> getImageContener() {
        return imageContener;
    }

    public void setImageContener(ArrayList<ImageContener> imageContener) {
        this.imageContener = imageContener;
    }

    public ImageAdapter(Context c, ArrayList<ImageContener> imageContener) {
        mContext = c;
        this.imageContener = imageContener;
        TypedArray styleAttrs = c.obtainStyledAttributes(R.styleable.PicGallery);
        styleAttrs.getResourceId(R.styleable.PicGallery_android_galleryItemBackground, 0);
        defaultItemBackground = styleAttrs.getResourceId(R.styleable.PicGallery_android_galleryItemBackground, 0);
        styleAttrs.recycle();
    }

    public int getCount() {
        return imageContener.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView i = new ImageView(mContext);
        if (imageContener.get(position).thumb != null) {
            i.setImageBitmap(imageContener.get(position).thumb);

            i.setBackgroundResource(defaultItemBackground);
        } else
            i.setImageDrawable(mContext.getResources().getDrawable(android.R.color.black)); //ATENÇÃO AO GET RESOUCES
        return i;
    }



}