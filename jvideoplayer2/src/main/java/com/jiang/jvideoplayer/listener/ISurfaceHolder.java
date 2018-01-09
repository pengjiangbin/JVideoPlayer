package com.jiang.jvideoplayer.listener;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.SurfaceHolder;

/**
 * Created by jiang on 18/1/9.
 */

public interface ISurfaceHolder {

    IRenderView getRenderView();

    SurfaceHolder getSurfaceHolder();

    SurfaceTexture getSurfaceTexture();

    Surface openSurface();

}
