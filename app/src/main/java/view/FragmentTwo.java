package view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ddopik.scopelistner.R;
import com.example.networkmodule.permationsController.PermationController;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by ddopik on 9/16/2017.
 */

public class FragmentTwo extends Fragment   {


    private final int REQ_CODE_SPEECH_INPUT = 100;
    @BindView(R.id.txtSpeechInput)
    TextView txtSpeechInput;
    @BindView(R.id.btnSpeak)
    ImageButton btnSpeak;
    private Unbinder unbinder;
    private View mainView;
    private PermationController permationController;
    private String message;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_one_scope_listner, container, false);
        unbinder = ButterKnife.bind(this, mainView);

        return mainView;
    }






    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

