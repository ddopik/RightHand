package defaultIntializarion;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmObject;


/**
 * Created by ddopik on 10/10/2017.
 */

public class RealmSingleton {

    private Context appContext;
    private Realm realm;

    public RealmSingleton(Realm realm)
    {
        this.realm=realm;
    }

    //    public static <T> List<T> toList(String json, Class<T> type,  ObjectMapperProperties objectMapperProperties)
    public <E extends RealmObject> void saveNewItemToRealm(E object) {

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(object);
        realm.commitTransaction();
    }

    public <E extends RealmObject> E getSingleRealmItem(Class<E> className, String id_Key, int id) {
        return realm.where(className).equalTo("id_Key", id).findFirst();
    }

}
