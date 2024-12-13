import java.util.*;
import java.io.*;
import java.math.*;

public class Day13 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("input2.txt"));
        String line;
        long total = 0;

        int num = 0;
        while (scan.hasNext()) {
            num++;

            // setup vars
            line = scan.nextLine();
            long[] a = new long[] {
                    Integer.parseInt(line.substring(line.indexOf("X+") + 2, line.indexOf(","))),
                    Integer.parseInt(line.substring(line.indexOf("Y+") + 2, line.length()))
            };

            line = scan.nextLine();
            long[] b = new long[] {
                    Integer.parseInt(line.substring(line.indexOf("X+") + 2, line.indexOf(","))),
                    Integer.parseInt(line.substring(line.indexOf("Y+") + 2, line.length()))
            };

            line = scan.nextLine();
            long px = (long) Math.pow(10, 15) + Integer.parseInt(line.substring(line.indexOf("X=") + 2, line.indexOf(",")));
            long py = (long) Math.pow(10, 15) + Integer.parseInt(line.substring(line.indexOf("Y=") + 2, line.length()));

            // math stuff, found with desmos solve for a
            MathContext mc = MathContext.DECIMAL128;

            BigDecimal det = new BigDecimal(a[1]).multiply(new BigDecimal(b[0])).subtract(new BigDecimal(a[0]).multiply(new BigDecimal(b[1])));
            BigDecimal a1 = new BigDecimal(b[0]).multiply(new BigDecimal(py)).subtract(new BigDecimal(b[1]).multiply(new BigDecimal(px))).divideToIntegralValue(det);
            BigDecimal b1 = new BigDecimal(px).subtract(a1.multiply(new BigDecimal(a[0]))).divideToIntegralValue(new BigDecimal(b[0]));

            BigDecimal calcx = a1.multiply(new BigDecimal(px)).add(b1.multiply(new BigDecimal(px)));
            BigDecimal calcy = a1.multiply(new BigDecimal(py)).add(b1.multiply(new BigDecimal(py)));
            
            if(
                new BigDecimal(px).subtract(a1.multiply(new BigDecimal(a[0]))).remainder(new BigDecimal(b[0])).equals(BigDecimal.ZERO)
            ){
                System.out.println(num);
                total += 3*a1.longValue()+b1.longValue();
            }

            System.out.println("----");
            try {
                scan.nextLine();
            } catch (Exception e) {}
        }

        System.out.println(total);
        scan.close();
    }
}