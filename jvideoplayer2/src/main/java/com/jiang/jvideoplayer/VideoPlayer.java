package com.jiang.jvideoplayer;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by jiang on 18/1/5.
 */

public class VideoPlayer extends FrameLayout {
    private Context mContext;
    private String mVideoUrl;
    private IMediaPlayer mIMediaPlayer;
    private VideoPlayerListener mListener;
    private SurfaceView mSurfaceView;

    public VideoPlayer(@NonNull Context context) {
        this(context, null);
    }

    public VideoPlayer(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoPlayer(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initVideoView(context);
    }

    public void initVideoView(Context context) {
        mContext = context;
    }

    public void setVideo(String url) {
        mVideoUrl = url;
        mSurfaceView = createSurfaceView();
        loadVideo();


    }

    public SurfaceView createSurfaceView() {
        SurfaceView surfaceView = new SurfaceView(mContext);
        surfaceView.getHolder().addCallback(new SurfaceCallback());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        surfaceView.setLayoutParams(params);
        this.addView(surfaceView);
        return surfaceView;
    }

    public void loadVideo() {
        createPlayer();
        try {
            mIMediaPlayer.setDataSource(mVideoUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mIMediaPlayer.setDisplay(mSurfaceView.getHolder());
        mIMediaPlayer.prepareAsync();
    }

    public void createPlayer() {
        if (mIMediaPlayer != null) {
            mIMediaPlayer.stop();
            mIMediaPlayer.setDisplay(null);
            mIMediaPlayer.release();
        }
        IjkMediaPlayer ijkMediaPlayer = new IjkMediaPlayer();
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "mediacodec", 1);
        mIMediaPlayer = ijkMediaPlayer;
        if (mListener != null) {
            mIMediaPlayer.setOnPreparedListener(mListener);
            mIMediaPlayer.setOnInfoListener(mListener);
            mIMediaPlayer.setOnSeekCompleteListener(mListener);
            mIMediaPlayer.setOnBufferingUpdateListener(mListener);
            mIMediaPlayer.setOnErrorListener(mListener);
        }
    }

    public void setListener(VideoPlayerListener listener) {
        mListener = listener;
        if (mIMediaPlayer != null) {
            mIMediaPlayer.setOnPreparedListener(listener);
        }
    }

    public void start() {
        if (mIMediaPlayer != null) {
            mIMediaPlayer.start();
        }
    }

    public void release() {
        if (mIMediaPlayer != null) {
            mIMediaPlayer.reset();
            mIMediaPlayer.release();
            mIMediaPlayer = null;
        }
    }

    public void pause() {
        if (mIMediaPlayer != null) {
            mIMediaPlayer.pause();
        }
    }

    public void stop() {
        if (mIMediaPlayer != null) {
            mIMediaPlayer.stop();
        }
    }


    public void reset() {
        if (mIMediaPlayer != null) {
            mIMediaPlayer.reset();
        }
    }


    public long getDuration() {
        if (mIMediaPlayer != null) {
            return mIMediaPlayer.getDuration();
        } else {
            return 0;
        }
    }


    public long getCurrentPosition() {
        if (mIMediaPlayer != null) {
            return mIMediaPlayer.getCurrentPosition();
        } else {
            return 0;
        }
    }


    public void seekTo(long l) {
        if (mIMediaPlayer != null) {
            mIMediaPlayer.seekTo(l);
        }
    }


    private class SurfaceCallback implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            loadVideo();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }

}
