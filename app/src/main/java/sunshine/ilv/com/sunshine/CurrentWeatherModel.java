package sunshine.ilv.com.sunshine;

import java.util.List;

/**
 * Created by vaibhav on 2/13/15.
 */
public class CurrentWeatherModel {
    List<Weather> weather;
    Main main;
    String dt;
    public class Main{
        Double humidity;
        Double temp;
        Double temp_max;
        Double temp_min;
    }

    public class Weather {
        String description;
        String main;
        Integer id;
    }
}



