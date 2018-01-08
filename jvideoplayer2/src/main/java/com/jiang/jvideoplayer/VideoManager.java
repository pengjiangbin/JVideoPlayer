package com.jiang.jvideoplayer;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by jiang on 18/1/8.
 * 视频管理者
 */

public class VideoManager {
    private IMediaPlayer mMediaPlayer;

    private VideoManager() {
    }

    public static VideoManager instance() {
        return SingleHolder.INSTANCE;
    }

    private static class SingleHolder {
        private static final VideoManager INSTANCE = new VideoManager();
    }

    public IMediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }

    public void start() {
        if (mMediaPlayer != null) {
            mMediaPlayer.start();
        }
    }

    public void pause() {
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
        }
    }

    public void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
        }
    }

    public void release() {
        if (mMediaPlayer != null) {
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public long getDuration() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getDuration();
        } else {
            return 0;
        }
    }

}
