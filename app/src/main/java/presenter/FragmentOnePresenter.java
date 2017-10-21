package presenter;


import android.app.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import android.widget.Toast;

import defaultIntializarion.AppConfig;
import defaultIntializarion.RealmSingleton;
import defaultIntializarion.Utilization;
import io.realm.RealmResults;
import model.ScopeListenerModel;
import model.tabels.SingleItem;
import view.ItemExistenceDialogFragment;
import view.SingleItemDialogFragment;

/**
 * Created by ddopik on 10/11/2017.
 */

public class FragmentOnePresenter {

    public RealmSingleton realmSingleton = new RealmSingleton(AppConfig.realm);
    Utilization utilization = new Utilization();
    ScopeListenerModel model = new ScopeListenerModel(AppConfig.realm);
    private Context context;


    public FragmentOnePresenter(Context context) {
        this.context = context;
    }

    public SingleItem getSingleItem(int id) {
        return realmSingleton.getSingleRealmItem(SingleItem.class, "itemId", id);
    }

    //// Empty Item Fired to add New Item
    public void launchSingleItemDialogFragment() {
        FragmentManager fm = ((Activity) context).getFragmentManager();
        SingleItemDialogFragment dialogFragment = new SingleItemDialogFragment();
        Bundle bundle_id = new Bundle();
//        bundle_id.putInt("item_id", Integer.parseInt(item_id.getText().toString()));
//        dialogFragment.setArguments(bundle_id);
        dialogFragment.show(fm, "SingleItemDialogFragment");

    }

    /// Item Clicked from RecyclerView
    public void launchSingleItemDialogFragment( int id, int requstCode) {
        FragmentManager fm = ((Activity) context).getFragmentManager();
        SingleItemDialogFragment dialogFragment = new SingleItemDialogFragment();
        Bundle bundle_id = new Bundle();
        bundle_id.putInt("item_id", id);
        dialogFragment.setArguments(bundle_id);
        dialogFragment.show(fm, "SingleItemDialogFragment");

    }
    /// Item Clicked from RecyclerView
    public void launchItemExistenceDialogFragment(Fragment fragment, int id, int requestCode) {
        FragmentManager fm = ((Activity) context).getFragmentManager();
        ItemExistenceDialogFragment dialogFragment = new ItemExistenceDialogFragment();
        Bundle bundle_id = new Bundle();
        bundle_id.putInt("itemId", id);
        dialogFragment.setArguments(bundle_id);
        dialogFragment.setTargetFragment(fragment,requestCode);
        dialogFragment.show(fm,"Existence DialogFragment");

    }

    public void setItemExistence(int id, boolean existence)
    {
        model.setSingleItemItemExistence(id,existence);
    }

    public RealmResults<SingleItem> querySingleItem(String query) {
        return model.querySingleItem(query);
    }

    public void AddNewItem(SingleItem singleItem) {
        try {
            Toast.makeText(((Activity) context), singleItem.getItemId(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

        }
        realmSingleton.saveNewItemToRealm(singleItem);
    }

    public void saveTestItem() {
        SingleItem singleItem1 = new SingleItem();
        singleItem1.setItemId(1);
        singleItem1.setItemName("أستيكه");
        singleItem1.setItemPrice("1.5");
        singleItem1.setItemExistence(true);
        singleItem1.setItemUpdate(utilization.getCurrentDate());

        ////////////////////////
        SingleItem singleItem2 = new SingleItem();
        singleItem2.setItemId(2);
        singleItem2.setItemName("ألوان");
        singleItem2.setItemPrice("2.5");
        singleItem2.setItemExistence(true);
        singleItem2.setItemUpdate(utilization.getCurrentDate());
        ////////////////////////
        SingleItem singleItem3 = new SingleItem();
        singleItem3.setItemId(3);
        singleItem3.setItemName("مسطره");
        singleItem3.setItemPrice("1.5");
        singleItem3.setItemExistence(true);
        singleItem3.setItemUpdate(utilization.getCurrentDate());
        ////////////////////////
        SingleItem singleItem4 = new SingleItem();
        singleItem4.setItemId(4);
        singleItem4.setItemName("قلم جيل");
        singleItem4.setItemPrice("3.5");
        singleItem4.setItemExistence(true);
        singleItem4.setItemUpdate(utilization.getCurrentDate());
        ////////////////////////
        SingleItem singleItem5 = new SingleItem();
        singleItem5.setItemId(5);
        singleItem5.setItemName("برايه");
        singleItem5.setItemPrice("1.5");
        singleItem5.setItemExistence(true);
        singleItem5.setItemUpdate(utilization.getCurrentDate());
        ////////////////////////
        SingleItem singleItem6 = new SingleItem();
        singleItem6.setItemId(6);
        singleItem6.setItemName("ورق تصوير");
        singleItem6.setItemPrice("55");
        singleItem6.setItemExistence(true);
        singleItem6.setItemUpdate(utilization.getCurrentDate());
        ////////////////////////
        SingleItem singleItem7 = new SingleItem();
        singleItem7.setItemId(7);
        singleItem7.setItemName("ورق كربون");
        singleItem7.setItemPrice("1.5");
        singleItem7.setItemExistence(true);
        singleItem7.setItemUpdate(utilization.getCurrentDate());
        ////////////////////////
        SingleItem singleItem8 = new SingleItem();
        singleItem8.setItemId(8);
        singleItem8.setItemName("فايل عادى");
        singleItem8.setItemPrice("3");
        singleItem8.setItemExistence(true);
        singleItem8.setItemUpdate(utilization.getCurrentDate());
        ////////////////////////
        SingleItem singleItem9 = new SingleItem();
        singleItem9.setItemId(9);
        singleItem9.setItemName("ورق بريستون");
        singleItem9.setItemPrice("3");
        singleItem9.setItemExistence(true);
        singleItem9.setItemUpdate(utilization.getCurrentDate());
        ////////////////////////
        SingleItem singleItem10 = new SingleItem();
        singleItem10.setItemId(10);
        singleItem10.setItemName("قص و لزق");
        singleItem10.setItemPrice("3.5");
        singleItem10.setItemExistence(true);
        singleItem10.setItemUpdate(utilization.getCurrentDate());
        ////////////////////////
        SingleItem singleItem11 = new SingleItem();
        singleItem11.setItemId(11);
        singleItem11.setItemName("دبوس مكتب");
        singleItem11.setItemPrice("1");
        singleItem11.setItemExistence(true);
        singleItem11.setItemUpdate(utilization.getCurrentDate());


        realmSingleton.saveNewItemToRealm(singleItem1);
        realmSingleton.saveNewItemToRealm(singleItem2);
        realmSingleton.saveNewItemToRealm(singleItem3);
        realmSingleton.saveNewItemToRealm(singleItem4);
        realmSingleton.saveNewItemToRealm(singleItem5);
        realmSingleton.saveNewItemToRealm(singleItem6);
        realmSingleton.saveNewItemToRealm(singleItem7);
        realmSingleton.saveNewItemToRealm(singleItem8);
        realmSingleton.saveNewItemToRealm(singleItem9);
        realmSingleton.saveNewItemToRealm(singleItem10);
        realmSingleton.saveNewItemToRealm(singleItem11);

    }
}
