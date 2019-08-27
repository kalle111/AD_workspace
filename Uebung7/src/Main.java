import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int c1 = 1;
    public static int c2 = 3;
    public static int m = 11;

    public static void main(String[] args) {
        System.out.println("Hashing mit offener Adressierung!");
            List<int[]> listOfArrays = new ArrayList<>();
            int[] intArray = {10, 22, 31, 4, 15, 28, 17, 88, 59};
            int[] hashArrayLinear = createHashArray();
            int[] hashArrayQuadratisch = createHashArray();
            int[] hashArrayDoppeltesHashing = createHashArray();


            hashArrayLinear = linearesHashing(intArray);
            hashArrayQuadratisch = quadratischesHashing(intArray);
            hashArrayDoppeltesHashing = doppeltesHashing(intArray);

            listOfArrays.add(hashArrayLinear);
            listOfArrays.add(hashArrayQuadratisch);
            listOfArrays.add(hashArrayDoppeltesHashing);

            for(int[] a : listOfArrays) {
                printHashArray(a);
            }
    }


    public static int hashValue(int s){
        //implement hash algorithm and return hash value
        return s%m;
    }

    public static void printHashArray(int[] hashArray){
        int count = 0;
        for(Object o: hashArray) {
            try {
                System.out.println("Adresse: "+ count++ + " - Value: " + o);
            } catch (InputMismatchException e1) {
                System.out.println("InputMismatch");
                e1.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Other exception");
            }
        }
    }

    public static int[] createHashArray() {
        int[] tempArray = new int[m];
        Integer ii = 0;  // default-value zur Befüllung

        for(int i = 0; i < tempArray.length; i++) {
            tempArray[i] = ii;
        }
        return tempArray;
    }

    public static int[] linearesHashing(int[]intArray) {
        int[] temp = createHashArray();
        int letztesEingefuegtesElement = 0;
        for(int i = 0; i < intArray.length; i++) {
            try {
                if (hashValue(intArray[i]) > temp.length) {
                    temp[linearesEinfuegen(temp, letztesEingefuegtesElement)] = intArray[i];
                    // letztes Element = 0 + Letzte Stelle
                } else {
                    temp[linearesEinfuegen(temp, letztesEingefuegtesElement)] = intArray[i];
                }
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                System.out.println("Stellensuche fehlerhaft!");
            }
        }
        return temp;
    }

    public static int linearesEinfuegen(int[] temp, int searchValue) {
        int i = searchValue;
        for(; i < temp.length; i++) {
            if (temp[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static int[] doppeltesHashing(int[] intArray) {
        int[] tempArray = createHashArray();


        return tempArray;
    }





    public static int[] quadratischesHashing(int[] intArray) {
        int[] tempArray = createHashArray();

        for(int i : intArray) {
            int hashVal = hashValue(i);
            if(tempArray[hashVal] == 0) {
                tempArray[hashVal] = i;
            } else {
                boolean optOut = false;
                int count = 1;
                while(optOut == false) {
                    hashVal = hashValue(hashVal + c1*count + ((c2*count)*(c2*count++)));
                    if(tempArray[hashVal] == 0) {
                            tempArray[hashVal] = i;
                            optOut = true;
                        }
                    }

                }

            }
        return tempArray;
    }



    // obsolete functions
    public static void neueZahlEinlesen(List<Integer> intList) {
        System.out.print("Bitte jetzt die Int-Zahl eingeben: ");
        Scanner sc = new Scanner(System.in);
        int newInt = Integer.parseInt(sc.next());
        intList.add(newInt);
    }
}

