package view;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.ddopik.scopelistner.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import model.tabels.SingleItem;
import presenter.FragmentOnePresenter;

/**
 * Created by ddopik on 10/20/2017.
 */

public class ItemExistenceDialogFragment extends DialogFragment {

    @BindView(R.id.item_false)
    CheckBox item_false;
    @BindView(R.id.item_true)
    CheckBox item_true;
    private FragmentOnePresenter fragmentOnePresenter;
    private SingleItem singleItem;
    private View mainView;
    private Unbinder unBinder;
    private boolean state;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.availabe_dialog_fragment, container, false);
        unBinder = ButterKnife.bind(this, mainView);
        fragmentOnePresenter = new FragmentOnePresenter(getActivity());

        if (getArguments().getInt("itemId") > 0) {
            singleItem = fragmentOnePresenter.getSingleItem(getArguments().getInt("itemId"));
            boolean isExist = singleItem.isItemExistence();
            if (isExist) {
                item_true.setChecked(true);
                state = true;
            } else {
                item_false.setChecked(true);
                state = false;
            }

        }


        return mainView;

    }

    @OnClick(R.id.item_true)
    public void trueEvent() {
        item_false.setChecked(false);
        state=true;
//        fragmentOnePresenter.setItemExistence(getArguments().getInt("itemId"),true);

    }

    @OnClick(R.id.item_false)
    public void falseEvent() {
        item_true.setChecked(false);
        state=false;
//        fragmentOnePresenter.setItemExistence(getArguments().getInt("itemId"),false);
    }


    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        Intent intent = new Intent();
        intent.putExtra("ItemId", getArguments().getInt("itemId"));
        intent.putExtra("State",state);
        getTargetFragment().onActivityResult(getTargetRequestCode(), 10, intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unBinder.unbind();
    }
}
