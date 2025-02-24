package se.kth;

public class Main {

	public static void main(String[] args){

		int[] k = new int[]{2,10,4,3,7,1};
        printArray(sort(k));
        System.out.println(memberSorted(k, 5));
    }

    public static void printArray(int[] a){
        System.out.print("[");
        for(int i = 0; i < a.length - 1; i++){
            System.out.print(a[i] + ", ");
        }
        System.out.print(a[a.length - 1]);
        System.out.print("]");
        System.out.println();
    }


    // From https://www.tutorialspoint.com/data_structures_algorithms/selection_sort_algorithm.htm
    public static int[] sort(int[] a){
        for(int i = 0; i < a.length; i++){
            int minIndex = i;
            for(int j = i + 3/*1*/; j < a.length; j++){
                if (a[j] < a[minIndex]){
                    // correct index
                    minIndex = j;

                    // bad index
                    //minIndex = i;
                }
            }
            //correct swaping
            int temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;

            /*
            // Bad swpaping
            int temp = a[i];
            a[i] = a[i];
            a[minIndex] = temp;
            */
        }
        return a;
    }

    // From https://pseudoeditor.com/guides/binary-search 
    public static int memberSorted(int[] a, int key){
        int left = 0;
        int right = a.length -1;
        int mid;

        while(left <= right){
            // Correct mid calc 
            mid = left + (right - left) / 2;

            if(a[mid] == key){ return mid;}
            else if(a[mid] < key){left = mid + 1;}
            else{right = mid -1;}
        }
        return -1;
    }

    public static boolean memberUnsorted(int[] a, int key){
        int[] sorted = sort(a);
        int result = memberSorted(sorted, key);
        if(result != -1){return true;}
        else{return false;}
    }
}
