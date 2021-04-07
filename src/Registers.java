import java.util.ArrayList;

public class Registers {
    private static ArrayList<Register> registers;

    /**
     * Inicializa os registradores
     */
    public static void setupRegisters(){
        Registers.registers = new ArrayList<Register>();

        Registers.registers.add(new Register("$zero", 0));

        Registers.registers.add(new Register("$t0", 8));
        Registers.registers.add(new Register("$t1", 9));
        Registers.registers.add(new Register("$t2", 10));
        Registers.registers.add(new Register("$t3", 11));
        Registers.registers.add(new Register("$t4", 12));
        Registers.registers.add(new Register("$t5", 13));
        Registers.registers.add(new Register("$t6", 14));
        Registers.registers.add(new Register("$t7", 15));

        Registers.registers.add(new Register("$s0", 16));
        Registers.registers.add(new Register("$s1", 17));
        Registers.registers.add(new Register("$s2", 18));
        Registers.registers.add(new Register("$s3", 19));
        Registers.registers.add(new Register("$s4", 20));
        Registers.registers.add(new Register("$s5", 21));
        Registers.registers.add(new Register("$s6", 22));
        Registers.registers.add(new Register("$s7", 23));

        Registers.registers.add(new Register("$t8", 24));
        Registers.registers.add(new Register("$t9", 25));
    }

    public static ArrayList<Register> getRegisters(){
        return Registers.registers;
    }
 }
