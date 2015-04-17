package info.ciolek.bookweb.network;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

/**
 * Created by michael on 10.04.15.
 */
public class ApiService extends RetrofitGsonSpiceService {

    private final static String BASE_URL = "http://bookweb.ciolek.info/api/v1";

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(ApiInterface.class);
    }

    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }
}