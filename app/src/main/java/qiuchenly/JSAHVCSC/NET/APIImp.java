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
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;

import java.io.IOException;
import java.io.InputStream;

import qiuchenly.JSAHVCSC.Login.iLogin;
import qiuchenly.JSAHVCSC.Login.vCodeRet;
import qiuchenly.JSAHVCSC.R;

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
  final String ERR_LOGINFAILED_PASSWORD = "密码错误！请重新输入！";
  final String ERR_LOGINFAILED_USERNAME = "账号错误！请检查输入！";

  final String LOGIN_URL = "https://cas.jsahvc.edu.cn/lyuapServer/login";
  final String GETCAPCHA = "https://cas.jsahvc.edu.cn/lyuapServer/captcha.jsp?time0.7153753779077225";

  private static android.content.Context contextA;


  @Override
  public void login(String user, String pass, String vCode, android.content.Context con
          , final iLogin loginResult) {
    if (contextA == null) {
      contextA = con;
    }
    String res = getRSAEncrypt("123123");
    loginResult.onSuccess();
  }

  public void login() {
    if (client == null) {
      client = new DefaultHttpClient();
    }
    HttpPost http = new HttpPost(LOGIN_URL);

  }


  String getRSAEncrypt(String pass) {
    //进入Context（）
    Context context = Context.enter();
    context.setOptimizationLevel(-1);
    Scriptable script = context.initStandardObjects();
    InputStream input = contextA.getResources().openRawResource(R.raw.jsahvc);
    byte bytes[] = new byte[0];
    try {
      bytes = new byte[input.available()];
      input.read(bytes);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        input.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    String JavaScriptCode = new String(bytes);
    context.evaluateString(script, JavaScriptCode, "JavaScript", 0, null);
    Object obj = script.get("getpass", script);
    String result = "";
    if (obj instanceof Function) {
      Function function = (Function) obj;
      //TODO:此函数调用将导致进程卡死
      Object resultObj = function.call(context, script, script, new Object[]{pass});
      result = resultObj.toString();
    }
    Context.exit();
    return result;
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
