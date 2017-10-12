package view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import defaultIntializarion.AppConfig;
import io.realm.RealmResults;
import model.ScopeListenerModel;
import model.tabels.SingleItem;
import presenter.Adapters.ItemsAdapter;
import presenter.FragmentOnePresenter;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * Created by ddopik on 9/16/2017.
 */

public class FragmentOne extends Fragment implements RecognitionListener {

    private final int REQ_CODE_SPEECH_INPUT = 100;
    @BindView(R.id.items_recycler_view)
    public RecyclerView recyclerView;
    @BindView(R.id.txtSpeechInput)
    TextView txtSpeechInput;
    @BindView(R.id.btnSpeak)
    ImageButton btnSpeak;
    private RealmResults<SingleItem> itemsList;
    private Unbinder unbinder;
    private View mainView;
    private PermationController permationController;
    private FragmentOnePresenter fragmentOnePresenter;


    private ItemsAdapter itemsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentOnePresenter = new FragmentOnePresenter();
        fragmentOnePresenter.saveNewItem();
        itemsList=new ScopeListenerModel(AppConfig.realm).getFullItems();
        itemsAdapter = new ItemsAdapter(itemsList,getActivity());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_two_scope_listner, container, false);
        unbinder = ButterKnife.bind(this, mainView);


        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(itemsAdapter);


        askFor_mic_permation();
        return mainView;
    }

    private void promptSpeechInput() {


        SpeechRecognizer speech = SpeechRecognizer.createSpeechRecognizer(getActivity());
        speech.setRecognitionListener(this);
        Intent voicerecogize = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);


        //        Simply takes user’s speech input and returns it to same activity
        voicerecogize.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
//        Those are the pattern or all words that android use to math your input
        voicerecogize.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        voicerecogize.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ar-EG");
//        is set to a string to display to the user during speech input.
        voicerecogize.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));

        try {
            startActivityForResult(voicerecogize, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), getString(R.string.speech_not_supported), Toast.LENGTH_SHORT).show();
        }


        /////////////////////

    }

    @Override
    public void onEvent(int arg0, Bundle arg1) {
    }

    @Override
    public void onPartialResults(Bundle arg0) {
    }

    @Override
    public void onReadyForSpeech(Bundle arg0) {
    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onResults(Bundle results) {
        ArrayList<String> matches = results
                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
    }

    @Override
    public void onRmsChanged(float rmsdB) {
    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int errorCode) {
//        switch (errorCode) {
//            case SpeechRecognizer.ERROR_AUDIO:
//                message = R.string.error_audio_error;
//                break;
//            case SpeechRecognizer.ERROR_CLIENT:
//                message = R.string.error_client;
//                break;
//            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
//                message = R.string.error_permission;
//                break;
//            case SpeechRecognizer.ERROR_NETWORK:
//                message = R.string.error_network;
//                break;
//            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
//                message = R.string.error_timeout;
//                break;
//            case SpeechRecognizer.ERROR_NO_MATCH:
//                message = R.string.error_no_match;
//                break;
//            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
//                message = R.string.error_busy;
//                break;
//            case SpeechRecognizer.ERROR_SERVER:
//                message = R.string.error_server;
//                break;
//            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
//                message = R.string.error_timeout;
//                break;
//            default:
//                message = R.string.error_understand;
//                break;
//        }
    }

    private void speechRecognition_type_1() {

        Intent voicerecogize = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//        Simply takes user’s speech input and returns it to same activity
        voicerecogize.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
//        Those are the pattern or all words that android use to math your input
        voicerecogize.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        voicerecogize.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ar-EG");
//        is set to a string to display to the user during speech input.
        voicerecogize.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));


        try {
            startActivityForResult(voicerecogize, REQ_CODE_SPEECH_INPUT);
//            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtSpeechInput.setText(result.get(0));
                }
                break;
            }

        }
    }

    private void askFor_mic_permation() {
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

            permationController.askSinglePermeation(android.Manifest.permission.RECORD_AUDIO, "message", 101);
        } else {
            //pre mashmello
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
