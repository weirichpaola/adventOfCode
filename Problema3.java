import java.util.*;

public class Problema3{
    static int[][] mat = new int[5000][5000];
public static void main( String[] args){
    Scanner in = new Scanner(System.in);
    
    int number = in.nextInt();
    
    int x = 2499;
    int y = 2499;
    int tamanhoLado = 1;
    mat[x][y] = 1;
    int currValue = 0;
    while (currValue < 500000){
        tamanhoLado +=2;
        //anda 1 para direita
        x=x+1;
        int curr = 0;
        curr = soma(x, y, number);
        mat[x][y] = curr;

        //anda para cima
        for(int i = 1; i< tamanhoLado-1; i++){
            y=y+1;
            curr = soma(x, y, number);
            mat[x][y] = curr;
        
        }
        //anda para esquerda
        for(int i=1; i< tamanhoLado; i++){
            x = x-1;
            curr = soma(x, y, number);
            mat[x][y] = curr;
        
        }
        //anda para baixo
        for(int i=1; i< tamanhoLado; i++){
            y = y-1;
            curr = soma(x, y,number);
            mat[x][y] = curr;
        
        }
        //anda para a direita
        for(int i=1; i< tamanhoLado; i++){
            x = x+1;
            curr = soma(x, y, number);
            mat[x][y] = curr;
        
        }
        currValue = curr;
    }
    
    
}

public static int soma(int x, int y, int number){
    int currSum = 0; 
     currSum+= mat[x-1][y] + mat[x+1][y] + mat[x+1][y+1] + mat[x+1][y-1] + mat[x-1][y-1] + mat[x-1][y+1] + mat[x][y+1] + mat[x][y-1];
     if (currSum > number){
        System.out.println("Resultado: " + currSum);
        System.exit(0);
    }

    return currSum;
}

}
