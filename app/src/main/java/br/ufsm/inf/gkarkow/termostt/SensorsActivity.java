package br.ufsm.inf.gkarkow.termostt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SensorsActivity extends AppCompatActivity {

    private ListView lvSensors;
    private List<String> sensorsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        lvSensors = (ListView) findViewById(R.id.lvSensors);
        sensorsList = new ArrayList<>();

        sensorsList.add("Temperature/Humidity");
        sensorsList.add("Luminosity");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                sensorsList);

        lvSensors.setAdapter(arrayAdapter);

        registerForContextMenu(lvSensors);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        String title = sensorsList.get(info.position);
        menu.setHeaderTitle(title);

        menu.add(Menu.NONE, 1, Menu.NONE, "Configure");
        menu.add(Menu.NONE, 2, Menu.NONE, "Remove");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1: configSensor();
                return true;
            case 2: removeSensor();
                return true;
            default: return true;
        }
    }

    public void newSensor(View view) {
        Intent intent = new Intent(this, SensorConfigActivity.class);
        startActivity(intent);
    }

    private void configSensor() {
        Intent intent = new Intent(this, SensorConfigActivity.class);
        startActivity(intent);
    }

    private void removeSensor() {

    }
}
