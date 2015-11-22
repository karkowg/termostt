package br.ufsm.inf.gkarkow.termostt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import br.ufsm.inf.gkarkow.util.Session;

public class CommandConfigActivity extends AppCompatActivity {

    private final String POWER = "on/off";
    private final String HEAT = "+";
    private final String COOL = "-";

    private TextView nameView;
    private EditText protocolView, codeView, lengthView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command_config);

        nameView = (TextView) findViewById(R.id.tvCommandNameValue);
        nameView.setText(Session.command);
        protocolView = (EditText) findViewById(R.id.etCommandProtocolValue);
        protocolView.setText(Session.typeIR);
        codeView = (EditText) findViewById(R.id.etCommandCodeValue);

        String command = Session.command;
        switch (command) {
            case POWER:
                codeView.setText(Session.powerIR);
                break;
            case HEAT:
                codeView.setText(Session.heatIR);
                break;
            case COOL:
                codeView.setText(Session.coolIR);
                break;
        }

        lengthView = (EditText) findViewById(R.id.etCommandLengthValue);
        lengthView.setText(Session.lengthIR);
    }

    public void saveCommand(View view) {
        String command = Session.command;
        switch (command) {
            case POWER:
                Session.powerIR = codeView.getText().toString();
                break;
            case HEAT:
                Session.heatIR = codeView.getText().toString();
                break;
            case COOL:
                Session.coolIR = codeView.getText().toString();
                break;
        }
    }
}
