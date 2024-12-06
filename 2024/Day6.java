import java.util.*;
import java.io.*;

public class Day6{
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(new File("day6.txt"));
        boolean[][] map = new boolean[130][130], map2, path;
        String line;
        int total = 0, x0 = -1, y0 = -1, x, y, dir, steps;
        
        for(int i = 0; i < map.length; i++){
            line = scan.nextLine();
            for(int j = 0; j < line.length(); j++){
                if(line.charAt(j) == '#'){
                    map[i][j] = true;
                }
                if(line.charAt(j) == '^'){
                    x0 = i; y0 = j;
                }
            }
        }
        
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map.length; j++){
                System.out.println("(" + i + "," + j + "): " + total);
                if(i == x0 && j == y0){continue;}
                if(map[i][j]){continue;}
                
                map2 = new boolean[130][130];
                for(int i2 = 0; i2 < map2.length; i2++){
                    for(int k2 = 0; k2 < map2.length; k2++){
                        map2[i2][k2] = map[i2][k2];
                    }
                }
                
                
                map2[i][j] = true;
                x = x0; y = y0; dir = 0; steps = 0;
                while(true){
                    if(steps > Math.pow(10,6)){total++; break;}
                    steps++;
                    
                    try{
                        switch(dir){
                            case 0:
                                //up
                                if(map2[x-1][y]){
                                   dir = 1; 
                                } else {
                                    x--;
                                }
                                break;
                            case 1:
                                //right
                                if(map2[x][y+1]){
                                    dir = 2;
                                } else {
                                    y++;
                                }
                                break;
                            case 2:
                                //down
                                if(map2[x+1][y]){
                                    dir = 3;
                                } else {
                                    x++;
                                }
                                break;
                            case 3:
                                //left
                                if(map2[x][y-1]){
                                    dir = 0;
                                } else {
                                    y--;
                                }
                                break;
                        }
                    } catch(Exception e){
                        break;
                    }
                }
            }
        }
        
        System.out.println(total);
    }
}