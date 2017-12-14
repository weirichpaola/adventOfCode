import java.util.*;


public class Problema13{

    public static int counter = 0;
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        HashMap<Integer, Integer> hashTam = new HashMap<>();
        HashMap<Integer, Integer> hash = new HashMap<>();
        int max =0; 
        int delay = 0;
        while(in.hasNext()){
            String line = in.nextLine().trim();
            String[] values = line.split(":");
            hashTam.put(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
            hash.put(Integer.parseInt(values[0]), 2*(Integer.parseInt(values[1]) -1));
            max = Integer.parseInt(values[0]);
        }
        int i =0;
        boolean flag = false;
        while (i<=max){
            if(hash.containsKey(i)){           
                if((i+delay)%hash.get(i) == 0){
                    delay++;
                    flag =true;
                    //counter+= hashTam.get(i)*i;
                }else if(i==max){
                    System.out.println("delay "+ delay);
                    break;
                }
            }
            i++;
            if(flag){
                i=0;
                flag = false;
            }
           
        }

    //System.out.println("Result: "+ counter);
    }

}