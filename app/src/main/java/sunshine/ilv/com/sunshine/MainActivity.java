package sunshine.ilv.com.sunshine;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity {

    private TextView currentDate;
    private TextView currentTemp;
    private TextView currentMin;
    private TextView currentWeatherText;
    private ListView lv;
    private ImageView todayWeatherArt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().setCustomView(R.layout);
        syncWeather();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        if (id == R.id.action_refresh) {
            syncWeather();
        }

        return super.onOptionsItemSelected(item);
    }

    public void syncWeather() {
        currentDate = (TextView) findViewById(R.id.currentDate);
        currentTemp = (TextView) findViewById(R.id.currentTemp);
        currentMin = (TextView) findViewById(R.id.currentMin);
        currentWeatherText = (TextView) findViewById(R.id.currentWeatherText);
        todayWeatherArt = (ImageView) findViewById(R.id.today_image);

        WeatherAPI weatherAPI = APIHandler.getApiInterface();
        weatherAPI.getCurrentWeather(new Callback<CurrentWeatherModel>() {
            public void success(final CurrentWeatherModel currentWeather, Response arg1) {
                Date now = new Date();
                String currentDayInfo = new SimpleDateFormat("EEE, d MMM").format(now);
                currentDate.setText(currentDayInfo);
                String tempPattern = "##.#";
                DecimalFormat decimalFormat = new DecimalFormat(tempPattern);
                currentTemp.setText(decimalFormat.format(currentWeather.main.temp) + "° C");
                Double fahrenheitTemp = (currentWeather.main.temp * (9/5)) + 32;
                currentMin.setText(decimalFormat.format(fahrenheitTemp) + "° F");
                currentWeatherText.setText(currentWeather.weather.get(0).main);
                todayWeatherArt.setImageResource(Utility.getArtResourceForWeatherCondition(currentWeather.weather.get(0).id));
            }
            public void failure(RetrofitError error) {
                Log.e("ERROR MSG", error.toString());
                Toast.makeText(getApplicationContext(), "Oops something went wrong", Toast.LENGTH_LONG).show();
            }
        });
        weatherAPI.getForecastData(new Callback<ForecastWeatherModel>() {
            public void success(ForecastWeatherModel forecastWeatherModel, Response response) {
                lv = (ListView) findViewById(R.id.forecastWeatherList);
                ArrayAdapter<ForecastWeatherModel.ForecastData> weatherForecastListAdapter = new WeatherForecastListAdapter(MainActivity.this, forecastWeatherModel.list);
                lv.setAdapter(weatherForecastListAdapter);
            }

            public void failure(RetrofitError error) {
                Log.e("ERROR MSG", error.toString());
                Toast.makeText(getApplicationContext(), "Oops something went wrong", Toast.LENGTH_LONG).show();
            }
        });
    }


}
