package com.muwood.jvideoplayer;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jiang.jvideoplayer.VideoPlayerListener;
import com.jiang.jvideoplayer.video.JVideoView;

import java.io.File;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {
    private JVideoView ijkPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        ijkPlayer = (JVideoView) findViewById(R.id.ijk_player);
        ijkPlayer.setListener(new VideoPlayerListener() {
            @Override
            public void onBufferingUpdate(IMediaPlayer mp, int percent) {
            }

            @Override
            public void onCompletion(IMediaPlayer mp) {
                mp.seekTo(0);
                mp.start();
            }

            @Override
            public boolean onError(IMediaPlayer mp, int what, int extra) {
                return false;
            }

            @Override
            public boolean onInfo(IMediaPlayer mp, int what, int extra) {
                return false;
            }

            @Override
            public void onPrepared(IMediaPlayer mp) {
                mp.start();
            }

            @Override
            public void onSeekComplete(IMediaPlayer mp) {

            }

            @Override
            public void onVideoSizeChanged(IMediaPlayer mp, int width, int height, int sar_num, int sar_den) {
                //获取到视频的宽和高
            }
        });
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url= Environment.getExternalStorageDirectory()+ File.separator+"EpMedia/out.mp4";
                ijkPlayer.setVideoUrl("http://7xjmzj.com1.z0.glb.clouddn.com/20171026175005_JObCxCE2.mp4");
            }
        });
    }

}
