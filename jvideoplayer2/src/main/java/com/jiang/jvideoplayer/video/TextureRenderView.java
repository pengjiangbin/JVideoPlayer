package com.jiang.jvideoplayer.video;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import com.jiang.jvideoplayer.listener.IRenderView;

/**
 * Created by jiang on 18/1/8.
 */

public class TextureRenderView extends TextureView implements IRenderView{
    public TextureRenderView(Context context) {
        super(context);
    }

    public TextureRenderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextureRenderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public View getView() {
        return null;
    }

    @Override
    public ViewGroup.LayoutParams getRenderLayoutParams() {
        return null;
    }


}
