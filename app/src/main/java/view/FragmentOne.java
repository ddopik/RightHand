package view;


import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ddopik.scopelistner.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import defaultIntializarion.AppConfig;
import io.realm.RealmResults;
import model.ScopeListenerModel;
import model.tabels.SingleItem;
import presenter.Adapters.ItemsAdapter;
import presenter.FragmentOnePresenter;
import presenter.pojoClasses.SingleItemMessage;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * Created by ddopik on 9/16/2017.
 */

public class FragmentOne extends Fragment {


    public static String FragmentOneTag = "FragmentOne";
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

    private FragmentOnePresenter fragmentOnePresenter;
    private SearchView searchView;
    private ItemsAdapter itemsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentOnePresenter = new FragmentOnePresenter(getActivity());
        fragmentOnePresenter.saveTestItem();

        itemsList = fragmentOnePresenter.getFullItems();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.e("FragmentOne", "FragmentOne ---->onCreateView()");
        mainView = inflater.inflate(R.layout.fragment_one_scope_listner, container, false);
        unbinder = ButterKnife.bind(this, mainView);
        setHasOptionsMenu(true);
        initializeRecyclerView(itemsList);
        fragmentOnePresenter.askFor_mic_permation();

        return mainView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.fragment_1_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);

        // Associate searchable configuration with the SearchView

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                itemsList = fragmentOnePresenter.querySingleItem(s);
                recyclerView.setAdapter(new ItemsAdapter(itemsList, getActivity(), FragmentOne.this));
                itemsAdapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.e(FragmentOneTag, "----->Null Search");
                itemsList = new ScopeListenerModel(AppConfig.realm).querySingleItem(s);
                itemsAdapter.setItemsList(itemsList);
                itemsAdapter.notifyDataSetChanged();
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                recyclerView.setAdapter(new ItemsAdapter(fragmentOnePresenter.getFullItems(), getActivity(), FragmentOne.this));
                itemsAdapter.notifyDataSetChanged();
                return false;
            }
        });
        menu.findItem(R.id.search).setActionView(searchView);
        /////////////////////////////////////////

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_new:
                fragmentOnePresenter.launchSingleItemDialogFragment();
                return true;
            case R.id.edit:
                // TODO put your code here to respond to the button tap
                Toast.makeText(getActivity(), "ADD!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Subscribe  /// activated when dialog fragment updated
    public void onMessageEvent(SingleItemMessage message) {
        if (message.getUpdateFragmentOneList()) {
            itemsAdapter.notifyDataSetChanged();
        }
    }

    public void initializeRecyclerView(RealmResults<SingleItem> itemsList) {


        itemsAdapter = new ItemsAdapter(getActivity());
        itemsAdapter.setItemsList(itemsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(itemsAdapter);


    }

    @OnClick(R.id.btnSpeak)
    public void speakVoiceSearch() {
        promptSpeechInput();
    }

    private void promptSpeechInput() {


        SpeechRecognizer speech = SpeechRecognizer.createSpeechRecognizer(getActivity());

        Intent voiceRecognizer = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);


        //        Simply takes user’s speech input and returns it to same activity
        voiceRecognizer.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
//        Those are the pattern or all words that android use to math your input
        voiceRecognizer.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        voiceRecognizer.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ar-EG");
//        is set to a string to display to the user during speech input.
        voiceRecognizer.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));

        try {
//            in case you you wan't google Speack Dialog
            //// onActivtyResult will be activated
//            startActivityForResult(voiceRecognizer, REQ_CODE_SPEECH_INPUT);
            speech.startListening(voiceRecognizer);
            speech.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle params) {

                }

                @Override
                public void onBeginningOfSpeech() {

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
                public void onError(int error) {

                }

                @Override
                public void onResults(Bundle results) {

                    ArrayList<String> result = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    txtSpeechInput.setText(result.get(0));
                    searchView.setIconified(false);
                    searchView.setQuery(result.get(0), true);

                }

                @Override
                public void onPartialResults(Bundle partialResults) {
                    ArrayList<String> result = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    txtSpeechInput.setText(result.get(0));
                    searchView.setIconified(false);
                    searchView.setQuery(result.get(0), true);
                }

                @Override
                public void onEvent(int eventType, Bundle params) {

                }
            });
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getActivity(), getString(R.string.speech_not_supported), Toast.LENGTH_SHORT).show();
        }


        /////////////////////

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
                    searchView.setIconified(false);
                    searchView.setQuery(result.get(0), true);
                }
                break;
            }

        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
