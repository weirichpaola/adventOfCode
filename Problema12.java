import java.util.*;


public class Problema12{

    public static int counter = 0;
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String[] lines =  new String[2000];
        int counter1 = 0;
        int[][] mat = new int[2000][2000];
        HashSet<Integer> hash = new HashSet<>();
        while(in.hasNext()){
            String line = in.nextLine();
            lines[counter1] = line;
            System.out.println(line);
            counter1++;
        }
        for (String l: lines){
            if(l.equals("")){
                continue;
            }
            System.out.println(l);
            String[] values = l.split("<->");
            System.out.println(values);
            String before = (values[0]).trim();
            String[] after = (values[1]).trim().split(",");

            for (String a : after){
                mat[Integer.parseInt(before)][Integer.parseInt(a)] = 1;
            }

        }

        for (int j = 0; j<2000; j++){
            if(!hash.contains(j)){
                counter++;
            readMatrix(j, mat, hash );
            }
        }
        System.out.println("counter:"+counter);

    }

    public static void readMatrix(int start, int[][] mat, HashSet<Integer> hash){
        
        for (int j = 0; j < 2000; j++) {
            if (hash.contains(j)) {
                hash.add(start);
                continue;
            } else if (mat[start][j] == 1) {
                //System.out.println(start);
                hash.add(start);
                readMatrix(j, mat, hash);
            }
        }
    }

}