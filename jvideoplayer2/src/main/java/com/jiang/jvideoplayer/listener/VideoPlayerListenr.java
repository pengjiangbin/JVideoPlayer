package com.jiang.jvideoplayer.listener;

/**
 * Created by jiang on 18/1/8.
 */

public interface VideoPlayerListenr {

    void onPrepared();

    void onCompletion();

    void onBufferingUpdate(int percent);

    void onSeekComplete();

    void onVideoSizeChnaged(int width,int height);

    void onError(int what,int extra);

    void onInfo(int what,int extra);

    void onPause();

    void onResume();

    void onFullScreen();


}
