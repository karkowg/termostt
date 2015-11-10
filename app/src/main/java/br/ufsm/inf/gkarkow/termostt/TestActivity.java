package br.ufsm.inf.gkarkow.termostt;

import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.util.Date;

import br.ufsm.inf.gkarkow.mqtt.MqttServiceDelegate;
import br.ufsm.inf.gkarkow.mqtt.MqttServiceDelegate.MessageHandler;
import br.ufsm.inf.gkarkow.mqtt.MqttServiceDelegate.MessageReceiver;
import br.ufsm.inf.gkarkow.mqtt.MqttServiceDelegate.StatusHandler;
import br.ufsm.inf.gkarkow.mqtt.MqttServiceDelegate.StatusReceiver;
import br.ufsm.inf.gkarkow.mqtt.service.MqttService;
import br.ufsm.inf.gkarkow.mqtt.service.MqttService.ConnectionStatus;

public class TestActivity extends AppCompatActivity implements MessageHandler, StatusHandler {

    private static final Logger LOG = Logger.getLogger(TestActivity.class);

    private MessageReceiver msgReceiver;
    private StatusReceiver statusReceiver;

    private TextView timestampView, topicView, messageView, statusView;

    private Button publishButtonOn;
    private Button publishButtonOff;
    private Button temperatureButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        LOG.debug("onCreate");

        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences(MqttService.APP_ID, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("broker", "192.168.2.200");
        editor.apply();

        //Init UI
        setContentView(R.layout.activity_test);

        timestampView = (TextView)findViewById(R.id.timestampView);
        topicView = (TextView)findViewById(R.id.topicView);
        messageView = (TextView)findViewById(R.id.messageView);
        statusView = (TextView)findViewById(R.id.statusView);

        publishButtonOn = (Button)findViewById(R.id.publishButtonOn);
        publishButtonOff = (Button)findViewById(R.id.publishButtonOff);
        temperatureButton = (Button)findViewById(R.id.temperatureButton);

        publishButtonOn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MqttServiceDelegate.publish(
                        TestActivity.this,
                        "termostt/mode",
                        "mode:DATA".getBytes());
            }
        });

        publishButtonOff.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MqttServiceDelegate.publish(
                        TestActivity.this,
                        "termostt/mode",
                        "mode:MONITOR".getBytes());
            }
        });

        temperatureButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MqttServiceDelegate.publish(
                        TestActivity.this,
                        "termostt/mode",
                        "mode:IDLE".getBytes());
                MqttServiceDelegate.publish(
                        TestActivity.this,
                        "termostt/infrared",
                        "IR:type:3".getBytes());
                MqttServiceDelegate.publish(
                        TestActivity.this,
                        "termostt/infrared",
                        "IR:value:BD30CF".getBytes());
                MqttServiceDelegate.publish(
                        TestActivity.this,
                        "termostt/infrared",
                        "IR:len:32".getBytes());
            }
        });

        //Init Receivers
        bindStatusReceiver();
        bindMessageReceiver();

        //Start service if not started
        MqttServiceDelegate.startService(this);
    }

    @Override
    protected void onDestroy() {
        LOG.debug("onDestroy");

        MqttServiceDelegate.stopService(this);

        unbindMessageReceiver();
        unbindStatusReceiver();

        super.onDestroy();
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

    private String getCurrentTimestamp(){
        return new Timestamp(new Date().getTime()).toString();
    }

    @Override
    public void handleMessage(String topic, byte[] payload) {
        String message = new String(payload);

        LOG.debug("handleMessage: topic="+topic+", message="+message);

        if(timestampView != null)timestampView.setText("When: "+getCurrentTimestamp());
        if(topicView != null)topicView.setText("Topic: "+topic);
        if(messageView != null)messageView.setText("Message: "+message);
    }

    @Override
    public void handleStatus(ConnectionStatus status, String reason) {
        LOG.debug("handleStatus: status="+status+", reason="+reason);
        if(statusView != null)statusView.setText("Status: "+status.toString()+" ("+reason+")");
    }
}
