package qiuchenly.JSAHVCSC.NET;


import android.graphics.BitmapFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import qiuchenly.JSAHVCSC.Login.iLogin;
import qiuchenly.JSAHVCSC.Login.vCodeRet;

/**
 * Created by QiuChenly on 2017年10月31日 0031.
 */

/*
    网络请求暂时不写
 */
public class APIImp implements API {

    private static HttpClient client;
    private static RequestConfig config;

    final String ERR_VCODEGETFAILED = "验证码加载失败!";

    final String LOGIN_URL = "https://cas.jsahvc.edu.cn/lyuapServer/login";
    final String GETCAPCHA = "https://cas.jsahvc.edu.cn/lyuapServer/captcha.jsp?time0.7153753779077225";


    @Override
    public void login(String user, String pass, String vCode, final iLogin loginResult) {
        loginResult.onSuccess();
    }

    public void login() {
        if (client == null) {
            client = new DefaultHttpClient();
        }
        HttpPost http = new HttpPost(LOGIN_URL);

    }


    public void getLoginImage(final vCodeRet ret) {
        new Thread() {
            @Override
            public void run() {
                if (client == null) {
                    client = new DefaultHttpClient();
                }
                HttpGet get = new HttpGet(GETCAPCHA);
                try {
                    //执行请求操作，并拿到结果（同步阻塞）
                    HttpResponse response = client.execute(get);
                    //获取结果实体
                    HttpEntity entity = response.getEntity();
                    byte[] bytes = EntityUtils.toByteArray(entity);
                    if (bytes.length > 0)
                        ret.onSucess(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                    else ret.onFailed(ERR_VCODEGETFAILED);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
