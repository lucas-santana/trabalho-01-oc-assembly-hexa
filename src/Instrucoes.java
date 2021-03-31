import java.util.ArrayList;

public class Instrucoes {

    ArrayList<Instrucao> instrucoes;

    public Instrucoes() {
        this.instrucoes = new ArrayList<Instrucao>();
        this.instrucoes.add(new Instrucao("I", "lw","23", "100011"));
        this.instrucoes.add(new Instrucao("I", "sw","2b", "101011"));
        this.instrucoes.add(new Instrucao("I", "beq","4", "000100"));
        this.instrucoes.add(new Instrucao("R", "add","20", "100000"));
        this.instrucoes.add(new Instrucao("R", "sub","22", "100010"));
        this.instrucoes.add(new Instrucao("R", "and","24", "100100"));
        this.instrucoes.add(new Instrucao("R", "or","25", "100101"));
        this.instrucoes.add(new Instrucao("R", "slt","2a", "101010"));
        this.instrucoes.add(new Instrucao("J", "j","2", "000010"));
    }

    public ArrayList<Instrucao> getInstrucoes(){
        return this.instrucoes;
    }


}
