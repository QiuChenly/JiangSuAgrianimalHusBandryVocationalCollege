package qiuchenly.JSAHVCSC;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import qiuchenly.JSAHVCSC.Base.BaseApp;
import qiuchenly.JSAHVCSC.Base.BaseUtils;
import qiuchenly.JSAHVCSC.Base.iSetting.Sets;
import qiuchenly.JSAHVCSC.UIViews.Login;

public class Splash extends BaseApp implements Animation.AnimationListener {
    ImageView mJSAHVC;

    @Override
    public Sets getDefaultSet(Sets sets) {
        sets.ViewID=R.layout.view_splash;
        sets.allowStatusBarTranslate=true;
//        sets.doubleClickToExit=false;
        sets.hideTitleBar=true;
        sets.NullBack=true;
        return sets;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void resolveFinish() {
        Bitmap bit = BitmapFactory.decodeResource(getResources(),R.mipmap.jsahvc);
        mJSAHVC.setImageBitmap(BaseUtils.makeRound(bit,52));
        AnimationSet sets=new AnimationSet(true);
        TranslateAnimation translate = new TranslateAnimation(0,0,-500,0);
        sets.addAnimation(translate);
        RotateAnimation rotate = new RotateAnimation(0,359+359+359+359, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        sets.addAnimation(rotate);
        sets.setDuration(3000);
        sets.setFillAfter(true);
        sets.setAnimationListener(this);
        mJSAHVC.setAnimation(sets);//设置总动画
    }

    @Override
    public void findID() {
        mJSAHVC=find(R.id.mJSAHVCImg);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        start(Login.class);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
