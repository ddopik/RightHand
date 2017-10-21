package presenter;


import android.app.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.networkmodule.permationsController.PermationController;

import defaultIntializarion.AppConfig;
import defaultIntializarion.RealmSingleton;
import defaultIntializarion.Utilization;
import io.realm.RealmResults;
import model.ScopeListenerModel;
import model.tabels.SingleItem;
import presenter.Adapters.ItemsAdapter;
import view.ItemExistenceDialogFragment;
import view.SingleItemDialogFragment;

/**
 * Created by ddopik on 10/11/2017.
 */

public class FragmentOnePresenter {

    public RealmSingleton realmSingleton = new RealmSingleton(AppConfig.realm);
    public Utilization utilization = new Utilization();
    private PermationController permationController;
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
    public void launchSingleItemDialogFragment( int id, int requestCode) {
        FragmentManager fm = ((Activity) context).getFragmentManager();
        SingleItemDialogFragment dialogFragment = new SingleItemDialogFragment();
        Bundle bundle_id = new Bundle();
        bundle_id.putInt("item_id", id);
        dialogFragment.setArguments(bundle_id);
        dialogFragment.show(fm, "SingleItemDialogFragment");

    }
    /// Item Clicked from RecyclerView (Simple dialog with two checkBoxes)
    public void launchItemExistenceDialogFragment(Fragment fragment, int id, int requestCode) {
        FragmentManager fm = ((Activity) context).getFragmentManager();
        ItemExistenceDialogFragment dialogFragment = new ItemExistenceDialogFragment();
        Bundle bundle_id = new Bundle();
        bundle_id.putInt("itemId", id);
        dialogFragment.setArguments(bundle_id);
        dialogFragment.setTargetFragment(fragment,requestCode);
        dialogFragment.show(fm,"Existence DialogFragment");

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
    public void askFor_mic_permation() {
        if (Build.VERSION.SDK_INT >= 23) {
            permationController = new PermationController(((Activity) context)) {
                @Override
                public ExternalPermeation_Cancel_Method setExternalPermeation_Cancel_Method() {

                    return new ExternalPermeation_Cancel_Method() {
                        @Override
                        public void ExternalPermeation_Cancel_Method() {
                            Log.e("FragmentOne", "TraccerHere------------->Horaaaai");
                        }
                    };
                }
            };

            permationController.askSinglePermeation(android.Manifest.permission.RECORD_AUDIO, "message", 101);
        } else {
            //pre mashmello
        }
    }
    public RealmResults<SingleItem> getFullItems()
    {
      return   new ScopeListenerModel(AppConfig.realm).getFullItems();
    }
    public void saveTestItem() {
        SingleItem singleItem1 = new SingleItem();
        singleItem1.setItemId(1);
        singleItem1.setItemName("أستيكه");
        singleItem1.setItemPrice("1.5");
        singleItem1.setItemExistence(true);
        singleItem1.setItemUpdate(utilization.getCurrentDate("def"));

        ////////////////////////
        SingleItem singleItem2 = new SingleItem();
        singleItem2.setItemId(2);
        singleItem2.setItemName("ألوان");
        singleItem2.setItemPrice("2.5");
        singleItem2.setItemExistence(true);
        singleItem2.setItemUpdate(utilization.getCurrentDate("def"));
        ////////////////////////
        SingleItem singleItem3 = new SingleItem();
        singleItem3.setItemId(3);
        singleItem3.setItemName("مسطره");
        singleItem3.setItemPrice("1.5");
        singleItem3.setItemExistence(true);
        singleItem3.setItemUpdate(utilization.getCurrentDate("def"));
        ////////////////////////
        SingleItem singleItem4 = new SingleItem();
        singleItem4.setItemId(4);
        singleItem4.setItemName("قلم جيل");
        singleItem4.setItemPrice("3.5");
        singleItem4.setItemExistence(true);
        singleItem4.setItemUpdate(utilization.getCurrentDate("def"));
        ////////////////////////
        SingleItem singleItem5 = new SingleItem();
        singleItem5.setItemId(5);
        singleItem5.setItemName("برايه");
        singleItem5.setItemPrice("1.5");
        singleItem5.setItemExistence(true);
        singleItem5.setItemUpdate(utilization.getCurrentDate("def"));
        ////////////////////////
        SingleItem singleItem6 = new SingleItem();
        singleItem6.setItemId(6);
        singleItem6.setItemName("ورق تصوير");
        singleItem6.setItemPrice("55");
        singleItem6.setItemExistence(true);
        singleItem6.setItemUpdate(utilization.getCurrentDate("def"));
        ////////////////////////
        SingleItem singleItem7 = new SingleItem();
        singleItem7.setItemId(7);
        singleItem7.setItemName("ورق كربون");
        singleItem7.setItemPrice("1.5");
        singleItem7.setItemExistence(true);
        singleItem7.setItemUpdate(utilization.getCurrentDate("def"));
        ////////////////////////
        SingleItem singleItem8 = new SingleItem();
        singleItem8.setItemId(8);
        singleItem8.setItemName("فايل عادى");
        singleItem8.setItemPrice("3");
        singleItem8.setItemExistence(true);
        singleItem8.setItemUpdate(utilization.getCurrentDate("def"));
        ////////////////////////
        SingleItem singleItem9 = new SingleItem();
        singleItem9.setItemId(9);
        singleItem9.setItemName("ورق بريستون");
        singleItem9.setItemPrice("3");
        singleItem9.setItemExistence(true);
        singleItem9.setItemUpdate(utilization.getCurrentDate("def"));
        ////////////////////////
        SingleItem singleItem10 = new SingleItem();
        singleItem10.setItemId(10);
        singleItem10.setItemName("قص و لزق");
        singleItem10.setItemPrice("3.5");
        singleItem10.setItemExistence(true);
        singleItem10.setItemUpdate(utilization.getCurrentDate("def"));
        ////////////////////////
        SingleItem singleItem11 = new SingleItem();
        singleItem11.setItemId(11);
        singleItem11.setItemName("دبوس مكتب");
        singleItem11.setItemPrice("1");
        singleItem11.setItemExistence(true);
        singleItem11.setItemUpdate(utilization.getCurrentDate("def"));


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
