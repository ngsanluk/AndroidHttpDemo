package hk.com.sunnyng.androidhttpdemo;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "AndroidHttpDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void loadContent(View v) {
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText("Loading ...");
        try {
            URL url = new URL("https://www.als.ogcio.gov.hk/lookup?q=fortress%20tower");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream is = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String data = "";
            tv.setText("");
            while ((data = reader.readLine()) != null){
                tv.append(data + "\n" );
            }
        }
        catch (Exception e) {
            Log.e(TAG, "loadContent: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
