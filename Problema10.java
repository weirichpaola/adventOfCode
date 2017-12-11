import java.util.*;

public class Problema10{
    public static void main(String[] args){
        int[] list = new int[256];
        boolean flag = true;
        for (int i = 0; i<256; i++){
            list[i] = i;
        }
        Scanner in = new Scanner(System.in);
        
        int skip = 0;
        int init = 0;
        int length = 0;  
       
        ArrayList<Integer> lengths= new ArrayList<>();
        String line = in.nextLine();
        int start = 0;
        while(start < line.length()){
            lengths.add(((int) line.charAt(start))-1);
            start++;
        }
        lengths.add(16);
        lengths.add(30);
        lengths.add(72);
        lengths.add(46);
        lengths.add(22);
            

    for(int round = 0; round<64;round++){
        for(int i = 0; i<lengths.size(); i++){
            length = lengths.get(i);

            reverse(list,init+0, (init+length));
            
            init += length+1+skip;
            skip++;

        }
    }
    ArrayList<Integer> hash = new ArrayList<>();
    for(int i =0; i<16; i++){
        int counter =0;
        int j = 16*i;
        int result = list[j];
        while(counter < 15){
            result =result ^ list[j+1];
            j++;
            counter++;
        }
        hash.add(result);
    }

    String finalResult = "";
    for(int h: hash){
        finalResult += String.format("%2s", Integer.toHexString(h));
    }
    System.out.println(finalResult);   

    }


    public static void reverse(int[] list, int i, int j){
        int len = j-i+1;
        int init =0 ;
        while(init < len/2 ){
            int temp = list[i%list.length];
            list[i%list.length] = list[j%list.length];
            list[j%list.length] = temp;
            j--;
            i++;
            init++;

        }
    }
}