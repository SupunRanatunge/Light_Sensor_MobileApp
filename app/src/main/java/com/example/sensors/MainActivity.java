package com.example.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener {
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private TextView text;
    private Button button;
    private ProgressBar progressBar;
    private boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        text = (TextView)findViewById(R.id.textView);

        progressBar = (ProgressBar)findViewById(R.id.progressbarID);

        button = (Button)findViewById(R.id.Start_1);
        button.setOnClickListener(this);
        button.setText("Start");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        text.setText("LIGHT: " + event.values[0]);
        //progressBar.setProgress(((event.values[0]/35000 * 100) > 100) ? 100 : (int)(event.values[0]/35000 * 100));
        progressBar.setProgress((int)event.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onClick(View v) {
        if(running) {
            running = false;
            button.setText("Start");
            sensorManager.unregisterListener(this);                 //unregistering the listener


        } else {
            running = true;
            button.setText("Stop");
            sensorManager.registerListener(this,lightSensor,SensorManager.SENSOR_DELAY_NORMAL);  //registering the listener

        }

    }
}
