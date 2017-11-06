package qiuchenly.JSAHVCSC.UIViews;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import qiuchenly.JSAHVCSC.Base.BaseApp;
import qiuchenly.JSAHVCSC.Base.BaseUtils;
import qiuchenly.JSAHVCSC.Base.iSetting.Sets;
import qiuchenly.JSAHVCSC.Login.vCodeRet;
import qiuchenly.JSAHVCSC.Presenter.LoginPresenter;
import qiuchenly.JSAHVCSC.R;

public class Splash extends BaseApp implements Animator.AnimatorListener, Login_iView {
  ImageView mJSAHVC;

  LinearLayout mLoginBox;

  int BOX_HEIGHT;

  LoginPresenter presenter;

  Button mLoginBtn;

  ImageView mImageView;

  /**
   * 默认配置设定
   *
   * @param sets 默认传入的默认配置
   * @return 将传入的默认配置修改后返回即可
   */
  @Override
  public Sets getDefaultSet(Sets sets) {
    sets.ViewID = R.layout.view_splash;//设定View布局
    sets.allowStatusBarTranslate = true;//设定状态栏透明
//        sets.doubleClickToExit=false;
    sets.hideTitleBar = true;//隐藏ActionBar
    sets.NullBack = false;//拦截返回键事件
    return sets;
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.mLoginBtn:
        sizeAnimatorHide.start();
        animator_Rotate = ObjectAnimator.ofFloat
                (mJSAHVC, "rotation", 0f, 360f * 500);
        animator_Rotate.setDuration(1000 * 30);
        animator_Rotate.start();
        presenter.login("", "", "", this);
        break;
      case R.id.mVCodeImage:
        getImages();
      default:
        break;
    }

  }

  ObjectAnimator animator_Rotate;
  int DEVICES_HEIGHT;

  @Override
  public void resolveFinish() {
    Bitmap bit = BitmapFactory.decodeResource(getResources(), R.mipmap.jsahvc);
    mJSAHVC.setImageBitmap(BaseUtils.makeRound(bit, 52));

    DEVICES_HEIGHT = getWindowManager().getDefaultDisplay().getHeight() / 2;

    ObjectAnimator animator_Translate = ObjectAnimator.ofFloat
            (mJSAHVC, "TranslationY", -1 * DEVICES_HEIGHT, 0);
    animator_Rotate = ObjectAnimator.ofFloat
            (mJSAHVC, "rotation", 0f, 360f * 5);

    AnimatorSet set = new AnimatorSet();
    set.playTogether(animator_Translate, animator_Rotate);
    set.setInterpolator(new DecelerateInterpolator());
    set.setDuration(1000);
    set.addListener(this);
    set.start();
    presenter = new LoginPresenter(this);
  }

  TextView mInfos;

  @Override
  public void findID() {
    mJSAHVC = find(R.id.mJSAHVCImg);
    mLoginBox = find(R.id.mLoginBox);
    mLoginBox.setVisibility(View.INVISIBLE);
    mLoginBtn = find(R.id.mLoginBtn, true);
    mInfos = find(R.id.mInfos);
    mImageView = find(R.id.mVCodeImage, true);
  }


  @Override
  public void onAnimationStart(Animator animation, boolean isReverse) {

  }

  @Override
  public void onAnimationStart(Animator animator) {
  }

  ValueAnimator sizeAnimatorHide;

  @Override
  public void onAnimationEnd(Animator animator) {

    BOX_HEIGHT = mLoginBox.getHeight();

    sizeAnimatorHide = ValueAnimator.ofInt(BOX_HEIGHT, 0);
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
        if (mLoginBox.getVisibility() != View.VISIBLE) {
          mLoginBox.setVisibility(View.VISIBLE);
        }
      }

      @Override
      public void onAnimationCancel(Animator animator) {

      }

      @Override
      public void onAnimationRepeat(Animator animator) {

      }
    });
    sizeAnimatorHide.setDuration(600);


    //TODO:这里加入自动登陆
    sizeAnimatorShow = ValueAnimator.ofInt(0, BOX_HEIGHT);
    sizeAnimatorShow.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator valueAnimator) {
        mLoginBox.getLayoutParams().height = (int) valueAnimator.getAnimatedValue();
        mLoginBox.requestLayout();
      }
    });
    sizeAnimatorShow.setDuration(800);
    AnimatorSet set = new AnimatorSet();
    set.playSequentially(sizeAnimatorHide, sizeAnimatorShow);
    set.start();

    getImages();

  }

  void getImages() {
    if (!BaseUtils.checkInternetState(this)) {
      Msg("啊呀!哇网络掉线了...");
      return;
    }
    //开始加载图片
    presenter.getvCodeImage(new vCodeRet() {
      @Override
      public void onSucess(Bitmap bit) {
        BaseUtils.setImage(mImageView, BaseUtils.roundRectF(bit, 5));
      }

      @Override
      public void onFailed(String errReason) {
        Msg(errReason, false);
      }
    });
  }

  ValueAnimator sizeAnimatorShow;

  @Override
  public void onAnimationCancel(Animator animator) {
  }

  @Override
  public void onAnimationRepeat(Animator animator) {

  }

  @Override
  public void onSuccess() {
    TranslateAnimation translateAnimation =
            new TranslateAnimation(
                    0f, 0f,
                    0,
                    getWindowManager().getDefaultDisplay().getHeight() / 2 + mJSAHVC.getHeight());
    translateAnimation.setDuration(2000);
    translateAnimation.setAnimationListener(new Animation.AnimationListener() {
      @Override
      public void onAnimationStart(Animation animation) {

      }

      @Override
      public void onAnimationEnd(Animation animation) {
        mJSAHVC.setVisibility(View.INVISIBLE);
        mInfos.setVisibility(View.INVISIBLE);
        animator_Rotate.cancel();
        start(MainContent.class);
      }

      @Override
      public void onAnimationRepeat(Animation animation) {

      }
    });
    mJSAHVC.setAnimation(translateAnimation);
    mInfos.setAnimation(translateAnimation);
    translateAnimation.start();
  }

  @Override
  public void onFailed(String errReason) {
    AnimatorSet set = new AnimatorSet();
    set.playSequentially(sizeAnimatorShow);
    set.start();
    animator_Rotate.cancel();
  }
}
