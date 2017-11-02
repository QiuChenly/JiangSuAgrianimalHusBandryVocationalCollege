package qiuchenly.JSAHVCSC.Login;

import android.graphics.Bitmap;

/**
 * Created by QiuChenly on 2017/11/2.
 */

public interface vCodeRet {
    void onSucess(Bitmap bit);

    void onFailed(String errReason);

}
