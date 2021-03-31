import java.util.ArrayList;

public class Instrucao {
    public String tipo;
    public String nomeMnemonic;
    public String hexaMnemonic;

    public Instrucao(String tipo, String mnemonic, String hexaMnemonic) {
        this.tipo = tipo;
        this.nomeMnemonic = mnemonic;
        this.hexaMnemonic = hexaMnemonic;
    }

    public String getBinarioFromHexa(){
        return Integer.toBinaryString(Integer.parseInt(this.hexaMnemonic, 16));
    }

    public boolean isInstrucaoTipoR(){
        return this.tipo.equals("R");
    }

    public boolean isInstrucaoTipoI(){
        return this.tipo.equals("I");
    }

    public String getTipo(){
        return this.tipo;
    }
    public String getNomeMnemonic() {
        return nomeMnemonic;
    }

    public String parserInstrucao(String linha, Instrucao instrucao, ArrayList<Registrador> listaRegistradores) throws Exception {
        if(this.isInstrucaoTipoR()){
            return this.parserInstrucaoTipoR(linha, instrucao, listaRegistradores);
        }

        if(this.isInstrucaoTipoI()){
            return this.parserInstrucaoTipoI(linha, instrucao, listaRegistradores);
        }

        throw new Exception("Tipo instrução não identificado: "+instrucao.getTipo());
    }

    private String parserInstrucaoTipoR(String linha, Instrucao instrucao, ArrayList<Registrador> listaRegistradores) throws Exception {
        if(!instrucao.isInstrucaoTipoR()){
            throw new Exception("Essa não é uma instrução do tipo R");
        }

        int indexCharacter;
        String opcode = "000000";//Instrução do tipo R, o OPCODE = 000000
        String shamt = "00000";//Shamt só usa na instrução sll
        String funct = instrucao.getBinarioFromHexa();

        //Pegar o RD
        indexCharacter = linha.indexOf(',');
        if(indexCharacter == -1){
            throw new Exception("Falha ao encontrar vírgula para encontrar o RD...");
        }

        String stringRd = linha.substring(0, indexCharacter);

        Registrador registradorRd = listaRegistradores.stream().filter(search -> {
            return stringRd.equals(search.getNomeRegistrador());
        }).findFirst().orElseThrow(()->new Exception("Registrador não encontrado: "+stringRd));

        linha = linha.substring(indexCharacter+1).trim();

        //Pegar o RS
        indexCharacter = linha.indexOf(',');
        if(indexCharacter == -1){
            throw new Exception("Falha ao encontrar vírgula para encontrar o RS...");
        }

        String stringRs = linha.substring(0, indexCharacter);

        Registrador registradorRs = listaRegistradores.stream().filter(search -> {
            return stringRs.equals(search.getNomeRegistrador());
        }).findFirst().orElseThrow(()->new Exception("Registrador não encontrado: "+stringRs));

        //Pegar o RT
        String stringRt = linha.substring(indexCharacter + 1).trim();

        Registrador registradorRt = listaRegistradores.stream().filter(search -> {
            return stringRt.equals(search.getNomeRegistrador());
        }).findFirst().orElseThrow(()->new Exception("Registrador não encontrado: "+stringRt));

        String binario =  opcode + registradorRs.getBinarioFromDecimal() + registradorRt.getBinarioFromDecimal() + registradorRd.getBinarioFromDecimal() + shamt + funct;


        int binarioDecimal = Integer.parseInt(binario, 2);
        String decimalHexa = Integer.toString(binarioDecimal, 16);
        decimalHexa = Util.padLeft(decimalHexa,"0",8);
        return decimalHexa;
    }

    private String parserInstrucaoTipoI(String linha, Instrucao instrucao, ArrayList<Registrador> listaRegistradores) throws Exception {
        if(!instrucao.isInstrucaoTipoI()){
            throw new Exception("Essa não é uma instrução do tipo I");
        }

        int indexCharacter;
        String opcode = Util.padLeft(instrucao.getBinarioFromHexa(),"0", 6);

        //Pegar o RT
        indexCharacter = linha.indexOf(',');
        if(indexCharacter == -1){
            throw new Exception("Falha ao encontrar vírgula para encontrar o RT...");
        }

        String stringRt = linha.substring(0, indexCharacter);

        Registrador registradorRt = listaRegistradores.stream().filter(search -> {
            return stringRt.equals(search.getNomeRegistrador());
        }).findFirst().orElseThrow(()->new Exception("Registrador não encontrado: "+stringRt));

        linha = linha.substring(indexCharacter+1).trim();

        //Pegar o RS
        indexCharacter = linha.indexOf(',');
        if(indexCharacter == -1){
            throw new Exception("Falha ao encontrar vírgula para encontrar o RS...");
        }

        String stringRs = linha.substring(0, indexCharacter);

        Registrador registradorRs = listaRegistradores.stream().filter(search -> {
            return stringRs.equals(search.getNomeRegistrador());
        }).findFirst().orElseThrow(()->new Exception("Registrador não encontrado: "+stringRs));

        //Pegar o RT
        String stringImm = linha.substring(indexCharacter + 1).trim();

        //TODO: tem que pegar o endereço do label
        String binario =  opcode + registradorRs.getBinarioFromDecimal() + registradorRt.getBinarioFromDecimal()+Util.padLeft("0","0",16);

        int binarioDecimal = Integer.parseInt(binario, 2);
        String decimalHexa = Integer.toString(binarioDecimal, 16);
        decimalHexa = Util.padLeft(decimalHexa,"0",8);
        return decimalHexa;
    }


}
