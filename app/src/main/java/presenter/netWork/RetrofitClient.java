package presenter.netWork;

import retrofit2.Retrofit;
import retrofit2.SimpleXmlConverterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 *
 * To issue network requests to a REST API with Retrofit,we need to create an instance using the Retrofit.Builder class
 * and configure it with a ' base URL '  as Retrofit needs a base URL to build its instance,
 * This class will create a singleton of Retrofit.
 * so we will pass a URL when calling RetrofitClient.getClient(String baseUrl).
 * This URL will then be used to build the instance .
 * We are also specifying the JSON converter we need
 */

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static Retrofit getXmlClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
