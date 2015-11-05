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

public class MainActivity extends AppCompatActivity {

    private ListView lvRooms;
    private List<String> roomsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvRooms = (ListView) findViewById(R.id.lvRooms);
        roomsList = new ArrayList<>();

        roomsList.add("Office");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                roomsList);

        lvRooms.setAdapter(arrayAdapter);

        registerForContextMenu(lvRooms);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        String title = roomsList.get(info.position);
        menu.setHeaderTitle(title);

        menu.add(Menu.NONE, 1, Menu.NONE, "Monitor");
        menu.add(Menu.NONE, 2, Menu.NONE, "Configure");
        menu.add(Menu.NONE, 3, Menu.NONE, "Remove");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1: monitorRoom();
                return true;
            case 2: configRoom();
                return true;
            case 3: removeRoom();
            default: return true;
        }
    }

    public void newRoom(View view) {
        Intent intent = new Intent(this, RoomConfigActivity.class);
        startActivity(intent);
    }

    private void configRoom() {
        Intent intent = new Intent(this, RoomConfigActivity.class);
        startActivity(intent);
    }

    private void monitorRoom() {
        Intent intent = new Intent(this, MonitorConfigActivity.class);
        startActivity(intent);
    }

    private void removeRoom() {

    }

    /*
    public void mqtt(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }
    */
}
