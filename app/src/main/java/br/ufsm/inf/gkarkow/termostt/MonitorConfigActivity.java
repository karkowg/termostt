package br.ufsm.inf.gkarkow.termostt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MonitorConfigActivity extends AppCompatActivity {

    private Spinner spinnerMet;
    private Spinner spinnerClo;

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
    }

    public void start(View view) {
        Intent intent = new Intent(this, MonitorActivity.class);
        startActivity(intent);
    }
}
