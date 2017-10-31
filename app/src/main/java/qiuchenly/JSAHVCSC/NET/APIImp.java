package qiuchenly.JSAHVCSC.NET;


import android.os.Handler;
import android.os.Looper;

import qiuchenly.JSAHVCSC.Login.iLogin;

/**
 * Created by QiuChenly on 2017年10月31日 0031.
 */

/*
    网络请求暂时不写
 */
public class APIImp implements API {
    @Override
    public void login(String user, String pass, String vCode, final iLogin loginResult) {
        loginResult.onSuccess();
    }
}
