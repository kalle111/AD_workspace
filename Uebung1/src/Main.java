import java.util.ArrayList;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        findAndPrintPrimes(100000);

    }

    public static void findAndPrintPrimes(int schranke) {
        List<Integer> primeList = new ArrayList<Integer>();
        List<Integer> fullTable = new ArrayList<>();
        for (int i = 1; i <= schranke; i++) {
            fullTable.add(i);
            if (i > 1 && i == 2) {
                primeList.add(i);
                continue;
            }

            for (int j = 2; j <= i; j++) {
                if (((i % j) == 0) && (i != j)) {
                    break;
                } else if (i == j) {
                    {
                        primeList.add(i);
                    }
                }
            }
        }

        // Aufgabe 2.3: Primes bis 100.000 ausgeben lassen
        for(int a : primeList) {
            System.out.print(a + ", ");
            if((primeList.indexOf(a)%15)==0 && primeList.indexOf(a) > 10){
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("Die Anzahl der Primzahlen ist: " + (primeList.size()));

        // Aufgabe 2.2 - Tabelle Ausgeben lassen:

        // Tabelle ausgeben - macht nur bis zu einer bestimmten Rechengroesse Sinn.
        //for(Integer a : fullTable) {
        //if(((fullTable.indexOf(a)+1)%10)==0 && fullTable.indexOf(a)>= 9){
        //System.out.println("");
        //}
        //if(primeList.contains(a)){
        //System.out.print(a + " ");
        //} else {
        //System.out.print("__ ");
        //}

        //}
    }
}


