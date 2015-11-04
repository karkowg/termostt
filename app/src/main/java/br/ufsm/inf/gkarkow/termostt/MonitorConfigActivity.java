package br.ufsm.inf.gkarkow.termostt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
        metList.add("Sedentário");
        metList.add("Médio");
        metList.add("Pesado");

        List<String> cloList = new ArrayList<>();
        cloList.add("Roupas de verão");
        cloList.add("Roupas de inverno");

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_monitor_config, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
