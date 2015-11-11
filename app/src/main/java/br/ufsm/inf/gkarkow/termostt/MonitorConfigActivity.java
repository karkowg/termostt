package br.ufsm.inf.gkarkow.termostt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        metList.add("Lazy");
        metList.add("Regular");
        metList.add("Heavy");

        List<String> cloList = new ArrayList<>();
        cloList.add("Nude");
        cloList.add("Summer");
        cloList.add("Spring/Autumn");
        cloList.add("Winter");

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

        /*
        spinnerMet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                chosen(0, item);
            }
        });

        spinnerClo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                chosen(1, item);
            }
        });
        */

        initMap();
    }

    private void chosen(Integer spinner, String item) {
        switch (spinner) {
            case 0:
                //Session.met = values.get(item);
                break;
            case 1:
                //Session.clo = values.get(item);
                break;
        }
    }

    public void start(View view) {
        Intent intent = new Intent(this, MonitorActivity.class);
        startActivity(intent);
    }

    private void initMap() {
        values = new HashMap<>();
        values.put("Lazy", 58.2);
        values.put("Regular", 116.4);
        values.put("Heavy", 174.6);
        values.put("Nude", 0.0);
        values.put("Summer", 0.5);
        values.put("Spring/Autumn", 1.0);
        values.put("Winter", 1.5);
    }
}
