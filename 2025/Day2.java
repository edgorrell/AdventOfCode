
import java.util.*;
import java.io.*;

public class Day2 {
    static ArrayList<Long> list;
    static String line, range;
    static long total = 0;

    public static void main(String[] args) throws IOException {
        System.out.println(new File(".").getAbsolutePath());

        Scanner scan = new Scanner(new File("2025\\input.txt"));
        list = new ArrayList<>();

        line = scan.nextLine();

        while (line.contains(",")) {
            range = line.substring(0, line.indexOf(","));
            line = line.substring(line.indexOf(",") + 1, line.length());

            findInvalid(range);
        }

        range = line;
        findInvalid(range);

        System.out.println("-----");
        System.out.println(total);
        scan.close();
    }

    static void findInvalid(String range){
        long min = Long.parseLong(range.substring(0, range.indexOf("-")));
        long max = Long.parseLong(range.substring(range.indexOf("-") + 1, range.length()));

        for (long num = min; num < max + 1; num++) {
            String str = "" + num;

            for(int length = 1; length < str.length(); length++){
                if(str.length() % length != 0){continue;}

                boolean check = true;
                for(int pos = 0; pos < str.length()/length; pos++){
                    if(!str.substring(length*pos,length*(pos+1)).equals(str.substring(0,length))){
                        check = false;
                    }
                }

                if(check && !list.contains(num)){
                    total += num;
                    list.add(num);
                    System.out.println(min + "|" + max + ":" + num);
                }
            }
        }
    }
}