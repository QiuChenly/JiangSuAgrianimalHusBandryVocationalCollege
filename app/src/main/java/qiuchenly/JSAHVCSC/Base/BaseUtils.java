package qiuchenly.JSAHVCSC.Base;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.ConnectivityManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

/**
 * Author        : QiuChenLy
 * Times         : 2017 年 10 月 29 日 17:01
 * Usage         :基本工具类，所有无关UI逻辑的方法代码写在这里
 */

public class BaseUtils {
    public static Bitmap makeRound(Bitmap bit, int size) {
        int w = bit.getWidth();
        int h = bit.getHeight();
        Bitmap bits = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bits);
        int color = 0xff424242;
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, w, h);
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, size, size, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bit, rect, rect, paint);
        return bits;
    }

    /**
     * 渐显渐隐加载图片到imageView Author:QiuChenly 17.11.2 下午
     *
     * @param view
     * @param bit
     */
    public static void setImage(final ImageView view, Bitmap bit) {
        view.setImageBitmap(bit);

        AnimatorSet set = new AnimatorSet();
        ValueAnimator alpha_HIDE = ValueAnimator.ofFloat(1f, 0f);
        alpha_HIDE.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float v = valueAnimator.getAnimatedFraction();
                view.setAlpha(v);
            }
        });
        ValueAnimator alpha_SHOW = ValueAnimator.ofFloat(0f, 1f);
        alpha_SHOW.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float v = valueAnimator.getAnimatedFraction();
                view.setAlpha(v);
            }
        });
        set.playTogether(alpha_HIDE, alpha_SHOW);
        set.setInterpolator(new DecelerateInterpolator());
        set.start();
    }

    public static Bitmap roundRectF(Bitmap bit, float RoundSize) {
        Bitmap out = Bitmap.createBitmap(bit.getWidth(), bit.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(out);
        final int color = 0xff424242;
        //初始化画笔
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        //设置矩形
        Rect rect = new Rect(0, 0, out.getWidth(), out.getHeight());
        RectF rectF = new RectF(rect);

        //设置角度
        final float roundPx = RoundSize;

        canvas.drawARGB(0, 0, 0, 0);

        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        canvas.drawBitmap(bit, rect, rect, paint);

        return out;

    }

    public static boolean checkInternetState(Context cons) {
        ConnectivityManager con = (ConnectivityManager) cons.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean state = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
        boolean state1 = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();
        if (state | state1)
            return true;
        else return false;
    }
}
