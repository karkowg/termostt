package br.ufsm.inf.gkarkow.termostt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class RoomConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_config);
    }

    public void saveRoom(View view) {

    }

    public void ac(View view) {
        Intent intent = new Intent(this, AirConfigActivity.class);
        startActivity(intent);
    }

    public void sensors(View view) {
        Intent intent = new Intent(this, SensorsActivity.class);
        startActivity(intent);
    }
}
