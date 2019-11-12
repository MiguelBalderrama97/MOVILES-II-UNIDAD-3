package com.example.miguel.eva3_1_sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private TextView txtInfo;

    private String[] sensorArray;
    private SensorManager sensorManager;
    private List<Sensor> sensorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        txtInfo = findViewById(R.id.txtInfo);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        sensorArray = new String[sensorList.size()];

        int i = 0;
        for(Sensor sensor : sensorList){
            sensorArray[i] = sensor.getName();
            i++;
        }

        listView.setAdapter(new ArrayAdapter<String>(
                getApplicationContext(),android.R.layout.simple_list_item_1,sensorArray
        ));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Sensor sensor = sensorList.get(i);
                txtInfo.setText("Rango maxiomo: " + sensor.getMaximumRange() + "\n" +
                        "Demora: " + sensor.getMaxDelay() + "\n" +
                        "Consumo energia: " + sensor.getPower() + "\n" +
                        "Precisión: " + sensor.getResolution());
            }
        });
    }
}
