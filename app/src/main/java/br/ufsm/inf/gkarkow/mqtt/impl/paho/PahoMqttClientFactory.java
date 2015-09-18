package br.ufsm.inf.gkarkow.mqtt.impl.paho;

import br.ufsm.inf.gkarkow.mqtt.impl.MqttException;
import br.ufsm.inf.gkarkow.mqtt.interfaces.IMqttClient;
import br.ufsm.inf.gkarkow.mqtt.interfaces.IMqttClientFactory;
import br.ufsm.inf.gkarkow.mqtt.interfaces.IMqttPersistence;

public class PahoMqttClientFactory implements IMqttClientFactory
{	
	@Override
	public IMqttClient create(String host, int port, String clientId,
		IMqttPersistence persistence) throws MqttException
	{
		PahoMqttClientPersistence persistenceImpl = null;
		if(persistence != null){
			persistenceImpl = new PahoMqttClientPersistence(persistence);
		}
		
		// TODO Auto-generated method stub
		return new PahoMqttClientWrapper(
			"tcp://"+host+":"+port, clientId, persistenceImpl);
	}
}
