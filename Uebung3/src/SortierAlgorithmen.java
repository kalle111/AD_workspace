import javafx.css.Match;

public class SortierAlgorithmen {

    public static void main (String [] args){
        int [] intArray = {-5, 13, -32, 7, -3, 17, 23, 12, -35, 19};
        int[] intArray2 = intArray.clone();
        int[] intArray3 = intArray2.clone();
        int[] intArray4 = intArray3.clone();
        int[] intArray5 = intArray4.clone();



        intArray = insertionSort(intArray);
        intArray2 = bubbleSort(intArray2);
        intArray3 = selectionSortByMax(intArray3);
        intArray4 = insertionSortMaximumPivot(intArray4);
        intArray5 = bubbleSortMaxToTheBack(intArray5);

        //int i = 0;
        //while(i < intArray.length){
        //    System.out.println((intArray[i++]));
        //}
    }

    public static int[] selectionSort(int[] intArry){
        int i, j, min;
        System.out.println("\nSelection Sort algorithm\n----");

        for(i = 0; i < intArry.length; i++){
            min = i;
            printArray(intArry);
            for(j = i; j < intArry.length; j++){
                if(intArry[j] < intArry[min]) {
                        min = j;
                }
            }
            int temp = intArry[i];
            intArry[i] = intArry[min];
            intArry[min] = temp;
        }
    return intArry;
    }

    public static int[] selectionSortByMax(int[] intArry){
        int i, j, max;
        System.out.println("\nSelection Sort - by Max - algorithm\n----");

        for(i = intArry.length-1; i >=0; i--){
            max = i;
            printArray(intArry);
            for(j = i; j >= 0; j--){
                if(intArry[j] > intArry[max]) {
                    max = j;
                }
            }
            int temp = intArry[i];
            intArry[i] = intArry[max];
            intArry[max] = temp;
        }
        return intArry;
    }

    public static int[] bubbleSort (int [] intArray){
        System.out.println("\nBubble Sort algorithm\n----");

        int i, key, temp, j;

        for(j = 0; j < intArray.length; j++) {
            for(i = intArray.length-1; i > j; i--){
                key = i-1;
                if(intArray[i] < intArray[key]){
                    temp = intArray[key];
                    intArray[i-1] = intArray[i];
                    intArray[i] = temp;
                }
                printArray(intArray);
            }
        }
        return intArray;
    }

    public static int[] insertionSort (int[] intArray) {
        System.out.println("\nInsertion Sort algorithm\n----");
        int i, j, key;

        for(j = 1; j < intArray.length; j++){
            key = intArray[j];
            i = j - 1;

            while(i >= 0 && key < intArray[i]){
                intArray[i+1] = intArray[i];
                i = i-1;

            }
            intArray[i+1] = key;
            printArray(intArray);
        }
        return intArray;
    }

    public static int[] insertionSortMaximumPivot(int[] intArray) {
        System.out.println("\nInsertion Sort - Maximum First - algorithm\n----");
        int i, j, key;

        for(j = 1; j < intArray.length; j++){
            key = intArray[j];
            i = j - 1;

            while(i >= 0 && key > intArray[i]){
                intArray[i+1] = intArray[i];
                i = i-1;

            }
            intArray[i+1] = key;
            printArray(intArray);
        }
        return intArray;
    }

    public static int[] bubbleSortMaxToTheBack(int [] intArray){
        System.out.println("\nBubble Sort - start with Max at the back - algorithm\n----");

        int i, key, temp, j;

        for(j = intArray.length-1; j > 0; j--) {
            for(i = 0; i < j; i++){
                key = i+1;
                if(intArray[i] > intArray[key]){
                    temp = intArray[key];
                    intArray[i+1] = intArray[i];
                    intArray[i] = temp;
                }
                printArray(intArray);
            }
        }
        return intArray;
    }

    private static int partition(int[] list, int first, int last) {
        int pivot = list[first];
        int low = first + 1;
        int high = last;

        while (high > low) {

            while (low < high && list[low] < pivot) {
                low++;
            }


            while (low < high && list[high] >= pivot) {
                high--;
            }


            if (high > low) {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }
            while (high > first && list[high] >= pivot) {
                high--;
            }

            if (pivot > list[high]) {
                list[first] = list[high];
                list[high] = pivot;
                return high;
            } else {
                return first;
            }

        }

        private static void quickSort(int[] list, int first, int last) {
            if (last > first) {
                int pivotIndex = partition(list, first, last);
                quickSort(list, first, pivotIndex - 1);
                quickSort(list, pivotIndex + 1, last);
            }
        }


    public static void printArray(int[] intArray){
        int j = 0;
        for(int i: intArray){
            j++;
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
