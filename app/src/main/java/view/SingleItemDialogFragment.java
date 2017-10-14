package view;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ddopik.scopelistner.R;

/**
 * Created by ddopik on 10/14/2017.
 */

public class SingleItemDialogFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.single_item_dialog_fragment, container, false);
        getDialog().setTitle("Simple Dialog");
        return rootView;
    }
}

