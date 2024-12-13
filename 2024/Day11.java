import java.util.*;
import java.io.*;

public class Day11 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("input.txt"));
        String line;
        long total = 0;

        ArrayList<Long> list = new ArrayList<Long>();

        line = scan.nextLine();
        String[] ar = line.split(" ");
        for (String s : ar) {
            list.add(new Long(Long.parseLong(s)));
        }
        System.out.println(list);

       
        for(Long stone : list){
            total += count(stone,75);
        }

        System.out.println(total);
        scan.close();

    }

    public static long count(long stone, int steps){
        if(steps == 0){return 1;}
        if(stone == 0){return count(1,steps-1);}
        String str = "" + stone;
        int len = str.length();
        if(len % 2 == 0){
            return
            count(Long.parseLong(str.substring(0, str.length()/2)),steps-1) +
            count(Long.parseLong(str.substring(str.length()/2,str.length())),steps-1);
        }
        return count(stone * 2024, steps - 1);
    }
}
