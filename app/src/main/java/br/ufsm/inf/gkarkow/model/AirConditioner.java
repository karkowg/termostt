package br.ufsm.inf.gkarkow.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.HashMap;
import java.util.List;

@Table(name = "AirConditioner")
public class AirConditioner extends Model {

    @Column(name = "Brand")
    private String brand;

    @Column(name = "Model")
    private String model;

    @Column(name = "Room", onDelete = Column.ForeignKeyAction.SET_NULL)
    private Room room;

    private HashMap<String, Long> commands;

    public AirConditioner() {}

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public HashMap<String, Long> getCommands() {
        commands = new HashMap<>();
        for (Command c: commandList()) {
            commands.put(c.getName(), c.getInfraredCode());
        }
        return commands;
    }

    public void setCommands(HashMap<String, Long> commands) {
        this.commands = commands;
    }

    private List<Command> commandList() {
        return getMany(Command.class, "AirConditioner");
    }
}
