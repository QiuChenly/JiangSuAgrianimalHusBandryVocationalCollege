package qiuchenly.JSAHVCSC.Base.httpClient.CookieStore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author : QiuChenLy
 * Date    : 8.28 日 周一 PM 2:06
 * Func    : 轻量级CookieStoreImplement
 * LastEdit: 8.28 日 周一 PM 2:06
 */

public class CookieStores implements iCookieStore {
    List<CookieKeyValue> Cookies = null;
    iCookieManage manage = null;

    public CookieStores(List<CookieKeyValue> cookies) {
        Cookies = cookies;
    }

    public CookieStores() {
        Cookies = new ArrayList<>();
    }

    @Override
    public String getCookieCollection() {
        int count = 0;
        String result = "";
        Iterator<CookieKeyValue> iterator = Cookies.iterator();
        while (iterator.hasNext()) {
            count++;
            CookieKeyValue key = iterator.next();
            result = result + key.key + "=" + key.value + ";";
        }
        return result;
    }

    @Override
    public void setCookie(String key, String value) {
        int a = HaveKey(key);
        if (a != -1) {
            Cookies.get(a).value = value;
            return;
        }
        CookieKeyValue k = new CookieKeyValue();
        k.key = key;
        k.value = value;
        Cookies.add(k);
    }

    @Override
    public void updataCookie(String key, String newValue) {
        setCookie(key, newValue);
    }

    @Override
    public String getCookieValue(String key) {
        int a = HaveKey(key);
        if (a != -1) {
            return Cookies.get(a).value;
        }
        return "";
    }

    @Override
    public void deleteCookie(String key) {
        int a = HaveKey(key);
        if (a != -1) {
            Cookies.remove(a);
        }
    }

    @Override
    public int HaveKey(String key) {
        int has = -1;
        int count = 0;
        for (Iterator<CookieKeyValue> i = Cookies.iterator(); i.hasNext(); ) {

            CookieKeyValue c = i.next();
            if (c.key.contains(key)) {
                has = count;
                break;
            }
            count++;
        }
        return has;
    }
}
