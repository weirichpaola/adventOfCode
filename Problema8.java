import java.util.*;

public class Problema8{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        HashMap<String, Integer> hash = new HashMap<>();
        int maxValue = 0;
        while(in.hasNext()){
            String line = in.nextLine();
            String[] lines = line.split(" ");
            String var1 = lines[0];
            String op = lines[1];
            int value = Integer.parseInt(lines[2]);
            String var2 = lines[4];
            String condition = lines[5];
            int valueCondition = Integer.parseInt(lines[6]);
            int valueVar1 = 0;
            int valueVar2 = 0;
            
            if(hash.containsKey(var1)){
                valueVar1 = hash.get(var1);
            }else{
                hash.put(var1, valueVar1);
            }
            if(hash.containsKey(var2)){
                valueVar2 = hash.get(var2);
            }else{
                hash.put(var2, valueVar2);
            }
            for (String k:hash.keySet()){
                System.out.print(k+":" +hash.get(k)+ "\n");

            }

            int currValue = 0;
            if (check(var2, condition, valueCondition, hash)){
                currValue = operation(var1, op, value, hash);
            }

            if(maxValue < currValue){
                maxValue = currValue;
            }
        }

        System.out.println(maxValue);
    }

    public static boolean check(String var, String condition, int value, HashMap<String, Integer> hash){
        switch(condition){
            case ">":
                return hash.get(var) > value;
            case "<":
                return hash.get(var) < value;
            case ">=":
                return hash.get(var) >= value;
            case "<=":
                return hash.get(var) <= value;
            case "==":
                return hash.get(var) == value;
            case "!=":
                return hash.get(var) != value;
        }
        return false;
    }

    public static int operation(String var, String op, int value, HashMap<String, Integer> hash){
        int newValue = hash.get(var);
        if (op.equals("inc")){
            newValue += value;
            hash.put(var, newValue);
        }else{
            newValue -= value;
            hash.put(var, hash.get(var) - value);
        }

        return newValue;
    }


}