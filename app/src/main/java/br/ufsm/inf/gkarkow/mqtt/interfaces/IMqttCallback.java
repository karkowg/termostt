package br.ufsm.inf.gkarkow.mqtt.interfaces;

public interface IMqttCallback
{
	public void messageArrived(IMqttTopic topic, IMqttMessage message) throws Exception;
	public void connectionLost(Throwable throwable);
}
