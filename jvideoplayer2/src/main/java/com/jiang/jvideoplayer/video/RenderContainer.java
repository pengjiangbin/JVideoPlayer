package com.jiang.jvideoplayer.video;

import android.view.SurfaceHolder;
import android.view.ViewGroup;

import com.jiang.jvideoplayer.listener.IRenderView;

/**
 * author: jiang
 * email:  694943422@qq.com
 * date:   2018/1/8
 * des:
 * version:1.0
 */

public class RenderContainer {

    private IRenderView mRenderView;

    public void addRenderView(ViewGroup container,IRenderView renderView) {
        if(container.getChildCount()>0){
            container.removeAllViews();
        }
        if(renderView instanceof SurfaceRenderView){
            addSurfaceRenderView(container,(SurfaceRenderView)renderView);
        }else if(renderView instanceof TextureRenderView){
            addTextureRenderView(container,(TextureRenderView)renderView);
        }

        this.mRenderView = renderView;
    }

    public void addSurfaceRenderView(ViewGroup container,SurfaceRenderView surfaceRenderView){
    }

    public void addTextureRenderView(ViewGroup container,TextureRenderView textureRenderView){

    }

    public IRenderView getRenderView(){
        return mRenderView;
    }

    public ViewGroup.LayoutParams getLayoutParams(){
        if(mRenderView!=null){
            return mRenderView.getRenderLayoutParams();
        }
        return null;
    }

    private  class SurfaceCallback implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }

}
