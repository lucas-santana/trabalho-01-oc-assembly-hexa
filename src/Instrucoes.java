import java.util.ArrayList;

public class Instrucoes {

    ArrayList<Instrucao> instrucoes;

    public Instrucoes() {
        this.instrucoes = new ArrayList<Instrucao>();
        this.instrucoes.add(new Instrucao("I", "lw","23"));
        this.instrucoes.add(new Instrucao("I", "sw","2b"));
        this.instrucoes.add(new Instrucao("I", "beq","4"));
        this.instrucoes.add(new Instrucao("R", "add","20"));
        this.instrucoes.add(new Instrucao("R", "sub","22"));
        this.instrucoes.add(new Instrucao("R", "and","24"));
        this.instrucoes.add(new Instrucao("R", "or","25"));
        this.instrucoes.add(new Instrucao("R", "slt","2a"));
        this.instrucoes.add(new Instrucao("J", "j","2"));
    }

    public ArrayList<Instrucao> getInstrucoes(){
        return this.instrucoes;
    }

    /*public Instrucao findInstrucao(String nome){
        Instrucoes instrucoes = new Instrucoes();
        ArrayList<Instrucao> arrayListInstrucao = instrucoes.getInstrucoes();

        for(Instrucao instrucao: arrayListInstrucao){
            if(instrucao.nomeMnemonic.equals(nome)){
                return instrucao;
            }
        }
        return null;
    }
*/


}
