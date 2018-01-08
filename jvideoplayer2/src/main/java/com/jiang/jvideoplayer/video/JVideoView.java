package com.jiang.jvideoplayer.video;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jiang.jvideoplayer.VideoPlayerListener;
import com.jiang.jvideoplayer.listener.IRenderView;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by jiang on 18/1/8.
 */

public class JVideoView extends FrameLayout{
    private Context mContext;
    private SurfaceView mSurfaceView;
    private String mVideoUrl;
    private IMediaPlayer mIMediaPlayer;
    private VideoPlayerListener mListener;
    private RenderContainer mRenderContainer;
    public JVideoView(@NonNull Context context) {
        this(context,null);
    }

    public JVideoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public JVideoView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context){
        this.mContext=context;

    }

    public void setVideoUrl(String videoUrl){
        mVideoUrl = videoUrl;
        setRenderView(mContext);
        loadVideo();
    }

    public void setRenderView(Context context){
        mSurfaceView=new SurfaceRenderView(context);
        mSurfaceView.getHolder().addCallback(new SurfaceCallback());
        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mSurfaceView.setLayoutParams(params);
        this.addView(mSurfaceView);
    }

    public void addRenderView(IRenderView renderView){
        mRenderContainer=new RenderContainer();
    }



    public void loadVideo(){
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


    private  class SurfaceCallback implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            loadVideo();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }
}
