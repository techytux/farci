package sunshine.ilv.com.sunshine;

import retrofit.Callback;
import retrofit.http.GET;
/**
 * Created by vaibhav on 2/13/15.
 */
public interface WeatherAPI {

    @GET("/weather?q=Berlin&units=metric")
    void getCurrentWeather(Callback<CurrentWeatherModel> callback);

    @GET("/forecast/daily?q=Berlin&units=metric&cnt=7")
    void getForecastData(Callback<ForecastWeatherModel> callback);
}
