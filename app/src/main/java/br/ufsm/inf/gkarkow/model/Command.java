package br.ufsm.inf.gkarkow.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Command")
public class Command extends Model {

    @Column(name = "Name")
    private String name;

    @Column(name = "Protocol")
    private Integer protocol;

    @Column(name = "Code")
    private Long code;

    @Column(name = "Length")
    private Integer length;

    @Column(name = "AirConditioner", onDelete = Column.ForeignKeyAction.SET_NULL)
    private AirConditioner airConditioner;

    public Command() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public AirConditioner getAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    public Integer getProtocol() {
        return protocol;
    }

    public void setProtocol(Integer protocol) {
        this.protocol = protocol;
    }
}
