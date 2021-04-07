import java.io.*;
import java.util.ArrayList;

public class File {

    private static ArrayList<String> readFile;
    private static String fileName;

    public ArrayList<String> readFile() {

        BufferedReader buffRead;
        ArrayList<String> arquivoLido = new ArrayList<String>();
        try {
            buffRead = new BufferedReader(new FileReader("files/"+ File.getFileName()));
            String linha = (buffRead.readLine()).trim();
            int indexHashTag;

            while (linha != null) {
                linha = linha.trim();
                if (linha.length() > 0) {
                    indexHashTag = linha.indexOf('#');
                    if(indexHashTag == -1){//não encontrou cerquilha, não encontrou comentários na linha
                        arquivoLido.add(linha);
                    }else{
                        linha = linha.substring(0, indexHashTag).trim();
                        if (linha.length() > 0) {//para os casos onde a linha começa com #
                            arquivoLido.add(linha);//se encontrou cerquilha, pega a string até a cerquilha
                        }
                    }
                }
                linha = buffRead.readLine();
            }
            buffRead.close();

            File.readFile = arquivoLido;
            return arquivoLido;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setFileName(String fileName) {
        File.fileName = fileName;
    }

    public static String getFileName() {
        return fileName;
    }

    public static ArrayList<String> getReadFile() {
        return readFile;
    }


    public ArrayList<String> getInstructionsHex(ArrayList<String> arrayListInstructions) throws Exception {
        int indexCharacter;
        String operation;
        ArrayList<String> arrayListInstuctionsHex = new ArrayList<String>();


        for (int i = 0; i < arrayListInstructions.size(); i++) {
            String string = arrayListInstructions.get(i);
            //1 - Pegar o mnemonic

            if (string.contains(":")) {
                string = string.substring(string.indexOf(':') + 1).trim();
            }
            indexCharacter = string.indexOf(' ');
            if (indexCharacter == -1) {
                continue;
            }

            String mnemonic = string.substring(0, indexCharacter);

            operation = string.substring(indexCharacter).trim();

            if (Help.isInstructionRByMnemonic(mnemonic)) {
                arrayListInstuctionsHex.add(this.parserInstructionR(mnemonic, operation));
            }
            if (Help.isInstructionIByMnemonic(mnemonic)) {
                arrayListInstuctionsHex.add(this.parserInstructionI(mnemonic, operation, i));
            }
            if (Help.isInstructionJByMnemonic(mnemonic)) {
                arrayListInstuctionsHex.add(this.parserInstructionJ(mnemonic, operation));
            }
        }

        return arrayListInstuctionsHex;
    }

    private String parserInstructionR(String mnemonic, String operation) throws Exception {
        int indexCharacter;
        String opcode = "000000";//Instrução do tipo R, o OPCODE = 000000
        String shamt = "00000";//Shamt só usa na instrução sll e nosso set de instrução não tem sll
        String funct = Help.getBinaryFromHex(Help.getFunctHexFromMnemonic(mnemonic));

        //Pegar o RD
        indexCharacter = operation.indexOf(',');
        if(indexCharacter == -1){
            throw new Exception("Falha ao encontrar vírgula para encontrar o RD...");
        }

        String stringRd = operation.substring(0, indexCharacter);

        Register registradorRd = Registers.getRegisters().stream().filter(search -> {
            return stringRd.equals(search.getName());
        }).findFirst().orElseThrow(()->new Exception("Registrador não encontrado: "+stringRd));

        operation = operation.substring(indexCharacter+1).trim();

        //Pegar o RS
        indexCharacter = operation.indexOf(',');
        if(indexCharacter == -1){
            throw new Exception("Falha ao encontrar vírgula para encontrar o RS...");
        }

        String stringRs = operation.substring(0, indexCharacter);

        Register registradorRs = Registers.getRegisters().stream().filter(search -> {
            return stringRs.equals(search.getName());
        }).findFirst().orElseThrow(()->new Exception("Registrador não encontrado: "+stringRs));

        //Pegar o RT
        String stringRt = operation.substring(indexCharacter + 1).trim();

        Register registradorRt = Registers.getRegisters().stream().filter(search -> {
            return stringRt.equals(search.getName());
        }).findFirst().orElseThrow(()->new Exception("Registrador não encontrado: "+stringRt));

        String binario =  opcode + registradorRs.getBinaryFromDecimal() + registradorRt.getBinaryFromDecimal() + registradorRd.getBinaryFromDecimal() + shamt + funct;


        int binarioDecimal = Integer.parseInt(binario, 2);
        String decimalHexa = Integer.toString(binarioDecimal, 16);
        decimalHexa = Help.padLeft(decimalHexa, '0',8);
        return decimalHexa;
    }

    private String parserInstructionI(String mnemonic, String operation, int indexInstrucion) throws Exception {
        int indexCharacter;
        String opcode = Help.padLeft(Help.getBinaryFromHex(Help.getOpcodeHexFromMnemonic(mnemonic)), '0', 6);
        Register registradorRs = null;
        Register registradorRt = null;
        String stringImm = null;

        if(mnemonic.equals("lw") || mnemonic.equals("sw")){
            //Pegar o RT
            indexCharacter = operation.indexOf(',');
            if(indexCharacter == -1){
                throw new Exception("Falha ao encontrar vírgula para encontrar o RT...");
            }

            String stringRt = operation.substring(0, indexCharacter);

            registradorRt = Registers.getRegisters().stream().filter(search -> {
                return stringRt.equals(search.getName());
            }).findFirst().orElseThrow(()->new Exception("Registrador não encontrado: "+stringRt));

            operation = operation.substring(indexCharacter+1).trim();

            //Pegar o valor do IMM == Offset
            indexCharacter = operation.indexOf('(');
            stringImm = operation.substring(0, indexCharacter).trim();
            stringImm = Help.getBinaryFromDec(stringImm);

            //deixa string no formato ($s0)
            operation = operation.substring(indexCharacter).trim();

            String stringRs =  operation.substring(operation.indexOf("(") + 1, operation.indexOf(")"));
            registradorRs = Registers.getRegisters().stream().filter(search -> {
                return stringRs.equals(search.getName());
            }).findFirst().orElseThrow(()->new Exception("Registrador não encontrado: "+stringRs));
        }
        if(mnemonic.equals("beq") ){

            //Pegar o RS
            indexCharacter = operation.indexOf(',');
            if(indexCharacter == -1){
                throw new Exception("Falha ao encontrar vírgula para encontrar o RS...");
            }

            String stringRs = operation.substring(0, indexCharacter);

            registradorRs = Registers.getRegisters().stream().filter(search -> {
                return stringRs.equals(search.getName());
            }).findFirst().orElseThrow(()->new Exception("Registrador não encontrado: "+stringRs));

            operation = operation.substring(indexCharacter+1).trim();

            //Pegar o RT
            indexCharacter = operation.indexOf(',');
            if(indexCharacter == -1){
                throw new Exception("Falha ao encontrar vírgula para encontrar o RS...");
            }

            String stringRt = operation.substring(0, indexCharacter);

            registradorRt = Registers.getRegisters().stream().filter(search -> {
                return stringRt.equals(search.getName());
            }).findFirst().orElseThrow(()->new Exception("Registrador não encontrado: "+stringRt));


            //Pegar o IMM
            String labelString = operation.substring(indexCharacter + 1).trim();


            int lineOfLabel = Help.getIndexLabelFromArquivoLido(labelString+":");

            int lineOfBeq = indexInstrucion+1;

            int numberLines = (lineOfBeq - lineOfLabel)*(-1);
            stringImm = Integer.toHexString(numberLines);
            if(numberLines<0){
                stringImm = stringImm.substring(stringImm.length() - 4);
            }

            stringImm = Help.getBinaryFromHex(stringImm);
        }

        String binario =  opcode + registradorRs.getBinaryFromDecimal() + registradorRt.getBinaryFromDecimal()+Help.padLeft(stringImm, '0',16);
        long binarioDecimal = Long.parseLong(binario, 2);
        String decimalHexa = Long.toString(binarioDecimal, 16);
        decimalHexa = Help.padLeft(decimalHexa, '0',8);
        return decimalHexa;
    }

    private String parserInstructionJ(String mnemonic, String labelAddress) throws Exception {
        String opcode = Help.padLeft(Help.getBinaryFromHex(Help.getOpcodeHexFromMnemonic(mnemonic)), '0', 6);

        labelAddress = labelAddress.trim();
        long lineOfLabel = Help.getIndexLabelFromArquivoLido(labelAddress+":");
        String stringLine = Long.toHexString(lineOfLabel);
        long address = 0x400000>>2;//divide por 4
        address = address + (Long.parseLong(stringLine, 16));

        String stringImm = Help.padLeft(Long.toBinaryString(address), '0', 26);

        String binario =  opcode + stringImm;
        long binarioDecimal = Long.parseLong(binario, 2);
        String decimalHexa = Long.toString(binarioDecimal, 16);
        return Help.padLeft(decimalHexa, '0', 8);
    }


    public void  saveInstructionsHexToFile(ArrayList<String> instructonsHex) throws IOException {
        java.io.File objFile = new java.io.File("files/"+ File.getFileName()+"-hexadecimal.txt");
        java.io.File objFile2 = new java.io.File("files/"+ File.getFileName()+"-hexadecimal-com-comentarios.txt");
        FileWriter objFileWriter = new FileWriter(objFile);
        FileWriter objFileWriter2 = new FileWriter(objFile2);
        for (int i = 0; i < instructonsHex.size(); i++) {
            String s = instructonsHex.get(i);
            String instruction = File.readFile.get(i);

            objFileWriter.write(s);
            objFileWriter.write("\n");

            objFileWriter2.write(s+"\t#"+instruction);
            objFileWriter2.write("\n");
        }
        objFileWriter.close();
        objFileWriter2.close();
    }
}
