package presenter.netWork;

import com.example.networkmodule.singletonNetWork.RetrofitSingleton;

import presenter.netWork.retroFit_interface.SOService;

/**
 * Created by ddopik on 9/9/2017.
 * Now are going to create a utility class.
 * This class will have the base URL as a static variable and also provide the SOService
 */

public class ApiUtils {

    public static final String BASE_URL = "https://api.stackexchange.com/2.2/";

    //    RetrofitClient is Static class that provide A Singltone instance of retrofit
    public static SOService getSOService() {
        return RetrofitSingleton.getClient(BASE_URL).create(SOService.class);
    }





}
