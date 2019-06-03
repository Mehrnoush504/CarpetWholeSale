
/**
 *
 * @author Mehrnoush
 */
import java.util.*;
public class QuickSort1 {
    
    public static int partition(int []a,int start,int end){
        int pivot = a[end];
        int i,j,pi;
        Vector left = new Vector();
        Vector right = new Vector();
        
        for(i= start;i<=(end -1);i++){
            
            if(a[i]<=pivot)
                left.add(a[i]);
            else
                right.add(a[i]);
        }
        i = start;
        for(j=0;j<left.size();j++)
        {
            a[i++] = (int) left.get(j);
        }
        pi = i;
        a[i++] = pivot;
        for(j=0;j<right.size();j++){
            a[i++] = (int) right.get(j);
        }
        return pi;
    }
    
    public static void quickSort(int[]a,int start,int end){
        if(start<end){

            int pivot = partition(a,start,end);
            quickSort(a, start, pivot-1);//befor pivot
            quickSort(a, pivot+1, end);//after pivot
            
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        java.util.Scanner input = new java.util.Scanner(System.in);
        int n = input.nextInt();
        int []a = new int[n];
        for(int i=0;i<n;i++)
        {
            a[i]=input.nextInt();
        }
       quickSort(a,0,n-1);
       for(int i=0;i<n;i++)
        {
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }
    
}
