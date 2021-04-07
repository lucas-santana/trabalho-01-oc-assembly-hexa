import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Help {

    /**
     * Completa a string a esquerda com o carater passado como argumento no tamnho passado no argumento length
     * @param string
     * @param stringPad
     * @param length
     * @return
     */
    public static String padLeft(String string, char stringPad, int length) {
        return String.format("%1$" + length + "s", string).replace(' ', stringPad);//completa com zeros a esquerda
    }

    /**
     * Converte um valor hexadecimal em binário
     * @param stringHex valor hexadecimal
     * @return valor em binário
     */
    public static String getBinaryFromHex(String stringHex){
        return Long.toBinaryString(Long.parseLong(stringHex, 16));
    }

    /**
     * Converte um valor decimal em binário
     * @param stringHex valor hexadecimal
     * @return valor em binário
     */
    public static String getBinaryFromDec(String stringHex){
        return Long.toBinaryString((Long.parseLong(stringHex, 10)));
    }

    /**
     * Retorna o valor hexadecimal do funct pelo nome da instrução
     * @return valor hexadecimal do funct pelo nome da instrução
     */
    public static String getFunctHexFromMnemonic(String mnemonic) throws Exception {

        return switch (mnemonic) {
            case "add" -> "20";
            case "sub" -> "22";
            case "and" -> "24";
            case "or" -> "25";
            case "slt" -> "2a";
            default -> throw new Exception("Mnemonic não encontrado: "+mnemonic);
        };

    }

    /**
     * Retorna o valor hexadecimal do opcode pelo nome da instrução
     * @return valor hexadecimal do opcode pelo nome da instrução
     */
    public static String getOpcodeHexFromMnemonic(String mnemonic) throws Exception {

        return switch (mnemonic) {
            case "lw" -> "23";
            case "sw" -> "2b";
            case "beq" -> "4";
            case "j" -> "2";
            default -> throw new Exception("Mnemonic não encontrado: "+mnemonic);
        };

    }

    public static boolean isInstructionRByMnemonic(String mnemonic){
        return List.of("add", "sub", "and", "or", "slt").contains(mnemonic);
    }

    public static boolean isInstructionIByMnemonic(String mnemonic){
        return List.of("lw", "sw", "beq").contains(mnemonic);
    }

    public static boolean isInstructionJByMnemonic(String mnemonic){
        return Objects.equals("j", mnemonic);
    }

    public static int getIndexLabelFromArquivoLido( String label) throws Exception {
        label = label.replace(" ", "");
        ArrayList<String> arquivoLido = File.getReadFile();
        for (int i = 0; i < arquivoLido.size(); i++) {
            String linha = arquivoLido.get(i).trim();
            if (linha.replace(" ", "").contains(label)) {
                return i;
            }
        }
        throw new Exception("não encontrado linha com label: "+label);
    }

}
