package com.max.ijkcmake.player;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.max.ijkcmake.R;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;


public class ijkUseMainActivity extends Activity implements IMediaPlayer.OnPreparedListener, IMediaPlayer.OnVideoSizeChangedListener{

    private IjkMediaPlayer ijkMediaPlayer;
    private EditText filePath;
    private SurfaceView surfaceView;
    private SurfaceHolder holder;
    private static final String TAG = "ijkUseMainActivity";
    private static final int PERMISSION_CODE = 0x101;
    private boolean initial = false;
    private boolean audioReady = false;
    private boolean videoReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ijk_use_main);

        filePath = findViewById(R.id.file_path);
        surfaceView = (SurfaceView) findViewById(R.id.video_show);
        holder = surfaceView.getHolder();

        ijkMediaPlayer = new IjkMediaPlayer();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_CODE);
            } else {
                initial = true;
            }
        }else {
            initial = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED){
            Log.w(TAG, "permission not granted, now exit. ");
            Toast.makeText(ijkUseMainActivity.this, "read storage permission not granted, now exit. ",Toast.LENGTH_SHORT).show();
            finish();
        }else {
            initial = true;
        }
    }

    public void btnClick(View view){

        if (!initial){
            Log.d(TAG, "button clicked, but read storage permission not granted, nothing to do. ");
            Toast.makeText(ijkUseMainActivity.this, "read storage permission not granted, nothing to do", Toast.LENGTH_SHORT).show();
            return;
        }
        String file = filePath.getText().toString();
        if ("".equals(file) || file == null){
            Toast.makeText(this, "file path is null", Toast.LENGTH_SHORT).show();
            return;
        }

        try{
            ijkMediaPlayer.setDataSource(file);
            ijkMediaPlayer.setDisplay(surfaceView.getHolder());
            ijkMediaPlayer.prepareAsync();
            ijkMediaPlayer.setOnVideoSizeChangedListener(this);
            ijkMediaPlayer.setOnPreparedListener(this);
        }catch (Exception e){
            Log.d(TAG, "set data source failed. ");
            e.printStackTrace();
            Toast.makeText(ijkUseMainActivity.this, "set data source failed. ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPrepared(IMediaPlayer mp) {
        Log.d(TAG, "on Prepared, play. ");

        audioReady = true;
        if (audioReady && videoReady){
            mp.start();
        }
    }

    @Override
    public void onVideoSizeChanged(IMediaPlayer mp, int width, int height, int sar_num, int sar_den) {
        videoReady = true;
        if (audioReady && videoReady){
            mp.start();
        }
    }
}
