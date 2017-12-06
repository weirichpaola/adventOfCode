import java.lang.reflect.Array;
import java.util.*;

public class Problema6{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int finalVal = 0;
        boolean flag = true;
        String[] values = in.nextLine().split("\t");
        System.out.println(Arrays.toString(values));
        HashSet<String> found = new HashSet<>();
        String result = Arrays.toString(values);
        while (!found.contains(result)) {
            found.add(result);
            int max = 0;
            int maxPos = 0;
            for (int i = 0; i < values.length; i++) {
                if (Integer.parseInt(values[i]) > max) {
                    max = Integer.parseInt(values[i]);
                    maxPos = i;
                }
            }
            int howMuchOthers = 0;
            int howMuchMine = 0;
            if (max < values.length-1){
                howMuchMine = 0;
                howMuchOthers = 1;
            }else{
                if (max % values.length != 0) {
                    howMuchOthers = (max / values.length) + 1;
                    howMuchMine = howMuchOthers - 1;
                } else {
                    howMuchOthers = (max / values.length);
                    howMuchMine = (max / values.length);
                }
            }

            values[maxPos] = Integer.toString(howMuchMine);

                for (int i = maxPos+1; i < values.length; i++) {
                    if (max > 0){
                        int aux = Integer.parseInt(values[i]) + howMuchOthers;
                        values[i] = Integer.toString(aux);
                    }
                    max-=howMuchOthers;
                }
                for(int i=0; i < maxPos;i++){
                    if (max > 0){
                        int aux = Integer.parseInt(values[i]) + howMuchOthers;
                        values[i] = Integer.toString(aux);
                    }
                    max-=howMuchOthers;
                }


            result = Arrays.toString(values);
            System.out.println(result);
            //first time found
            if (found.contains(result) && flag){
                found.clear();
                flag = false;
                found.add(result);
                finalVal = -1;
                result ="";

            }
            finalVal++;
        }

        System.out.println(finalVal);
    }


}