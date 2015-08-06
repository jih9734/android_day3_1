package com.example.coupang.secondexample;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.Image;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private final String TAG = "ServiceLog";

    DownloadReceiver receiver = new DownloadReceiver();
    IntentFilter intentFilter = new IntentFilter(Constants.BROADCAST_ACTION);

    private Button btn_dialog;
    private TextView textView;
    private Button btn_webview;
    private Button btn_download;
    private ImageView imageview_download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout parent = (LinearLayout) findViewById(R.id.parent);

        TextView tv = (TextView) getLayoutInflater().inflate(R.layout.child, null);

        parent.addView(tv);

        imageview_download = (ImageView)findViewById(R.id.image_download);

        btn_dialog = (Button) findViewById(R.id.btn_dialog);
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EditText edit = (EditText) findViewById(R.id.edit);
//                String txt = edit.getText().toString();
                Toast.makeText(v.getContext(), "Click", Toast.LENGTH_LONG).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {   // User clicked OK button 
                        //      
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {   // User cancelled the dialog       
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        btn_webview = (Button) findViewById(R.id.btn_webview);
        btn_webview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });

        // 다운로드
        btn_download = (Button) findViewById(R.id.btn_download);
        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick()");
                Intent intent = new Intent(MainActivity.this, DownloadService.class);
//                intent.setComponent(
//                        new ComponentName("com.example.coupang.secondexample",
//                                "com.example.coupang.secondexample.DownloadService"));
                startService(intent);

//                url('//imgs.coupangcdn.com/image/coupang/common/bg_header_sprite_150702.png')

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        // BoradCast 등록
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // BoradCast 해제
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    // BoradCast Receiver
    private class DownloadReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent){
            Log.i(TAG,"onReceive()");
            String value = intent.getStringExtra(Constants.EXTENDED_DATA_STATUS);

            Toast.makeText(MainActivity.this,value,Toast.LENGTH_LONG).show();

        }
    }

    // PPT 49쪽 예제

//    Button button1, button2;
//    TextView textView;
//    ImageView imageView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.test2);
//
//        button1 = (Button) findViewById(R.id.button1);
//        button2 = (Button) findViewById(R.id.button2);
//        textView = (TextView) findViewById(R.id.textview);
//        imageView = (ImageView) findViewById(R.id.imageview);
//
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imageView.setVisibility(View.VISIBLE);
//                textView.setVisibility(View.GONE);
//            }
//        });
//
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imageView.setVisibility(View.GONE);
//                textView.setVisibility(View.VISIBLE);
//            }
//        });
//    }

}
