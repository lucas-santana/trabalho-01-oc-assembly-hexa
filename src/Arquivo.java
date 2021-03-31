import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Arquivo {

    public  ArrayList<String> lerArquivo(String pathFile) {
        BufferedReader buffRead;
        ArrayList<String> arquivoLido = new ArrayList<String>();
        try {
            buffRead = new BufferedReader(new FileReader(pathFile));
            String linha = (buffRead.readLine()).trim();
            int indexCerquilha;

            while (linha != null) {
                linha = linha.trim();
                if (linha.length() > 0) {
                    indexCerquilha = linha.indexOf('#');
                    if(indexCerquilha == -1){//não encontrou cerquilha, não encontrou comentários na linha
                        arquivoLido.add(linha);
                    }else{
                        arquivoLido.add(linha.substring(0, indexCerquilha));//se encontrou cerquilha, pega a string até a cerquilha
                    }
                }
                linha = buffRead.readLine();
            }
            buffRead.close();

            return arquivoLido;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getInstrucaoHexa(ArrayList<String> arrayListInstucoes){

        int indexCharacter;
        String opcode, rs, rt, rd, restoString = "", concat = "";
        for (String string:arrayListInstucoes) {
            System.out.println(string);

            /*
                TODO: em vez de concatenar as intruções, criar um método para buscar o código binario da instrução e do registrador
             */
            //1 - Pegar o opcode
            indexCharacter = string.indexOf(' ');
            if(indexCharacter == -1){
                continue;
            }
            opcode = string.substring(0, indexCharacter);
            concat = concat.concat(opcode);
            restoString = string.substring(indexCharacter).trim();

            //2 - Pegar o RS
            indexCharacter = restoString.indexOf(',');
            if(indexCharacter == -1){
                continue;
            }
            rs = restoString.substring(0, indexCharacter);
            concat = concat.concat(rs);
            restoString = restoString.substring(indexCharacter+1).trim();

            //3 - Pegar o RT
            indexCharacter = restoString.indexOf(',');
            if(indexCharacter == -1){
                continue;
            }
            rt = restoString.substring(0, indexCharacter);
            concat = concat.concat(rt);
            restoString = restoString.substring(indexCharacter+1).trim();

            //4 - Pegar o RD se for instrução do tipo R
            if(this.isRInstruction(opcode)){
                rd = restoString.substring(0);
                concat = concat.concat(rd);
            }
        }


        return restoString;
    }

    public boolean isRInstruction(String opcode){
        Instrucoes instrucoes = new Instrucoes();
        ArrayList<Instrucao> arrayListInstrucao = instrucoes.getInstrucoes();

        for(Instrucao instrucao: arrayListInstrucao){
            if(instrucao.nomeInstrucao.equals(opcode)){
                return instrucao.tipo.equals("R");
            }
        }
        return false;
    }
}
