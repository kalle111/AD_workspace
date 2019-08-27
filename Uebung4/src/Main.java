public class Main {

    public static void main(String[] args) {
        int a1[] = {-5, 13,-32, 7,-3,17,23,12,-35,19};
        int a2[] = a1.clone();


        System.out.print("### MergeSort: \n");
        MergeSort(a1, 0, a1.length-1);
        System.out.print("\n### HeapSort:\n");
        heapSort(a2, 0, a2.length-1);
    }

    public static void heapify(int a[], int f, int l, int root) {
        int largest, left = f+ ((root-f) * 2)+1, right=f+ ((root-f) * 2)+2;
        if(left <= l && a[left] > a[root]) {
            largest = left;
        } else {
            largest = root;
        }

        if(right <= l && a[right] > a[largest]) {
            largest = right;
        }
        if(largest != root) {
           swap(a, root, largest);
           heapify(a, f, l, largest);
        }
    }

    public static void Merge(int a[], int f, int l, int m){
        int i, n = l-f+1;
        int a1f = f, a1l = m-1;
        int a2f = m, a2l = l;
        int anew[] = new int[n];

        for(i = 0; i < n; i++){
            if(a1f <= a1l) {
                if(a2f <= a2l) {
                    if(a[a1f] <= a[a2f]) {
                        anew[i] = a[a1f++];
                    } else {
                        anew[i] = a[a2f++];
                    }
                    } else {
                    anew[i] = a[a1f++];
                }
            } else {
                anew[i] = a[a2f++];
            }
        }

        for(i=0; i<n; i++) {
            a[f+i] = anew[i];
        }
    }

    public static void MergeSort(int a[], int f, int l) {
        if(f<l){
            int m = (f+l+1)/2;
            MergeSort(a,f,m-1);
            MergeSort(a, m,l);
            Merge(a,f,l,m);
        }

        for(int i : a){
            System.out.print(i + " ");
        }
        System.out.print("\n");

    }
    public static void heapSort(int a[], int f, int l) {
        // initial print
        for(int i : a) {
            System.out.print(i + " ");
        }
        BuildHeap(a, f, l);
        for(int i = l; i > f; i--){
            System.out.println();
            for(int b : a) {
                System.out.print(b + " ");
            }
            swap(a, f, i);
            heapify(a, f, i-1, f);
        }


    }

    public static void BuildHeap(int a[], int f, int l){
        int n = l - f + 1;
        for(int i = f + (n-2) / 2; i >= f; i--){
            heapify(a, f, l, i);
        }
    }

    public static void swap(int a[], int b, int c){
        int temp = a[b];
        a[b] = a[c];
        a[c] = temp;
    }
}
