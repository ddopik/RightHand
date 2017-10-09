package view;

import android.Manifest;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ddopik.scopelistner.R;
import com.example.networkmodule.permationsController.PermationController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by ddopik on 9/16/2017.
 */

public class FragmentOne extends Fragment {
    public static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;
    public static final int REQUEST_PERMISSION_SETTING = 101;
    public static final int REQUEST_PERMISSION_Contact = 102;
    PermationController permationController;
    private View mainView;
    private Unbinder unbinder;
    private SharedPreferences permissionStatus;
    private boolean sentToSettings = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_one, container, false);
        unbinder = ButterKnife.bind(this.mainView);

        if (Build.VERSION.SDK_INT >= 23) {
            permationController = new PermationController(getActivity()) {
                @Override
                public ExternalPermeation_Cancel_Method setExternalPermeation_Cancel_Method() {

                    return new ExternalPermeation_Cancel_Method() {
                        @Override
                        public void ExternalPermeation_Cancel_Method() {
                            Toast.makeText(getActivity(), "Horaiiiiii", Toast.LENGTH_SHORT).show();
                            Log.e("FragmentOne", "TraccerHere------------->Horaaaai");
                        }
                    };
                }
            };

//            permationController.askSinglePermation(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,"message",101);

           List<HashMap<String,String>> permations=new ArrayList<HashMap<String, String>>();
            HashMap<String,String> permatiosList=new HashMap<String, String>();
            permatiosList.put("permeation",android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            permatiosList.put("permeationCode","100");
            permations.add(permatiosList);

            HashMap<String,String> permatiosList2=new HashMap<String, String>();
            permatiosList2.put("permeation",Manifest.permission.READ_CONTACTS);
            permatiosList2.put("permeationCode","102");
            permations.add(permatiosList2);
            permationController.askMuiltyPermeation(permations,"in order to use this we need youur apprval");
        } else {
            // Pre-Marshmallow
        }


        return mainView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
