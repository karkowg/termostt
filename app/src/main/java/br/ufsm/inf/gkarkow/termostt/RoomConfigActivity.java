package br.ufsm.inf.gkarkow.termostt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import br.ufsm.inf.gkarkow.util.Session;

public class RoomConfigActivity extends AppCompatActivity {

    private EditText nameView, brokerView, portView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_config);

        nameView = (EditText) findViewById(R.id.etRoomName);
        nameView.setText(Session.room == null ? "" : Session.room.getName());
        brokerView = (EditText) findViewById(R.id.etRoomBrokerHost);
        brokerView.setText(Session.broker);
        portView = (EditText) findViewById(R.id.etRoomBrokerPort);
        portView.setText(Session.port.toString());
    }

    public void saveRoom(View view) {
        Session.broker = brokerView.getText().toString();
        Session.port = Integer.parseInt(portView.getText().toString());
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
