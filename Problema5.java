import java.util.*;

public class Problema5{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int init =0;
        int steps = 0;
        String[] values = in.nextLine().split(" ");
        for(int i = 0; i< values.length;i++){
            if(values[i].charAt(0) == '('){
                init = i;
                values[i] = Character.toString(values[i].charAt(1));
                for (int j = 2; j< values[i].length();i++){
                   if(values[i].charAt(j) != ')')
                        values[i] += values[i].charAt(j);
                }
            }continue;

        }
        for(String v:values){
            System.out.print(v + " ");
        }
        int[] values2 = new int[values.length];
        for(int i = 0; i< values.length; i++){
            values2[i] = Integer.parseInt(values[i]);
        }
        for(int v:values2){
            System.out.print(v + " ");
        }
        while(true) {
            int value = Integer.parseInt(values[init]);  
            System.out.println(value);  
            if(value == 0){
                int currVal = value+1;
                values[init] = Integer.toString(currVal);
                steps++;
            }else{
                if (value < 3){
                int currVal = value+1; 
                values[init] = Integer.toString(currVal);
                init = init + (currVal-1);
                steps++;
                if(init >= values.length)
                    break;
                }else{
                    int currVal = value-1; 
                    values[init] = Integer.toString(currVal);
                    init = init + (currVal+1);
                    steps++;
                    if(init < 0)
                        break;
                }
            }
        }
        System.out.println(steps);
    }


}