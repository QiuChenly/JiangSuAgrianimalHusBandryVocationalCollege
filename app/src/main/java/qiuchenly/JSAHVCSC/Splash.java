package qiuchenly.JSAHVCSC;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import qiuchenly.JSAHVCSC.Base.BaseApp;
import qiuchenly.JSAHVCSC.Base.BaseUtils;
import qiuchenly.JSAHVCSC.Base.iSetting.Sets;
import qiuchenly.JSAHVCSC.UIViews.Login;

public class Splash extends BaseApp implements Animator.AnimatorListener {
    ImageView mJSAHVC;

    LinearLayout mLoginBox;

    @Override
    public Sets getDefaultSet(Sets sets) {
        sets.ViewID = R.layout.view_splash;
        sets.allowStatusBarTranslate = true;
//        sets.doubleClickToExit=false;
        sets.hideTitleBar = true;
        sets.NullBack = true;
        return sets;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void resolveFinish() {
        Bitmap bit = BitmapFactory.decodeResource(getResources(), R.mipmap.jsahvc);
        mJSAHVC.setImageBitmap(BaseUtils.makeRound(bit, 52));

        int DEVICES_HEIGHT = getWindowManager().getDefaultDisplay().getHeight() / 2;

        ObjectAnimator animator_Translate = ObjectAnimator.ofFloat
                (mJSAHVC, "TranslationY", -1 * DEVICES_HEIGHT, 0);
        ObjectAnimator animator_Rotate = ObjectAnimator.ofFloat
                (mJSAHVC, "rotation", 0f, 360f * 5);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animator_Translate, animator_Rotate);
        set.setInterpolator(new DecelerateInterpolator());
        set.setDuration(1000);
        set.addListener(this);
        set.start();
    }


    @Override
    public void findID() {
        mJSAHVC = find(R.id.mJSAHVCImg);
        mLoginBox = find(R.id.mLoginBox);
        mLoginBox.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onAnimationStart(Animator animator) {
    }

    @Override
    public void onAnimationEnd(Animator animator) {
        int BOX_HEIGHT;
        BOX_HEIGHT = mLoginBox.getHeight();

        ValueAnimator sizeAnimatorHide = ValueAnimator.ofInt(BOX_HEIGHT,0);
        sizeAnimatorHide.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mLoginBox.getLayoutParams().height = (int) valueAnimator.getAnimatedValue();
                mLoginBox.requestLayout();
            }
        });
        sizeAnimatorHide.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mLoginBox.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        sizeAnimatorHide.setDuration(600);


        ValueAnimator sizeAnimatorShow = ValueAnimator.ofInt(0, BOX_HEIGHT);
        sizeAnimatorShow.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mLoginBox.getLayoutParams().height = (int) valueAnimator.getAnimatedValue();
                mLoginBox.requestLayout();
            }
        });
        sizeAnimatorShow.setDuration(800);
        AnimatorSet set=new AnimatorSet();
        set.playSequentially(sizeAnimatorHide,sizeAnimatorShow);
        set.start();

    }

    @Override
    public void onAnimationCancel(Animator animator) {
    }

    @Override
    public void onAnimationRepeat(Animator animator) {
    }
}
