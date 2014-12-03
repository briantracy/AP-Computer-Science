import java.util.ArrayList;
import java.util.Arrays;

public class MedianFinder
{


    public static void main(String[] args)
    {
        int[] t = new int[] {
                10, 11, 12, 13, 14, 15
        };

       swap(t, 0, 3);

        System.out.println(Arrays.toString(t));

    }




    private static void quicksort(int[] array)
    {
        Arrays.sort(array);
    }



    private static void swap(int[] array, int i, int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;

    }

}

