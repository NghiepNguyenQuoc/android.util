package com.nghiepnguyen.androidutils.utils;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    protected Bitmap doInBackground(String... urls) {
        Bitmap imageResult = null;
        ImagesCache cache = ImagesCache.getInstance();
        imageResult = cache.getImageFromWarehouse(urls[0]);
        if (imageResult != null)
            return imageResult;
        else {
            String imageUrl = urls[0];
            imageResult = downloadImageFromPath(imageUrl);
            if(imageResult!=null)
                cache.addImageToWarehouse(imageUrl, imageResult);
        }
        return imageResult;
    }

    public Bitmap downloadImageFromPath(String path) {
        InputStream in = null;
        Bitmap bmp = null;
        int responseCode = -1;
        try {

            URL url = new URL(path);// "http://192.xx.xx.xx/mypath/img1.jpg
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.setChunkedStreamingMode(1024);
            con.connect();
            responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // download
                in = con.getInputStream();
                if (in != null)
                    //bmp = BitmapFactory.decodeStream(in);
                    bmp = decodeSampledBitmapFromResource(in);
                in.close();
            }

        } catch (Exception ex) {
            Log.e("Exception", ex.toString());
        }
        return bmp;
    }

    public Bitmap decodeSampledBitmapFromResource(InputStream is) {

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //BitmapFactory.decodeStream(is, null, options);
        // Calculate inSampleSize
        //options.inSampleSize = ImageUtils.calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        options.inSampleSize = 1;
        return BitmapFactory.decodeStream(is, null, options);
    }

}


