package br.ufsm.inf.gkarkow.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = "Root")
public class Root extends Model {

    @Column(name = "Username")
    private String username;

    private List<Room> rooms;

    public Root() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Room> getRooms() {
        return getMany(Room.class, "Root");
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
