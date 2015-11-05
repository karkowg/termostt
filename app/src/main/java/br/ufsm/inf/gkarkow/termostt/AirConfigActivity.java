package br.ufsm.inf.gkarkow.termostt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AirConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_config);
    }

    public void saveAC(View view) {

    }

    public void commands(View view) {
        Intent intent = new Intent(this, AirCommandsActivity.class);
        startActivity(intent);
    }
}
