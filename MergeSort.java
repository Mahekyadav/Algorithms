public class MergeSort {
    void sort(int[] arr, int start, int end) {
        if (start<end) {
            int mid = start + (end - start)/2;
            sort(arr, start, mid);
            sort(arr, mid + 1, end);
            merge(arr, start, mid, end);

        }
    }
    void merge(int[] a, int start, int mid, int end) {
        int i, j, k=0;
        int[] R = new int[end - start + 1];
        i = start;
        j = mid + 1;
        while (i<=mid && j<=end ) {
            if(a[i] <= a[j]) {
                R[k++] = a[i++];
                
            }
            else {
                R[k++] = a[j++];
            }
        }
        while (i<=mid) { 
                R[k++]=a[i++];
            }
        while (j <= end) { 
            R[k++] = a[j++]; 
        }
        

            for (int n=0; n<R.length; n++) {
                a[start + n] = R[n];
            }
        }
        static void printArray(int[] arr) {
            for(int i=0; i<arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }

    

    public static void main(String arg[]) {
        int arr[] = {38, 27, 43, 3, 9, 82, 10};
        int n = arr.length;
        System.out.println("Original Array");
        printArray(arr);
        MergeSort ob = new MergeSort();
        ob.sort(arr, 0, n-1 );
        System.out.println("Sorted Array");
        printArray(arr);
    }

}