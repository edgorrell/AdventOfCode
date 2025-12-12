import java.util.*;
import java.io.*;

public class Day25{
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(new File("input.txt"));
        String line;

        ArrayList<int[]> locks = new ArrayList<int[]>();
        ArrayList<int[]> keys = new ArrayList<int[]>();
        
        while(scan.hasNext()){
            line = scan.nextLine();
            char match = line.charAt(0);

            int[] heights = new int[] {0,0,0,0,0};

            for(int i = 0; i < 6; i++){
                line = scan.nextLine();
                for(int j = 0; j < line.length(); j++){
                    if(line.charAt(j) == match){
                        heights[j]++;
                    }
                }
            }
            if(match == '.'){
                for(int j = 0; j < heights.length; j++){
                    heights[j] = 5 - heights[j];
                }
            }
           
            switch (match) {
                case '.':
                    keys.add(heights);
                    break;
                case '#':
                    locks.add(heights);
                    break;
            }

            try {
                scan.nextLine();
            } catch (Exception e) {
                
            }
        }
        scan.close();

        int total = 0;
        for(int[] key : keys){
            for(int[] lock : locks){
                boolean check = true;
                for(int i = 0; i < 5; i++){
                    if(key[i] + lock[i] > 5){
                        check = false;
                        break;
                    }
                }
                if(check){
                    total++;
                }
            }
        }




        System.out.println(total);
    }
}