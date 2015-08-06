package com.example.coupang.secondexample;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.ImageView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by coupang on 2015. 8. 6..
 */
public class DownloadService extends IntentService{
    private final String TAG = "ServiceLog";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DownloadService(){
        super("download");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "Service onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "Service onDestroy()");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Uri uri = intent.getData();

        try {
//            Thread.sleep(5000);
            // 이미지 다운로드
            downloadBitmapFromNetwork("http://imgs.coupangcdn.com/image/coupang/common/bg_header_sprite_150702.png");

            // BroadCast
            Intent broadIntent = new Intent(Constants.BROADCAST_ACTION).putExtra(Constants.EXTENDED_DATA_STATUS, "Image Download Complete");
            LocalBroadcastManager.getInstance(this).sendBroadcast(broadIntent);

            Log.i(TAG, "Processing...");
        } catch (Exception e) {}
    }

    // 이미지 다운로드
    private void downloadBitmapFromNetwork(String imageUrl) throws IOException{
        Log.i(TAG, "downloadBitmapFromNetwork()");

        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.connect();

        InputStream inputStream = connection.getInputStream();
//        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//        Log.i(TAG,bitmap.toString());

        FileOutputStream fos = null;
        String fileName = imageUrl.substring(imageUrl.lastIndexOf('/') + 1);
        String filePath = Environment.getExternalStorageDirectory()+"/"+fileName;
        fos = new FileOutputStream(filePath);

        byte[] buffer=new byte[1024];
        int count;
        while((count=inputStream.read(buffer))>0){
            fos.write(buffer,0,count);
        }
        fos.flush();

        Log.i(TAG, "Complete File Save : " + filePath);
//        return bitmap;
        return;
    }
}
