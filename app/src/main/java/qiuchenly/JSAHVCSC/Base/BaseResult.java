package qiuchenly.JSAHVCSC.Base;

/**
 * Created by QiuChenly on 2017年10月31日 0031.
 */

public interface BaseResult {
    void onSuccess();
    void onFailed(String errReason);
}
