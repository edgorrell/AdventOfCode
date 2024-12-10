import java.util.Scanner;
import java.io.*;

public class Day4{
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(new File("input.txt"));
        char[][] map = new char[140][140];
        String line;
        int num, total = 0;
        
        num = 0;
        while(scan.hasNext()){
            line = scan.nextLine();
            for(int i = 0; i < line.length(); i++){
                map[num][i] = line.charAt(i);
                System.out.print(map[num][i]);
            }
            System.out.println();
            num++;
        }
        
        for(int i = 0; i < 140; i++){
            for(int j = 0; j < 140; j++){
                if(map[i][j]  != 'A'){continue;}
                total += search2(map,i,j);
            }
        }
        System.out.println(total);
        scan.close();
    }
    
    public static int search1(char[][] map, int i0, int j0){
        //[y][x] == [i][j]
        int[] idif = new int[] {0,1,1,1,0,-1,-1,-1};
        int[] jdif = new int[] {1,1,0,-1,-1,-1,0,1};
        int total = 0;
        
        for(int k = 0; k < 8; k++){
            try{
                if(
                 map[i0 + idif[k]][j0 + jdif[k]] == 'M' &&
                 map[i0 + 2*idif[k]][j0 + 2*jdif[k]] == 'A' &&
                 map[i0 + 3*idif[k]][j0 + 3*jdif[k]] == 'S'
                ){
                    total++;
                }
            } catch(Exception e){}
        }
        
        return total;
    }
    
    public static int search2(char[][] map, int i0, int j0){
        //1-2
        //-0+
        //3+4
        int total = 0;
        
        try{
            if(
             (map[i0 - 1][j0 - 1] == 'M' && map[i0 - 1][j0 + 1] == 'M' && map[i0 + 1][j0 - 1] == 'S' && map[i0 + 1][j0 + 1] == 'S') ||
             (map[i0 - 1][j0 - 1] == 'M' && map[i0 - 1][j0 + 1] == 'S' && map[i0 + 1][j0 - 1] == 'M' && map[i0 + 1][j0 + 1] == 'S') ||
             (map[i0 - 1][j0 - 1] == 'S' && map[i0 - 1][j0 + 1] == 'M' && map[i0 + 1][j0 - 1] == 'S' && map[i0 + 1][j0 + 1] == 'M') ||
             (map[i0 - 1][j0 - 1] == 'S' && map[i0 - 1][j0 + 1] == 'S' && map[i0 + 1][j0 - 1] == 'M' && map[i0 + 1][j0 + 1] == 'M')
            ){
                total++;
            }
        } catch(Exception e){}
        
        return total;
    }
}
