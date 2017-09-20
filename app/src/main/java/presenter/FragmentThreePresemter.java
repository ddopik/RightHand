package presenter;

import android.app.Activity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ddopik.scopelistner.R;

import java.util.ArrayList;

import presenter.netWork.ApiUtils;
import presenter.netWork.retroFit_interface.CnnServiceInterface;
import presenter.netWork.retroFit_interface.SOService;
import presenter.netWork.retroFit_models.CnnMainModel;
import presenter.netWork.retroFit_models.Item;
import presenter.netWork.retroFit_models.SOAnswersResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.AnswersAdapter;
import view.MainActivity;

/**
 * Created by ddopik on 9/20/2017.
 */

public class FragmentThreePresemter {

    private CnnServiceInterface cnnServiceInterface;
    private ApiUtils apiUtils;
    private AnswersAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SOService mService;
    private Activity mainActivity;
    private View mainView;

    public FragmentThreePresemter(Activity mainActivity, View view)
    {
        this.mainActivity=mainActivity;
        this.mainView=view;
    }

    public void intializeRecyclerAdapter() {

        mRecyclerView = (RecyclerView)mainView. findViewById(R.id.rv_answers);
        mAdapter = new AnswersAdapter(mainActivity, new ArrayList<Item>(0), new AnswersAdapter.PostItemListener() {

            @Override
            public void onPostClick(long id) {
                Toast.makeText(mainActivity, "Post id is" + id, Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mainActivity);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mainActivity, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);


    }

    public void loadAnswers() {
        apiUtils =new ApiUtils();
        mService = apiUtils.getSOService();
        mService.getAnswers().enqueue(new Callback<SOAnswersResponse>() {
            @Override
            public void onResponse(Call<SOAnswersResponse> call, Response<SOAnswersResponse> response) {

                if (response.isSuccessful()) {
                    mAdapter.updateAnswers(response.body().getItems());
//                    Log.e("MainActivity", "posts loaded from API" + response.body().getBackoff().toString());
                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {

                Log.d("MainActivity", "error loading from API");

            }
        });
    }

    public void loadCnnUrl() {

        apiUtils = new ApiUtils();
        cnnServiceInterface = apiUtils.getCnnServicesInterface();

        cnnServiceInterface.getPosts().enqueue(new Callback<CnnMainModel>() {
            @Override
            public void onResponse(Call<CnnMainModel> call, Response<CnnMainModel> response) {

                if (response.isSuccessful()) {
//                    mAdapter.updateAnswers(response.body().getItems());
                    Log.e("MainActivity", "posts loaded from API" + response.body().channel.title.toString());
                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<CnnMainModel> call, Throwable t) {

                Log.e("MainActivity", "error loading from API---> " + t.getMessage());

            }
        });
    }
}