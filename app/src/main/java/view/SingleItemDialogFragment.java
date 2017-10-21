package view;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ddopik.scopelistner.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import model.tabels.SingleItem;
import presenter.FragmentOnePresenter;
import presenter.pojoClasses.SingleItemMessage;

/**
 * Created by ddopik on 10/14/2017.
 */

public class SingleItemDialogFragment extends DialogFragment {


    @BindView(R.id.item_id)
    public TextView item_id;
    @BindView(R.id.item_name_val)
    public EditText item_name;
    @BindView(R.id.item_price_val)
    public EditText item_price;
    @BindView(R.id.item_existence_val)
    public EditText item_existence;
    @BindView(R.id.item_last_update_val)
    public EditText item_last_update;
    private View rootView;
    private SingleItem singleItem;
    private Unbinder unbinder;
    private FragmentOnePresenter fragmentOnePresenter;
    private int item_existence_val = 10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.single_item_dialog_fragment, container, false);
        getDialog().setTitle("Simple Dialog");
        unbinder = ButterKnife.bind(this, rootView);

        fragmentOnePresenter = new FragmentOnePresenter(getActivity());

        if (getArguments() != null)
            if (getArguments().getInt("item_id") >= 0)
                initializeItem(getArguments().getInt("item_id"));


        return rootView;
    }

    public void initializeItem(int id) {
        singleItem = fragmentOnePresenter.getSingleItem(id);
        item_id.setText(singleItem.getItemId() + "");
        item_name.setText(singleItem.getItemName());
        item_existence.setText(singleItem.isItemExistence() + "");
        item_price.setText(singleItem.getItemPrice());
        item_last_update.setText(singleItem.getItemUpdate());
    }

    @OnClick(R.id.dialog_save_btn)
    public void saveItem() {
        SingleItem singleItem = new SingleItem();
        int id;

        singleItem.setItemName(item_name.getText().toString());
        singleItem.setItemPrice(item_price.getText().toString());
        singleItem.setItemExistence(Boolean.parseBoolean(item_existence.getText().toString()));
        singleItem.setItemUpdate(item_last_update.getText().toString());
        if (item_id.getText() == null || item_id.getText() == "") {
            id = fragmentOnePresenter.realmSingleton.getLastID(SingleItem.class, "itemId");
        } else {
            id = Integer.parseInt(item_id.getText().toString());
        }
        singleItem.setItemId(id);

        getDialog().dismiss();

        fragmentOnePresenter.AddNewItem(singleItem);


        SingleItemMessage sss = new SingleItemMessage();
        sss.setUpdateFragmentOneList(true);
        EventBus.getDefault().post(sss);


    }


    @OnClick(R.id.item_existence_val)
    public void setItem_existence() {
        fragmentOnePresenter.launchItemExistenceDialogFragment(this, Integer.parseInt(item_id.getText().toString()), item_existence_val);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == item_existence_val) {

//            item_existence.setText(singleItem.isItemExistence() + "");
            item_existence.setText(data.getExtras().getBoolean("State") + "");

        }
    }


    @OnClick(R.id.item_last_update_val)
    public void pickdate() {
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            }
        };

        DatePickerDialog dialog = new DatePickerDialog(getActivity(), onDateSetListener, 2013, 2, 18);
        dialog.show();
    }


    @OnClick(R.id.dialog_cancel_btn)
    public void cancel_btn() {
        getDialog().dismiss();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }
}

