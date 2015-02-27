package sunshine.ilv.com.sunshine;

import retrofit.RestAdapter;

/**
 * Created by vaibhav on 2/13/15.
 */
public class APIHandler {
    private static final String API_URL = "http://api.openweathermap.org/data/2.5";
    private static RestAdapter restAdapter;

    private static RestAdapter getRestAdapter(){
        if(restAdapter==null){
            restAdapter = new RestAdapter.Builder()
                    .setEndpoint(API_URL)
                    .build();
        }
        return restAdapter;
    }

    public static WeatherAPI getApiInterface(){

        // Create an instance of our  API interface.
        WeatherAPI weatherAPI =null;
        try {
            if(restAdapter==null){
                restAdapter=getRestAdapter();
            }
            weatherAPI = restAdapter.create(WeatherAPI.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weatherAPI;
    }

}
