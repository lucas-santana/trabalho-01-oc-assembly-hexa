import java.util.ArrayList;

public class Registradores {
    ArrayList<Registrador> registradores;

    public Registradores() {
        this.registradores = new ArrayList<Registrador>();
        this.registradores.add(new Registrador("$zero", "0"));

        this.registradores.add(new Registrador("$t0", "8"));
        this.registradores.add(new Registrador("$t1", "9"));
        this.registradores.add(new Registrador("$t2", "10"));
        this.registradores.add(new Registrador("$t3", "11"));
        this.registradores.add(new Registrador("$t4", "12"));
        this.registradores.add(new Registrador("$t5", "13"));
        this.registradores.add(new Registrador("$t6", "14"));
        this.registradores.add(new Registrador("$t7", "15"));

        this.registradores.add(new Registrador("$s0", "16"));
        this.registradores.add(new Registrador("$s1", "17"));
        this.registradores.add(new Registrador("$s2", "18"));
        this.registradores.add(new Registrador("$s3", "19"));
        this.registradores.add(new Registrador("$s4", "20"));
        this.registradores.add(new Registrador("$s5", "21"));
        this.registradores.add(new Registrador("$s6", "22"));
        this.registradores.add(new Registrador("$s7", "23"));

        this.registradores.add(new Registrador("$t8", "24"));
        this.registradores.add(new Registrador("$t9", "25"));
    }

    public ArrayList<Registrador> getRegistradores(){
        return this.registradores;
    }
}
