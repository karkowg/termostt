package br.ufsm.inf.gkarkow.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Sensor")
public class Sensor extends Model {

    @Column(name = "Type")
    private String type;
    
    @Column(name = "Model")
    private String model;

    @Column(name = "Pin")
    private Integer pin;

    @Column(name = "Room", onDelete = Column.ForeignKeyAction.SET_NULL)
    private Room room;

    public Sensor() {}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }
}
