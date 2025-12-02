import java.util.*;
import java.io.*;

public class Day1{
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(new File("2025\\input.txt"));
        String str;
        int pos = 50, num, sign = 0;
        
        int total = 0;
        while(scan.hasNext()){
            str = scan.nextLine();
            num = Integer.parseInt(str.substring(1, str.length()));

            switch(str.charAt(0)){
                case 'L':
                    sign = -1;
                    break;
                case 'R':
                    sign = 1;
                    break;
            }

            for(int i = 0; i < num; i++){
                pos += sign;
                if(pos % 100 == 0){
                    total++;
                }
            }
        }
        

        System.out.println(total);
        scan.close();
    }
}