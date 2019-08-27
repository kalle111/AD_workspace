public class AdressRaum {
    private int value;
    private String adresse; // kann auch bei LDK eine Zahl sein.

    public AdressRaum(int v, String a){
        this.value = v;
        this.adresse = a;
    }

    public void setValue(int v){
        this.value = v;
    }
    public void setAdresse(String a){
        this.adresse = a;
    }

    public int getValue() {
        return value;
    }

    public String getAdresse() {
        return adresse;
    }
}
