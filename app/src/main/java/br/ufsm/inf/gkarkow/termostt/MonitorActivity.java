package br.ufsm.inf.gkarkow.termostt;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
import br.ufsm.inf.gkarkow.util.Session;

public class MonitorActivity extends AppCompatActivity implements MessageHandler, StatusHandler {

    private MessageReceiver msgReceiver;
    private StatusReceiver statusReceiver;

    private TextView roomView, temperatureView, targetView, acView, humidityView, pmvView, sensationView, connView;
    private Button btAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);

        temperatureView = (TextView) findViewById(R.id.tvTemperatureValue);
        humidityView = (TextView) findViewById(R.id.tvHumidityValue);
        pmvView = (TextView) findViewById(R.id.tvPMVValue);
        sensationView = (TextView) findViewById(R.id.tvSensationValue);
        targetView = (TextView) findViewById(R.id.tvTempTargetValue);
        acView = (TextView) findViewById(R.id.tvTempACValue);
        connView = (TextView) findViewById(R.id.tvConnStatus);

        roomView = (TextView) findViewById(R.id.tvRoomLabel);
        roomView.setText(Session.room.getName());
        btAction = (Button) findViewById(R.id.btMonitorAction);
        btAction.setText(getResources().getString(R.string.start));

        //Init Receivers
        bindStatusReceiver();
        bindMessageReceiver();

        //Start service if not started
        MqttServiceDelegate.startService(this);

        publishParams();
    }

    private void publishParams() {
        String metParam = "met:" + Session.met;
        String cloParam = "clo:" + Session.clo;
        MqttServiceDelegate.publish(
                MonitorActivity.this,
                "termostt/params",
                metParam.getBytes());
        MqttServiceDelegate.publish(
                MonitorActivity.this,
                "termostt/params",
                cloParam.getBytes());
    }

    private void startMonitoring() {
        Session.monitoring = true;
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

    public void action(View view) {
        if (btAction.getText().equals(getResources().getString(R.string.start))) {
            startMonitoring();
            btAction.setText(getResources().getString(R.string.stop));
        }
        else if (btAction.getText().equals(getResources().getString(R.string.stop))) {
            MqttServiceDelegate.publish(MonitorActivity.this, "termostt/mode", "mode:IDLE".getBytes());
            Session.monitoring = false;
            btAction.setText(getResources().getString(R.string.finish));
        }
        else if (btAction.getText().equals(getResources().getString(R.string.finish))) {
            Session.room = null;
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
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
        final String NOW = "termostt/status/temperature/now";
        final String TARGET = "termostt/status/temperature/target";
        final String AC = "termostt/status/temperature/ac";
        final String RH = "termostt/status/humidity";
        final String PMV = "termostt/status/pmv";

        switch (topic) {
            case NOW:
                if (temperatureView != null) temperatureView.setText(message + " ºC");
                break;
            case TARGET:
                if (targetView != null) targetView.setText(message + " ºC");
                break;
            case AC:
                if (acView != null) acView.setText(message + " ºC");
                break;
            case RH:
                if (humidityView != null) humidityView.setText(message + "%");
                break;
            case PMV:
                if (pmvView != null) pmvView.setText(message);
                if (sensationView != null) sensationView.setText(getSensation(message));
                break;
        }
    }

    private String getSensation(String pmvString) {
        double pmv = Double.parseDouble(pmvString);
        if (pmv <= -2) {
            sensationView.setTextColor(Color.rgb(0, 108, 140));
            return getResources().getString(R.string.scale_1);
        }
        if (pmv <= -1) {
            sensationView.setTextColor(Color.rgb(0, 196, 255));
            return getResources().getString(R.string.scale_2);
        }
        if (pmv <= 1) {
            sensationView.setTextColor(getResources().getColor(R.color.material_deep_teal_500));
            return getResources().getString(R.string.scale_3);
        }
        if (pmv <= 2) {
            sensationView.setTextColor(Color.rgb(255, 129, 90));
            return getResources().getString(R.string.scale_4);
        }
        sensationView.setTextColor(getResources().getColor(R.color.heat));
        return getResources().getString(R.string.scale_5);
    }

    private String getCurrentTimestamp(){
        return new Timestamp(new Date().getTime()).toString();
    }

    @Override
    public void handleStatus(ConnectionStatus status, String reason) {
        String connStatus = getResources().getString(R.string.broker).toUpperCase() + " " + status.toString();
        if (connView != null) connView.setText(connStatus);
    }
}
