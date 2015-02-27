package sunshine.ilv.com.sunshine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by vaibhav on 2/17/15.
 */
public class WeatherForecastListAdapter extends ArrayAdapter<ForecastWeatherModel.ForecastData> {
    private Activity context;
    private List<ForecastWeatherModel.ForecastData> values;

    public WeatherForecastListAdapter(Activity context, List<ForecastWeatherModel.ForecastData> values) {
        super(context, R.layout.week_temp, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listView = inflater.inflate(R.layout.week_temp, parent, false);

        Date dt = new Date(Long.parseLong(values.get(position).dt) * 1000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);

        final int dayName = calendar.get(Calendar.DAY_OF_WEEK);
        final String[] namesOfDays =  {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        TextView day = (TextView) listView.findViewById(R.id.day_name);
        day.setText(namesOfDays[dayName - 1]);

        TextView description = (TextView) listView.findViewById(R.id.weather_description);
        description.setText(values.get(position).weather.get(0).main);

        TextView maxTemp = (TextView) listView.findViewById(R.id.day_max);
        maxTemp.setText(values.get(position).temp.max.toString() + "°");

        TextView minTemp = (TextView) listView.findViewById(R.id.day_min);
        minTemp.setText(values.get(position).temp.min.toString() + "°");

        ImageView weatherPNG = (ImageView) listView.findViewById(R.id.weather_image);
        weatherPNG.setImageResource(Utility.getIconResourceForWeatherCondition(values.get(position).weather.get(0).id));
        //String imageURL = "http://openweathermap.org/img/w/" + values.get(position).weather.get(0).icon +".png";
        //ImageLoader.getInstance().displayImage(imageURL, weatherPNG);

        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                String forecastDataJson = values.get(position).toJson();
                intent.putExtra("FORECAST_DATA_KEY", forecastDataJson);
                context.startActivity(intent);
                Log.v("ILV_DEBUG", "Pushing intent to detailsActivity");
            }
        });

        return listView;

    }
}
