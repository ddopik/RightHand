package view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ddopik.scopelistner.R;
import com.example.networkmodule.permationsController.PermationController;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;


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
        mainView = inflater.inflate(R.layout.fragment_two_scope_listner, container, false);
        unbinder = ButterKnife.bind(this, mainView);

        return mainView;
    }






    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

