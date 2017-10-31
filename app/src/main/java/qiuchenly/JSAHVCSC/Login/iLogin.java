package qiuchenly.JSAHVCSC.Login;

import qiuchenly.JSAHVCSC.Base.BaseResult;

/**
 * Created by QiuChenly on 2017年10月31日 0031.
 */

public interface iLogin extends BaseResult {
    @Override
    void onFailed(String errReason);

    @Override
    void onSuccess();
}
