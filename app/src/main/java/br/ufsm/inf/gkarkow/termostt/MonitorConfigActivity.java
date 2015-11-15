package br.ufsm.inf.gkarkow.termostt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufsm.inf.gkarkow.util.Session;

public class MonitorConfigActivity extends AppCompatActivity {

    private Spinner spinnerMet;
    private Spinner spinnerClo;
    private Map<String, Double> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_config);

        spinnerMet = (Spinner) findViewById(R.id.spinnerMet);
        spinnerClo = (Spinner) findViewById(R.id.spinnerClo);

        List<String> metList = new ArrayList<>();
        metList.add(getResources().getString(R.string.met_1));
        metList.add(getResources().getString(R.string.met_2));
        metList.add(getResources().getString(R.string.met_3));

        List<String> cloList = new ArrayList<>();
        cloList.add(getResources().getString(R.string.clo_1));
        cloList.add(getResources().getString(R.string.clo_2));
        cloList.add(getResources().getString(R.string.clo_3));
        cloList.add(getResources().getString(R.string.clo_4));

        ArrayAdapter<String> metAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                metList);
        ArrayAdapter<String> cloAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                cloList);

        spinnerMet.setAdapter(metAdapter);
        spinnerClo.setAdapter(cloAdapter);

        initMap();

        spinnerMet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                chosen(0, item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerClo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                chosen(1, item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void chosen(Integer spinner, String item) {
        switch (spinner) {
            case 0:
                Session.met = values.get(item);
                break;
            case 1:
                Session.clo = values.get(item);
                break;
        }
    }

    public void start(View view) {
        Intent intent = new Intent(this, MonitorActivity.class);
        startActivity(intent);
    }

    private void initMap() {
        values = new HashMap<>();
        values.put(getResources().getString(R.string.met_1), 58.2);
        values.put(getResources().getString(R.string.met_2), 116.4);
        values.put(getResources().getString(R.string.met_3), 174.6);
        values.put(getResources().getString(R.string.clo_1), 0.0);
        values.put(getResources().getString(R.string.clo_2), 0.5);
        values.put(getResources().getString(R.string.clo_3), 1.0);
        values.put(getResources().getString(R.string.clo_4), 1.5);
    }
}
