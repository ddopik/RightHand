package model;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import model.tabels.SingleItem;

/**
 * Created by ddopik on 10/11/2017.
 */

public class ScopeListenerModel {
    private Realm realm;

    public ScopeListenerModel(Realm realm)
    {
        this.realm=realm;
    }


public RealmResults<SingleItem> getFullItems()
{
    return realm.where(SingleItem.class).findAll();
}

}
