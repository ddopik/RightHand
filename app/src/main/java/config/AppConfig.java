package config;

import android.content.Context;
import android.support.multidex.MultiDex;

import io.realm.Realm;

/**
 * Created by ddopik on 10/10/2017.
 */

public class AppConfig extends defaultIntializarion.AppConfig {


    public static  Realm realm;
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        this.app = this;

        intializeRealmInstance(); //intializing Realm Config Instance
        realm=super.realm;
        intializeSteatho();
//        deleteCache(app);   ///for developing        ##################

    }



}
