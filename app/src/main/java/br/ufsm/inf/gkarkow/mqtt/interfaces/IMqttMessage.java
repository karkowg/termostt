package br.ufsm.inf.gkarkow.mqtt.interfaces;

import br.ufsm.inf.gkarkow.mqtt.impl.MqttException;

public interface IMqttMessage
{
	public int getQoS();
	public byte[] getPayload() throws MqttException;
	public boolean isRetained();	
	public boolean isDuplicate();
}
