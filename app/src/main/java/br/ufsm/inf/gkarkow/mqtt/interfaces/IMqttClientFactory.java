package br.ufsm.inf.gkarkow.mqtt.interfaces;

import br.ufsm.inf.gkarkow.mqtt.impl.MqttException;

public interface IMqttClientFactory
{
	public IMqttClient create(String host, int port, String clientId, IMqttPersistence persistence) throws MqttException;
}
