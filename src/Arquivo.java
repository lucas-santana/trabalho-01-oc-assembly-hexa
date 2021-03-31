import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Arquivo {

    public ArrayList<String> lerArquivo(String pathFile) {
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

    public ArrayList<String> getInstrucaoHexa(ArrayList<String> arrayListInstucoes, ArrayList<Instrucao> listaInstrucoes, ArrayList<Registrador> listaRegistradores) throws Exception {
        int indexCharacter;
        String operation;
        ArrayList<String> arrayListInstucoesHexa = new ArrayList<String>();

        for (String string:arrayListInstucoes) {
            System.out.println(string);

            //1 - Pegar o mnemonic
            indexCharacter = string.indexOf(' ');
            if(indexCharacter == -1){
                continue;
            }

            String mnemonic = string.substring(0, indexCharacter);

            Instrucao instrucao = listaInstrucoes.stream().filter(search -> mnemonic.equals(search.getNomeMnemonic())).findFirst().orElseThrow(()->new Exception("Instrução não encontrada: "+mnemonic));

            operation = string.substring(indexCharacter).trim();


            arrayListInstucoesHexa.add(instrucao.parserInstrucao(operation, instrucao, listaRegistradores));

            //TODO: Salvar instruções hexa em um arquivo

        }

        return arrayListInstucoesHexa;
    }
}
