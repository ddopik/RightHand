package presenter.netWork.retroFit_interface;

import presenter.netWork.retroFit_models.SOAnswersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * is interface contains methods we are going to use to execute HTTP requests such as GET,
 * POST, PUT, PATCH, and DELETE.
 * For this tutorial, we are going to execute a GET request.
 */

public interface SOService {
    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<SOAnswersResponse> getAnswers();

//    we added a query parameter for us to filter the data from the serve

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<SOAnswersResponse> getAnswers(@Query("tagged") String tags);
}
