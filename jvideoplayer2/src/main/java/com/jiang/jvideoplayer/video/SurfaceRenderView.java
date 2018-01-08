package com.jiang.jvideoplayer.video;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.jiang.jvideoplayer.listener.IRenderView;

/**
 * Created by jiang on 18/1/8.
 */

public class SurfaceRenderView extends SurfaceView implements IRenderView {

    public SurfaceRenderView(Context context) {
        this(context, null);

    }

    public SurfaceRenderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SurfaceRenderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView() {
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public ViewGroup.LayoutParams getRenderLayoutParams() {
        return null;
    }


}
