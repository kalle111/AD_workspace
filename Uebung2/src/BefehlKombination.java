public class BefehlKombination {

    public String adress;
    public String cmd;

    public BefehlKombination (String cmd, String a){
        this.cmd = cmd;
        this.adress = a;
    }


    public String printCommand(){
        String s = this.cmd;
        return s;
    }
    public String printAdress(){
        String s = this.adress;
        return s;
    }
}
