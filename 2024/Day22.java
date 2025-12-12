import java.util.*;
import java.io.*;

public class Day22 {
    // why does this work lol
    // like it got the example seq wrong but both nums right
    // for pt2 at least
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("input.txt"));
        String line;
        long total = 0;
        ArrayList<Long> starts = new ArrayList<Long>();

        while (scan.hasNext()) {
            line = scan.nextLine();
            starts.add(Long.parseLong(line));
        }
        scan.close();

        long max = 0;
        int[] best = new int[4];
        // check all seq of change: 19^4, -9 -> 9
        for (int index = 0; index < (int) (Math.pow(19, 4)); index++) {
            int[] target = new int[] {
                    (int) ((1 / Math.pow(19, 3)) * (index - (index % (int) Math.pow(19, 3))) - 9),
                    (int) ((1 / Math.pow(19, 2)) * ((index % (int) Math.pow(19, 3)) - (index % (int) Math.pow(19, 2))) - 9),
                    (int) ((1 / Math.pow(19, 1)) * ((index % (int) Math.pow(19, 2)) - (index % (int) Math.pow(19, 1))) - 9),
                    (int) ((1 / Math.pow(19, 0)) * ((index % (int) Math.pow(19, 1)) - (index % (int) Math.pow(19, 0))) - 9),
            };
            // System.out.println(seq[0] + "|" + seq[1] + "|" + seq[2] + "|" + seq[3]);

            int[] nums, change;
            total = 0;
            for (long num : starts) {
                int digit = (int) num % 10;

                nums = new int[] {digit,-10,-10,-10};
                change = new int[4];

                for (int i = 0; i < 2000; i++) {
                    num ^= (num * 64);
                    num %= 16777216;

                    num ^= (num / 32);
                    num %= 16777216;

                    num ^= (num * 2048);
                    num %= 16777216;

                    digit = (int) num % 10; 
                    change[3] = nums[2]-nums[3]; nums[3] = nums[2];
                    change[2] = nums[1]-nums[2]; nums[2] = nums[1];
                    change[1] = nums[0]-nums[1]; nums[1] = nums[0];
                    change[0] = digit-nums[0]; nums[0] = digit;
                
                    if(Arrays.equals(target, change)){
                        total += digit;
                        best = change;
                        break;
                    }
                }
            }

            if (total > max) {
                max = total;
            }
            System.out.println(index/(Math.pow(19, 4)) * 100);
        }

        System.out.println(Arrays.toString(best));
        System.out.println(max);
    }
    // 18261820068
}
