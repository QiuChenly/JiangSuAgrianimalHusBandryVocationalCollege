package qiuchenly.JSAHVCSC;

import android.view.View;

import qiuchenly.JSAHVCSC.Base.BaseApp;
import qiuchenly.JSAHVCSC.Base.iSetting.Sets;

public class Splash extends BaseApp {

    @Override
    public Sets getDefaultSet(Sets sets) {
        sets.ViewID=R.layout.view_splash;
        sets.allowStatusBarTranslate=true;
//        sets.doubleClickToExit=false;
        sets.hideTitleBar=true;
        sets.NullBack=true;
        return sets;
    }

    @Override
    public void onClick(View v) {

    }
}
