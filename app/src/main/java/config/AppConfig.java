package config;

import io.realm.Realm;

/**
 * Created by ddopik on 10/10/2017.
 */

public class AppConfig extends defaultIntializarion.AppConfig {


    public static  Realm realm;

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
