import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Registers.setupRegisters();

        //Ler arquivo
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do arquivo no diretorio files: ");
        String fileName = scanner.nextLine();

        File file = new File();
        File.setFileName(fileName);
        ArrayList<String> arquivoLido = file.readFile();
        ArrayList<String> instructionsHex = null;
        if (arquivoLido != null) {
            try {
                instructionsHex  = file.getInstructionsHex(arquivoLido);
                file.saveInstructionsHexToFile(instructionsHex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
