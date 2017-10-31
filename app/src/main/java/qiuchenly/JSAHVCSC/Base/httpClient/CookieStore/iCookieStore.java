package qiuchenly.JSAHVCSC.Base.httpClient.CookieStore;

/**
 * Author : QiuChenLy
 * Date    : ${DATA}
 * Func    : null
 * LastEdit: ${DATA}
 */

public interface iCookieStore {
    String getCookieCollection();
    void setCookie(String key,String value);
    void updataCookie(String key,String newCookie);
    String getCookieValue(String key);
    void deleteCookie(String key);
    int HaveKey(String key);

}
