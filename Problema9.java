import java.util.*;

public class Problema9{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        HashMap<String, Integer> hash = new HashMap<>();
        int groups = 0;
        String line = in.nextLine();
        boolean isGarbage = false;
        int result =0; 
        int group = 1; 
        boolean nextIsValid = true;
        for(int i = 0; i < line.length(); i++){
            if (!nextIsValid){
                nextIsValid = true;
                continue;
            }else if(line.charAt(i) == '<' && !isGarbage){
                isGarbage = true;
            }else if(line.charAt(i) == '>'){
                isGarbage = false;
            }else if(line.charAt(i) == '!'){
                nextIsValid = false;
                continue;
            }else if(isGarbage){
                result++;
            }
            // }else if( !isGarbage && line.charAt(i) == '{'){
            //     result += group;
            //     group++;
            // }else if(!isGarbage && line.charAt(i) == '}'){
            //     group--;
            // }
            System.out.println("---char: "+ line.charAt(i) +"---group: "+group + "---result: "+result);
        }
    }
}