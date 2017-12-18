import java.math.BigInteger;
import java.util.ArrayList;

public class Problema14{
    public static void main(String[] args){
        String line1 = "vbqugkhl-";
        String finalResult2 = "";
        String finalResult = "";
        String[] allLines = new String[128];
        for(int it = 0; it<128; it++){
            allLines[it] = line1+Integer.toString(it);
        }
       for (int k=0; k<128; k++){
            int[] list = new int[256];
            for (int i = 0; i<256; i++){
                list[i] = i;
            }
            
            int skip = 0;
            int init = 0;
            int length = 0;  
        
            ArrayList<Integer> lengths= new ArrayList<>();
            String line = allLines[k];
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

     finalResult = "";
        
        for(int h: hash){
            finalResult += String.format("%2s", Integer.toHexString(h)).replace(" ", "0");
            //finalResult2 += hexToBin(String.format("%128s", Integer.toHexString(h)).replace(" ", "0"));
        }
        // System.out.println(finalResult); 
        // System.out.println(hexToBin(finalResult));
        finalResult2 += hexToBin(finalResult);
        //System.out.println(hexToBin(finalResult).length());
       // System.out.println(finalResult2);
        
}
 
int cont =0;
for(char c: finalResult2.toCharArray()){
    if(c == '1'){
        cont++;
    }
}
int pos = 0;
String[][] mat = new String[128][128];
for(int i = 0; i< 128; i++){
    for (int j =0; j<128; j++){
        mat[i][j] = Character.toString(finalResult2.charAt(pos));
        pos++;
    }
}
// for(int i = 0; i< 10; i++){
//     for (int j =0; j<10; j++){
           
//             System.out.print(mat[i][j] + "|");
//     }
//     System.out.println();
// }

System.out.println(cont);  
int resp = 0;
for(int i = 0; i< 128; i++){
    for (int j =0; j<128; j++){
        if(mat[i][j].equals("1")){
            resp++;
             floodFill(i, j, mat, resp);
        }
    }
}



System.out.println(resp);


}

   public static void floodFill(int i, int j, String[][] mat, int resp){
        if(!(mat[i][j].equals("1"))){
            return;
         }else{   
        mat[i][j] = "0";
        //System.out.printf(" ---- side mat[%s][%s] \n", i, j);
        
        if(i+1 < 128 && mat[i+1][j].equals("1"))
            floodFill(i+1, j, mat, resp);
        if(i-1 >= 0 && mat[i-1][j].equals("1"))
            floodFill(i-1, j, mat, resp);
        if(j+1 < 128 && mat[i][j+1].equals("1"))
            floodFill(i, j+1, mat, resp);
        if (j-1 >= 0 && mat[i][j-1].equals("1"))
            floodFill(i, j-1, mat, resp);
         }
        }

   public static String hexToBin(String s) {
        String preBin = new BigInteger(s, 16).toString(2);
        preBin = String.format("%128s", preBin).replace(" ", "0");
        return preBin;
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