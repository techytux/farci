package sunshine.ilv.com.sunshine;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by vaibhav on 2/13/15.
 */
public class ForecastWeatherModel {

    Temp temp;
    Weather weather;
    List<ForecastData> list;
    ForecastData forecastData;

    public class Weather {
        Integer id;
        String main ;
        String description ;
        String icon;
    }

    public class Temp {
        Double day ;
        Double min ;
        Double max ;
        Double night ;
        Double eve ;
        Double morn ;
    }

    public class ForecastData {
        String dt ;
        Temp temp ;
        Integer humidity;
        Double pressure;
        Double speed;
        List<Weather> weather;

        public String toJson() {
            return new Gson().toJson(ForecastData.this);
        }
    }

    public static ForecastData fromJson(String json) {
        return new Gson().fromJson(json, ForecastData.class);
    }

}
