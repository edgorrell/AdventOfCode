import java.util.*;
import java.io.*;

public class Day5 {
    public static void main(String[] args) throws IOException{
        ArrayList<Range> list = new ArrayList<>();
        Scanner scan; 
        long total = 0;
        String line;

        scan = new Scanner(new File("2025\\input.txt"));
        while (scan.hasNextLine()) {
            line = scan.nextLine();

            long num1 = Long.parseLong(line.substring(0,line.indexOf("-")));
            long num2 = Long.parseLong(line.substring(line.indexOf("-")+1));
            
            list.add(new Range(num1, num2));
            System.out.println("" + list.get(list.size()-1));
        }

        System.out.println("-----");

        Collections.sort(list);
        Range current = list.get(0);
        
        for(Range r : list){
             if(r.min > current.max){
                System.out.println(r + ":" + (r.max - r.min));
                total += current.max - current.min + 1;
                current = r;
            }else{
                current.max = Math.max(r.max,current.max);
            }
        }
        total += current.max - current.min + 1;


        System.out.println(total);
        scan.close();
    }

    static class Range implements Comparable<Range>{
        long min, max;
        public Range(long min, long max){
            this.min = min;
            this.max = max;
        }

        public boolean contains(long num){
            return num >= min && num <= max;
        }

        @Override
        public int compareTo(Range r) {
            if(this.min > r.min){
                return 1;
            }
            if(this.min < r.min){
                return -1;
            }
            return 0;
        }

        @Override
        public String toString(){
            return min + "-" + max;
        }
    }
}
