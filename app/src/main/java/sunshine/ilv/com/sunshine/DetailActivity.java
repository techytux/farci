package sunshine.ilv.com.sunshine;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonSerializer;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        String forecastJSONStr = getIntent().getExtras().getString("FORECAST_DATA_KEY");
        ForecastWeatherModel.ForecastData forecastData = ForecastWeatherModel.fromJson(forecastJSONStr);



        String tempPattern = "##.#";
        DecimalFormat decimalFormat = new DecimalFormat(tempPattern);

        Date date = new Date(Long.parseLong(forecastData.dt) * 1000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        final int dayName = calendar.get(Calendar.DAY_OF_WEEK);
        final String[] namesOfDays =  {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String currentDayInfo = new SimpleDateFormat("d MMM", Locale.ENGLISH).format(date);

        TextView detailTextView = (TextView) findViewById(R.id.detail_day_textview);

        Log.d(DetailActivity.class.getName(), "DayName [" + namesOfDays[dayName - 1] + "]");
        detailTextView.setText(namesOfDays[dayName - 1]);

        TextView detail_date_textview = (TextView) findViewById(R.id.detail_date_textview);
        detail_date_textview.setText(currentDayInfo);

        TextView detail_high_textview = (TextView) findViewById(R.id.detail_high_textview);
        detail_high_textview.setText(decimalFormat.format(forecastData.temp.max) + "°");


        TextView detail_low_textview = (TextView) findViewById(R.id.detail_low_textview);
        detail_low_textview.setText(decimalFormat.format(forecastData.temp.min) + "°");

        ImageView detail_icon = (ImageView) findViewById(R.id.detail_icon);
        detail_icon.setImageResource(Utility.getArtResourceForWeatherCondition(forecastData.weather.get(0).id));

        TextView detail_forecast_textview = (TextView) findViewById(R.id.detail_forecast_textview);
        detail_forecast_textview.setText(forecastData.weather.get(0).main);

        TextView detail_humidity_textview = (TextView) findViewById(R.id.detail_humidity_textview);
        detail_humidity_textview.setText("Humidity:" + forecastData.humidity.toString() + "%");

        TextView detail_pressure_textview = (TextView) findViewById(R.id.detail_pressure_textview);
        detail_pressure_textview.setText("Pressure:" + decimalFormat.format(forecastData.pressure) + "hPa");

        TextView detail_wind_textview = (TextView) findViewById(R.id.detail_wind_textview);
        detail_wind_textview.setText("Wind:" + decimalFormat.format(forecastData.speed) + "km/h");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_detail, container, false);
            return rootView;
        }
    }
}
