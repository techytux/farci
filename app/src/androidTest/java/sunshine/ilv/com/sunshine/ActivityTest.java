package sunshine.ilv.com.sunshine;

import android.widget.TextView;

/**
 * Created by vaibhav on 2/24/15.
 */
public class ActivityTest extends android.test.ActivityUnitTestCase<MainActivity> {

    private MainActivity mainActivity;
    public ActivityTest() {
        super(MainActivity.class);
    }
    private TextView mainWeatherView;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mainActivity = getActivity();
        mainWeatherView = (TextView) mainActivity.findViewById(R.id.currentDate);

    }

    public void aTest() {
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainWeatherView.requestFocus();
            }
        });
        assertEquals("True", "True");
    }
}
