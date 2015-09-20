package br.ufsm.inf.gkarkow.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Command")
public class Command extends Model {

    @Column(name = "Name")
    private String name;

    @Column(name = "InfraredCode")
    private Long infraredCode;

    @Column(name = "AirConditioner", onDelete = Column.ForeignKeyAction.SET_NULL)
    private AirConditioner airConditioner;

    public Command() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getInfraredCode() {
        return infraredCode;
    }

    public void setInfraredCode(Long infraredCode) {
        this.infraredCode = infraredCode;
    }

    public AirConditioner getAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }
}
