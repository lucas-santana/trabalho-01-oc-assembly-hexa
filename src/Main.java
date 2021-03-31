import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /*
        TODO:
            1. Passar caminho do arquivo ao executar o programa e ler um arquivo
            2. Remover todas as linhas em branco
     */
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

        Arquivo arquivo = new Arquivo();
        ArrayList<String> arquivoLido = arquivo.lerArquivo(caminhoArquivo);

        if (arquivoLido != null) {
            arquivo.getInstrucaoHexa(arquivoLido);
        }

        Registradores registradores = new Registradores();
        ArrayList<Registrador> listaRegistradores = registradores.getRegistradores();
        /*for (Registrador s2:listaRegistradores) {
            System.out.println(s2.getBinarioFromDecimal());
        }*/

        /*if (arquivoLido != null) {
            for (String s : arquivoLido) {
                //String bin = Integer.toBinaryString(Integer.parseInt(s, 16));

                System.out.println(s);
            }
        }
*/

    }


}
