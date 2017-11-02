package qiuchenly.JSAHVCSC.NET;

import qiuchenly.JSAHVCSC.Login.iLogin;
import qiuchenly.JSAHVCSC.Login.vCodeRet;

/**
 * Created by QiuChenly on 2017年10月31日 0031.
 */

public interface API {
    void getLoginImage(vCodeRet ret);

    void login(String user, String pass, String vCode, iLogin loginResult);
}
