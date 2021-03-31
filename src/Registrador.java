public class Registrador {
    public String nomeRegistrador;
    public String decimal;

    public Registrador(String nomeRegistrador, String decimal) {
        this.nomeRegistrador = nomeRegistrador;
        this.decimal = decimal;
    }

    public String getBinarioFromDecimal(){
        String inputString = Integer.toBinaryString(Integer.parseInt(this.decimal));
        inputString = String.format("%1$" + 5 + "s", inputString).replace(' ', '0');//completa com zeros a esquerda
        return inputString;
    }

    public String getNomeRegistrador() {
        return nomeRegistrador;
    }
}
