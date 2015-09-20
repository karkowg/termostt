package br.ufsm.inf.gkarkow.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Broker")
public class Broker extends Model {

    @Column(name = "Name")
    private String name;

    @Column(name = "Hostname")
    private String hostname;

    @Column(name = "Port")
    private Integer port;

    @Column(name = "Room", onDelete = Column.ForeignKeyAction.SET_NULL)
    private Room room;

    public Broker() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
