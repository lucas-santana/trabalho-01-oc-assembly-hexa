public class Util {

    public static String padLeft(String string, String stringPad, int length) {
        return String.format("%1$" + length + "s", string).replace(' ', '0');//completa com zeros a esquerda
    }
}
