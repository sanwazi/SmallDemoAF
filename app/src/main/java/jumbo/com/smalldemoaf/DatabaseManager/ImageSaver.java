package jumbo.com.smalldemoaf.DatabaseManager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jumbo on 11/4/15.
 */
public class ImageSaver extends AsyncTask<Void, Integer, Void> {

    private String url;
    private String title;
    private PromotionDao dao;


    public ImageSaver(String url, Context c, String str) {
        this.url = url;
        this.title = str;
        dao = new PromotionDao(c);
    }

    /*--- we need this interface for keeping the reference to our Bitmap from the MainActivity.
     *  Otherwise, bmp would be null in our MainActivity*/
    public interface ImageLoaderListener {

        void onImageDownloaded(Bitmap bmp);

    }


    @Override
    protected Void doInBackground(Void... arg0) {

        recordBitmapFromURL(url);

        return null;
    }


    public void recordBitmapFromURL(String link) {
    /*--- this method downloads an Image from the given URL,
     *  then decodes and returns a Bitmap object
     ---*/
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);


            try {
                File file = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
                file.getParentFile().mkdirs();
                FileOutputStream out = new FileOutputStream(file);
                myBitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                out.flush();
                out.close();
                dao.updateImage(title,file.getAbsolutePath());

            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}