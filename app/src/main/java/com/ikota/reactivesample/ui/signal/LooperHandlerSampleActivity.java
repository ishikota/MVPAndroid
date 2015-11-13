package com.ikota.reactivesample.ui.signal;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;

import com.ikota.reactivesample.R;

/**
 * Created by kota on 2015/11/13.
 */
public class LooperHandlerSampleActivity extends Activity {
    private TextView mTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView)findViewById(R.id.text);
        new SomeLongTask().execute();
    }

    class SomeLongTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            Log.i("LooperHandlerSample", "Long task starts");
            SystemClock.sleep(3000);
            Log.i("LooperHandlerSample", "Long task ends");
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mTextView.setText("Yahoo!!");
                }
            });
            return null;
        }
    }

}
