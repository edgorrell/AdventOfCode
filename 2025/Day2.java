import java.util.*;
import java.io.*;

public class Day2 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("2025\\input.txt"));
        long total = 0;

        for (String range : scan.nextLine().split(",")) {
            long min = Long.parseLong(range.substring(0, range.indexOf("-")));
            long max = Long.parseLong(range.substring(range.indexOf("-") + 1, range.length()));

            for (long num = min; num <= max; num++) {
                String str = "" + num;

                for (int length = 1; length < str.length()/2 + 1; length++) {
                    if (str.length() % length != 0) {
                        continue;
                    }

                    boolean check = true;

                    for (int pos = 0; pos < str.length() / length; pos++) {
                        String pattern = str.substring(length * pos, length * (pos + 1));
                        if (!pattern.equals(str.substring(0, length))) {
                            check = false;
                            break;
                        }
                    }

                    if (check) {
                        total += num;
                        break;
                    }
                }
            }
        }

        System.out.println(total);
        scan.close();
    }
}