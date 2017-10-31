package qiuchenly.JSAHVCSC.NET;

import qiuchenly.JSAHVCSC.Login.iLogin;

/**
 * Created by QiuChenly on 2017年10月31日 0031.
 */

public class APIImp implements API {
    @Override
    public void login(String user, String pass, String vCode, iLogin loginResult) {
        loginResult.onSuccess();
    }
}
