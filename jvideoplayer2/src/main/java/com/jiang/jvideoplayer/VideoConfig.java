package com.jiang.jvideoplayer;

/**
 * Created by jiang on 18/1/8.
 */

public class VideoConfig {
    //默认显示比例
    public static final int SCREEN_DEFAULT = 0;
    //4：3显示
    public static final int SCREEN_4_3 = 1;
    //16：9显示
    public static final int SCREEN_16_9 = 2;
    //全屏显示
    public static final int SCREEN_FULL = 3;
    //全屏拉伸显示
    public static final int SCREEN_FIT_XY = 4;

    public static final int RENDER_SURFACE = 0;

    public static final int RENDER_TEXTURE = 1;

    public static final int RENDER_GLSURFACE = 2;

    private static int sScreenType = SCREEN_DEFAULT;

    private static int sRenderType = RENDER_SURFACE;

    public static int getRenderType() {
        return sRenderType;
    }

    public static int getScreenType() {
        return sScreenType;
    }

    public static void setRenderType(int renderType){
        sRenderType=renderType;
    }

    public static void setScreenType(int screenType){
        sScreenType=screenType;
    }


}
