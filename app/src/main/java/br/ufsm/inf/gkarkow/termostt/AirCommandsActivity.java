package br.ufsm.inf.gkarkow.termostt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import br.ufsm.inf.gkarkow.util.Session;

public class AirCommandsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_commands);
    }

    public void onOffCommand(View view) {
        Session.command = "on/off";
        Intent intent = new Intent(this, CommandConfigActivity.class);
        startActivity(intent);
    }

    public void upCommand(View view) {
        Session.command = "+";
        Intent intent = new Intent(this, CommandConfigActivity.class);
        startActivity(intent);
    }

    public void downCommand(View view) {
        Session.command = "-";
        Intent intent = new Intent(this, CommandConfigActivity.class);
        startActivity(intent);
    }
}
