package qiuchenly.JSAHVCSC.Presenter;

import qiuchenly.JSAHVCSC.Login.iLogin;
import qiuchenly.JSAHVCSC.NET.API;
import qiuchenly.JSAHVCSC.NET.APIImp;
import qiuchenly.JSAHVCSC.UIViews.Login_iView;

/**
 * Created by QiuChenly on 2017年10月31日 0031.
 */

public class LoginPresenter {
    API api;
    Login_iView iView;

    public LoginPresenter(Login_iView iView) {
        api = new APIImp();
        this.iView = iView;
    }

    public void login(String user,String pass,String vCode){
        api.login(user, pass, vCode, new iLogin() {
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
}
