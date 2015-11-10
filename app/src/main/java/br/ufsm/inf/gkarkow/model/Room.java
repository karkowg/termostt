package br.ufsm.inf.gkarkow.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = "Room")
public class Room extends Model {

    @Column(name = "Name")
    private String name;

    @Column(name = "AirConditioner", onDelete = Column.ForeignKeyAction.SET_NULL)
    private AirConditioner airConditioner;

    @Column(name = "Broker", onDelete = Column.ForeignKeyAction.SET_NULL)
    private Broker broker;

    private List<Sensor> sensors;

    public Room() {}

    public Room(String name) {
        this.name = name;
        this.airConditioner = null;
        this.broker = null;
        this.sensors = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AirConditioner getAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    public Broker getBroker() {
        return broker;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    public List<Sensor> getSensors() {
        return getMany(Sensor.class, "Room");
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }
}
