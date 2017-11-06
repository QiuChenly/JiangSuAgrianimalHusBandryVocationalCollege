package qiuchenly.JSAHVCSC.Presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;

import qiuchenly.JSAHVCSC.Login.iLogin;
import qiuchenly.JSAHVCSC.Login.vCodeRet;
import qiuchenly.JSAHVCSC.NET.API;
import qiuchenly.JSAHVCSC.NET.APIImp;
import qiuchenly.JSAHVCSC.UIViews.Login_iView;

/**
 * Created by QiuChenly on 2017年10月31日 0031.
 */

public class LoginPresenter {
  API api;
  Login_iView iView;
  Handler handler;

  public LoginPresenter(Login_iView iView) {
    api = new APIImp();
    this.iView = iView;
    handler = new Handler(Looper.getMainLooper());
  }

  public void login(String user, String pass, String vCode, Context context) {
    api.login(user, pass, vCode, context, new iLogin() {
      @Override
      public void onFailed(String errReason) {
        iView.onFailed(errReason);
      }

      @Override
      public void onSuccess() {
        iView.onSuccess();
      }
    });
  }

  public void getvCodeImage(final vCodeRet ret) {
    api.getLoginImage(new vCodeRet() {
      @Override
      public void onSucess(final Bitmap bit) {
        handler.post(new Runnable() {
          @Override
          public void run() {
            ret.onSucess(bit);
          }
        });
      }

      @Override
      public void onFailed(final String errReason) {
        handler.post(new Runnable() {
          @Override
          public void run() {
            ret.onFailed(errReason);
          }
        });
      }
    });
  }
}
