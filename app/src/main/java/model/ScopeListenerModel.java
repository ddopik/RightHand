package model;

import io.realm.Realm;
import io.realm.RealmResults;
import model.tabels.SingleItem;

/**
 * Created by ddopik on 10/11/2017.
 */

public class ScopeListenerModel {
    private Realm realm;

    public ScopeListenerModel(Realm realm) {
        this.realm = realm;
    }


    public RealmResults<SingleItem> getFullItems() {
        return realm.where(SingleItem.class).findAll();
    }

    public RealmResults<SingleItem> querySingleItem(String query)
    {
        // *char  ---> match Only if there is any characters before it
        // *char* ---> match Only if there is any characters before it and any character after it
        // char*  ---> match Only if there is any characters after it
        return realm.where(SingleItem.class).like("itemName","*"+query+"*").findAll();
    }

}
