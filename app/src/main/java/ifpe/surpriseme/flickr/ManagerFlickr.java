package ifpe.surpriseme.flickr;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Rayana on 11/21/2016.
 */
public class ManagerFlickr extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {

        String photoUrl = "";
        String tag = params[0];

        try {
            URL url = new URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=a9ea4b229109908309f6fbd86c4f8fec&format=json&nojsoncallback=1&text=" +
                    tag + "&extras=url_o");
            URLConnection connection = url.openConnection();

            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            JSONObject json = new JSONObject(builder.toString());

            photoUrl = json.getJSONObject("photos").getJSONArray("photo").getJSONObject(0).getString("url_o");
            System.out.println("ifpe.surpriseme.flickr.ManagerFlickr.imageUrl: " + photoUrl);

        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return photoUrl;
    }

}
