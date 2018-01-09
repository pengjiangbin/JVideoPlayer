package com.jiang.jvideoplayer.video;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.widget.FrameLayout;

import com.jiang.jvideoplayer.VideoConfig;
import com.jiang.jvideoplayer.VideoPlayerListener;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by jiang on 18/1/8.
 */

public class JVideoView extends FrameLayout implements SurfaceHolder.Callback,TextureView.SurfaceTextureListener {

    private Surface mSurface;

    private String mVideoUrl;

    private IMediaPlayer mIMediaPlayer;

    private VideoPlayerListener mListener;

    private RenderContainer mRenderContainer;

    private int rotate;

    public JVideoView(@NonNull Context context) {
        this(context, null);
    }

    public JVideoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JVideoView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {

    }

    public void setVideoUrl(String videoUrl) {
        mVideoUrl = videoUrl;
        addRenderView();
        loadVideo();
    }



    public void addRenderView() {
        if (getChildCount() > 0) {
            removeAllViews();
        }
        mRenderContainer = new RenderContainer();
        if (VideoConfig.getRenderType() == VideoConfig.RENDER_SURFACE) {
            mRenderContainer.addSurfaceRenderView(getContext(),this,rotate,this);
        } else if (VideoConfig.getRenderType() == VideoConfig.RENDER_TEXTURE) {
            mRenderContainer.addTextureRenderView(getContext(),this,rotate,this);
        }
    }


    public void loadVideo() {
        createPlayer();
        try {
            mIMediaPlayer.setDataSource(mVideoUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mIMediaPlayer.setSurface(mSurface);
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

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mSurface=holder.getSurface();
        loadVideo();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }


    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        mSurface=new Surface(surface);
        loadVideo();
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }
}
