package com.ikota.reactivesample.ui.signal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ikota.reactivesample.R;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private Signal mCountSignal = new Signal(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView)findViewById(R.id.text);

        findViewById(R.id.countup_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCountSignal.increment();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mCountSignal.register(new Signal.OnValueChangeListener() {
            @Override
            public void onReceiveChange(int before_val, int new_val) {
                mTextView.setText(String.valueOf(new_val));
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        mCountSignal.unregister();
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
            Intent intent = new Intent(MainActivity.this, LooperHandlerSampleActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
