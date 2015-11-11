package br.ufsm.inf.gkarkow.termostt;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.Date;

import br.ufsm.inf.gkarkow.mqtt.MqttServiceDelegate;
import br.ufsm.inf.gkarkow.mqtt.MqttServiceDelegate.MessageHandler;
import br.ufsm.inf.gkarkow.mqtt.MqttServiceDelegate.MessageReceiver;
import br.ufsm.inf.gkarkow.mqtt.MqttServiceDelegate.StatusHandler;
import br.ufsm.inf.gkarkow.mqtt.MqttServiceDelegate.StatusReceiver;
import br.ufsm.inf.gkarkow.mqtt.service.MqttService;
import br.ufsm.inf.gkarkow.mqtt.service.MqttService.ConnectionStatus;

public class MonitorActivity extends AppCompatActivity implements MessageHandler, StatusHandler {

    private MessageReceiver msgReceiver;
    private StatusReceiver statusReceiver;

    private TextView temperatureView, humidityView, pmvView, statusView, connView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);

        temperatureView = (TextView) findViewById(R.id.tvTemperatureValue);
        humidityView = (TextView) findViewById(R.id.tvHumidityValue);
        pmvView = (TextView) findViewById(R.id.tvPMVValue);
        statusView = (TextView) findViewById(R.id.tvStatusValue);
        connView = (TextView) findViewById(R.id.tvConnStatus);

        //Init Receivers
        bindStatusReceiver();
        bindMessageReceiver();

        //Start service if not started
        MqttServiceDelegate.startService(this);

        publishParams();
    }

    private void publishParams() {
        String metParam = "met:" + 58.2;//Session.met;
        String cloParam = "clo:" + 1.0; //Session.clo;
        MqttServiceDelegate.publish(
                MonitorActivity.this,
                "termostt/params",
                metParam.getBytes());
        MqttServiceDelegate.publish(
                MonitorActivity.this,
                "termostt/params",
                cloParam.getBytes());
        MqttServiceDelegate.publish(
                MonitorActivity.this,
                "termostt/mode",
                "mode:MONITOR".getBytes());
    }

    private void bindMessageReceiver() {
        msgReceiver = new MessageReceiver();
        msgReceiver.registerHandler(this);
        registerReceiver(msgReceiver,
                new IntentFilter(MqttService.MQTT_MSG_RECEIVED_INTENT));
    }

    private void unbindMessageReceiver() {
        if(msgReceiver != null){
            msgReceiver.unregisterHandler(this);
            unregisterReceiver(msgReceiver);
            msgReceiver = null;
        }
    }

    private void bindStatusReceiver() {
        statusReceiver = new StatusReceiver();
        statusReceiver.registerHandler(this);
        registerReceiver(statusReceiver,
                new IntentFilter(MqttService.MQTT_STATUS_INTENT));
    }

    private void unbindStatusReceiver() {
        if(statusReceiver != null){
            statusReceiver.unregisterHandler(this);
            unregisterReceiver(statusReceiver);
            statusReceiver = null;
        }
    }

    public void stop(View view) {
        MqttServiceDelegate.publish(MonitorActivity.this, "termostt/mode", "mode:IDLE".getBytes());
    }

    @Override
    protected void onDestroy() {
        MqttServiceDelegate.stopService(this);

        unbindMessageReceiver();
        unbindStatusReceiver();

        super.onDestroy();
    }

    @Override
    public void handleMessage(String topic, byte[] payload) {
        String message = new String(payload);
        final String T = "termostt/status/temperature";
        final String H = "termostt/status/humidity";
        final String P = "termostt/status/pmv";
        final String S = "termostt/status/feel";

        switch (topic) {
            case T:
                if (temperatureView != null) temperatureView.setText(message + " ºC");
                break;
            case H:
                if (humidityView != null) humidityView.setText(message + "%");
                break;
            case P:
                if (pmvView != null) pmvView.setText(message);
                break;
            case S:
                if (statusView != null) statusView.setText(message);
                break;
        }
    }

    private String getCurrentTimestamp(){
        return new Timestamp(new Date().getTime()).toString();
    }

    @Override
    public void handleStatus(ConnectionStatus status, String reason) {
        if (connView != null) connView.setText(status.toString() + " ("+reason+") at " + getCurrentTimestamp());
    }
}
