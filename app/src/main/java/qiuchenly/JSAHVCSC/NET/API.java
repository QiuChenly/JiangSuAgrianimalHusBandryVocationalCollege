package qiuchenly.JSAHVCSC.NET;

import qiuchenly.JSAHVCSC.Base.BaseResult;
import qiuchenly.JSAHVCSC.Login.iLogin;

/**
 * Created by QiuChenly on 2017年10月31日 0031.
 */

public interface API {
    void login(String user, String pass, String vCode, iLogin loginResult);
}
