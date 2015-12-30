package com.huangzj.seekbar;

import android.content.Context;

/**
 * 工具
 * <p/>
 * Created by huangzj on 2015/12/28.
 */
public class Utils {

    public static int spTopx(Context context, float sp) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * fontScale + 0.5f);
    }
}
