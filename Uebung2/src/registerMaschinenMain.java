import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class registerMaschinenMain {

    public static void main(String[] args){
        HashMap<Integer, BefehlKombination> befehlsMap = new HashMap<>();


        List<String>cmdList = new ArrayList<>();
        List<String>adressList = new ArrayList<>();



        String filePath = "summenQuadrat.txt";

        //Read file and convert to String
        String hexaProgramCode = convertToHexaCode(filePath);
        // Divide in commands and adresses
        addInformationToLists(cmdList, adressList, hexaProgramCode);
        // print translated code
        printTranslatedCode(cmdList, adressList);

        //map befüllen
        for(int i = 1; i<= cmdList.size(); i++){
            // erst gleichheit d Listen checken
            befehlsMap.put(i, new BefehlKombination(cmdList.get(i-1), adressList.get(i-1)));
        }

        returnOperation(befehlsMap);
    }

    public static void returnOperation(HashMap h) {
        List<AdressRaum>adressenImSpeicher = new ArrayList<>();
        //PC = ProgramCounter
        int programCounter = 0;
        //Akkumulator
        AdressRaum akku = new AdressRaum(0, "00");
        //EndergebnisVariable
        int endergebnis = 0;
        boolean isValid = true;


        while(isValid){
            BefehlKombination temp = (BefehlKombination)h.get(programCounter++);



            switch(temp.cmd){
                case "01":
                    //addition
                    akku.setValue(
                            akku.getValue() +
                            getValueFromMemory(temp.adress, adressenImSpeicher));
                    break;
                case "02":
                    //Subtraktion
                    akku.setValue(
                            akku.getValue() -
                            getValueFromMemory(temp.adress, adressenImSpeicher)
                    );
                    break;
                case "03":
                    //Multiplikation
                    akku.setValue(
                            akku.getValue() *
                            getValueFromMemory(temp.adress, adressenImSpeicher)
                    );
                    break;
                case "04":
                    // DIV
                    akku.setValue(
                            akku.getValue() /
                            getValueFromMemory(temp.adress, adressenImSpeicher)
                    );
                    break;
                case "05":
                    // LDA, Adresse laden
                    akku.setValue(getValueFromMemory(temp.adress, adressenImSpeicher));
                    break;
                case "06":
                    // LDK, konstante laden,
                    try{
                        akku.setValue(Integer.parseInt(temp.adress));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        System.out.println("Please do not use hexadecimals with this command --only int/long/double possible");
                    }
                    break;
                case "07":
                    // falls Adresse noch nicht hinterlegt - > abspeichern
                    for(AdressRaum a: adressenImSpeicher){
                        if(!(a.getAdresse().equals(temp.adress))){
                            AdressRaum tempAdress = new AdressRaum(0,temp.adress);
                            tempAdress.setValue(akku.getValue());
                            adressenImSpeicher.add(tempAdress);
                        } else {
                            a.setValue(akku.getValue());
                        }
                    }
                    break;
                case "08":
                    // INP adresse - f(adresse) = Eingabe
                    Scanner sc = new Scanner(System.in);
                    for(AdressRaum a: adressenImSpeicher){
                        if(!(a.getAdresse().equals(temp.adress))){
                            AdressRaum tempAdress = new AdressRaum(0,temp.adress);
                            System.out.println("ProgrammCounter = " + programCounter + " - Es soll eine Eingabe " +
                                    "in Addresse '" + temp.adress + "' " +
                                    "gespeichert werden: ");
                            tempAdress.setValue(sc.nextInt());
                            adressenImSpeicher.add(tempAdress);
                        } else {
                            System.out.println("ProgrammCounter = " + programCounter + " - Es soll eine Eingabe " +
                                    "in Addresse '" + temp.adress + "' " +
                                    "gespeichert werden: ");
                            a.setValue(sc.nextInt());
                        }
                        sc.close();
                    }
                    break;
                case "09":
                    for(AdressRaum a: adressenImSpeicher) {
                        if (!(a.getAdresse().equals(temp.adress))) {
                            System.out.println("Adresse konnte nicht gefunden werden -> Keine Ausgabe möglich");
                        } else {
                            System.out.println("Adresse '" + temp.adress + "' beinhaltet den Wert: " + a.getValue());
                        }
                    }
                    break;
                case "0A":
                    isValid = false;
                    break;
                case "0B":
                    // Jump-Befehlsatz: programCounter = new programCounter
                    programCounter = Integer.parseInt(temp.adress);
                    break;
                case "0C":
                    if(akku.getValue() == 0) {
                        programCounter = Integer.parseInt(temp.adress);
                    }
                    break;
                case "0D":
                    if(akku.getValue() != 0) {
                        programCounter = Integer.parseInt(temp.adress);
                    }
                    break;
                case "0E":
                    if(akku.getValue() < 0) {
                        programCounter = Integer.parseInt(temp.adress);
                    }
                    break;
                case "0F":
                    if(akku.getValue() <= 0) {
                        programCounter = Integer.parseInt(temp.adress);
                    }
                    break;
                case "10":
                    if(akku.getValue() > 0) {
                        programCounter = Integer.parseInt(temp.adress);
                    }
                    break;
                case "11":
                    if(akku.getValue() >= 0) {
                        programCounter = Integer.parseInt(temp.adress);
                    }
                    break;
            }
        }
    }
    public static int getValueFromMemory(String tempAdresse, List<AdressRaum> liste){
        try {
            for(AdressRaum a : liste){
                if(a.getAdresse().equals(tempAdresse)) {
                    return a.getValue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public static void setValueInMemory(String tempAdresse, List<AdressRaum> liste){

    }

    public static void printTranslatedCode(List<String>commandList, List<String> adressList) {
        int i = 1;
        for(String s : commandList) {
            System.out.println(i++ + ". command: " + s + ", Addresse: " + adressList.get(commandList.indexOf(s)));

        }
    }
    public static void addInformationToLists(List<String>commands, List<String>memory, String hexaCode) {
        int stringLength = hexaCode.length();
        String temp = "";
        int listIndicator = 0;

        for(int i = 1; i <= stringLength/2; i++) {
            temp = hexaCode.substring((i-1)*2, i*2);

            if(listIndicator == 0){
                commands.add(temp);
                listIndicator = 1;
                temp = "";
                continue;
            }
            if(listIndicator == 1) {
                memory.add(temp);
                listIndicator = 0;
                temp = "";
                continue;
            }
        }
    }


    public static String convertToHexaCode(String txtPfad) {

        String programmCode = "";
        try {
            FileReader fr = new FileReader(txtPfad);
            BufferedReader br = new BufferedReader(fr);
            String temp = "";


            while((temp = br.readLine())!= null) {
                programmCode += temp;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return programmCode;
    }
}
