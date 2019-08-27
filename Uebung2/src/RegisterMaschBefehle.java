public enum RegisterMaschBefehle {
    ADD ("01"),
    SUB ("02"),
    MUL ("03"),
    DIV ("04"),
    LDA ("05"),
    LDK ("06"),
    STA ("07"),
    INP ("08"),
    OUT ("09"),
    HLT99 ("0A"),
    JMP ("0B"),
    JEZ ("0C"),
    JNE ("0D"),
    JLZ ("0E"),
    JLE ("0F"),
    JGZ ("10"),
    JGE ("11");



    public final String befehlChars;

    private RegisterMaschBefehle(String s) {
        this.befehlChars = s;
    }
}
