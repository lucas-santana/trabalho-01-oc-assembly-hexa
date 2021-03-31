import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Aperte enter para ler o arquivo no diretório src/assembly ou informe o caminho absoluto do arquivo com código assembly ISA MIPS: ");
        //String caminhoArquivo = scanner.nextLine();
        String caminhoArquivo = "src/assembly.txt";

        /*
        if(caminhoArquivo.trim().length() == 0){
            caminhoArquivo = "src/assembly.txt";
        }
        */

        Registradores registradores = new Registradores();
        ArrayList<Registrador> listaRegistradores = registradores.getRegistradores();

        Instrucoes instrucoes = new Instrucoes();
        ArrayList<Instrucao> listaInstrucoes = instrucoes.getInstrucoes();

        Arquivo arquivo = new Arquivo();
        ArrayList<String> arquivoLido = arquivo.lerArquivo(caminhoArquivo);
        ArrayList<String> instrucoesHexa = null;
        if (arquivoLido != null) {
            try {
                instrucoesHexa  = arquivo.getInstrucaoHexa(arquivoLido, listaInstrucoes, listaRegistradores);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}
