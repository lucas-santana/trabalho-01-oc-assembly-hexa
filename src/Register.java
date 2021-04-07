public class Register {
    private final String name;
    private final Integer number;


    public Register(String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    public String getBinaryFromDecimal() {
        return Help.padLeft(Long.toBinaryString(this.number), '0', 5);
    }

    public String getName() {
        return name;
    }
}
