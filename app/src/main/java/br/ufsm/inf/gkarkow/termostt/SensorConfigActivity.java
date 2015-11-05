package br.ufsm.inf.gkarkow.termostt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SensorConfigActivity extends AppCompatActivity {

    private Spinner spinnerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_config);

        spinnerType = (Spinner) findViewById(R.id.spinnerSensorType);

        List<String> typeList = new ArrayList<>();
        typeList.add("Temperature/Humidity");
        typeList.add("Luminosity");

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                typeList);

        spinnerType.setAdapter(typeAdapter);
    }

    public void saveSensor(View view) {

    }
}
