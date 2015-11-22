package br.ufsm.inf.gkarkow.util;

import br.ufsm.inf.gkarkow.model.Room;
import br.ufsm.inf.gkarkow.model.Root;

public class Session {

    public static Root root = null;
    public static Room room = null;
    public static Double met = null;
    public static Double clo = null;
    public static Boolean monitoring = false;
    public static String broker = "192.168.2.200";
    public static Integer port = 1883;
    public static String typeIR = "3";
    public static String powerIR = "BD807F";
    public static String heatIR = "BD30CF";
    public static String coolIR = "BD08F7";
    public static String lengthIR = "32";
    public static String command = null;
}
