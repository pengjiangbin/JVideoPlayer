package com.jiang.jvideoplayer.video;

import android.content.Context;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.jiang.jvideoplayer.VideoConfig;

/**
 * author: jiang
 * email:  694943422@qq.com
 * date:   2018/1/8
 * des:
 * version:1.0
 */

public class RenderContainer {

    private View mRenderView;


    public void addSurfaceRenderView(Context context, ViewGroup container, int rotate, SurfaceHolder.Callback callback) {
        SurfaceRenderView surfaceRenderView = new SurfaceRenderView(context);
        surfaceRenderView.getHolder().addCallback(callback);
        surfaceRenderView.setRotation(rotate);
        mRenderView = surfaceRenderView;
        addToParent(container, surfaceRenderView);
    }

    public void addTextureRenderView(Context context, ViewGroup container, int rotate, TextureView.SurfaceTextureListener listener) {
        TextureRenderView textureRenderView=new TextureRenderView(context);
        textureRenderView.setSurfaceTextureListener(listener);
        textureRenderView.setRotation(rotate);
        mRenderView=textureRenderView;
        addToParent(container,textureRenderView);
    }

    public void addToParent(ViewGroup container, View renderView) {
        int param = getRenderParam();
        if(container instanceof RelativeLayout){
            RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(param,param);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            container.addView(renderView,params);
        }else if(container instanceof FrameLayout){
            FrameLayout.LayoutParams params=new FrameLayout.LayoutParams(param,param);
            params.gravity= Gravity.CENTER;
            container.addView(renderView,params);
        }
    }

    public View getRenderView() {
        return mRenderView;
    }

    public int getRenderParam() {
        boolean typeChanged = (VideoConfig.getScreenType() != VideoConfig.SCREEN_DEFAULT);
        return (typeChanged) ? ViewGroup.LayoutParams.WRAP_CONTENT : ViewGroup.LayoutParams.MATCH_PARENT;
    }


}
