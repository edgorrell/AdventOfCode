import java.util.*;
import java.io.*;

public class Day1{
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(new File("2025/input.txt"));
        String str;
        ArrayList<Integer> list = new ArrayList<Integer>(), list2;
        int num, total = 0;
        boolean check;
        
        while(scan.hasNext()){
            str = scan.nextLine();
            
            for(int i = 0; i < str.length(); i++){
                total++;
            }
        }

        
        System.out.println(total);
        scan.close();
    }
}