import java.util.*;
import java.io.*;

class Day24 {
    static ArrayList<String> ends;
    static ArrayList<String> list;
    static ArrayList<Integer> nums;
    static long xval, yval;

    public static void main(String[] args) throws IOException {
        // setup vars
        list = new ArrayList<String>();
        nums = new ArrayList<Integer>();
        String line;
        int num;

        // read setup
        Scanner scan = new Scanner(new File("input.txt"));
        while (scan.hasNext()) {
            line = scan.nextLine();
            if (line.equals("")) {
                break;
            }
            list.add(line.substring(0, line.indexOf(":")));
            nums.add(Integer.parseInt(line.substring(line.indexOf(" ") + 1, line.length())));
        }
        scan.close();

        // get x and y vals
        xval = 0;
        yval = 0;
        for (String wire : list) {
            if (wire.charAt(0) == 'x') {
                num = nums.get(list.indexOf(wire)).intValue();
                xval += num * Math.pow(2, Integer.parseInt(wire.substring(1, 3)));
            }
            if (wire.charAt(0) == 'y') {
                num = nums.get(list.indexOf(wire)).intValue();
                yval += num * Math.pow(2, Integer.parseInt(wire.substring(1, 3)));
            }
        }

        // read endpoints
        scan = new Scanner(new File("input2.txt"));
        ends = new ArrayList<String>();
        String end;
        while (scan.hasNext()) {
            line = scan.nextLine();
            end = line.substring(line.indexOf(">") + 2, line.length());
            ends.add(end);
        }
        scan.close();

        // mix alg
        ArrayList<Integer> ints = new ArrayList<Integer>();
        for (int i = 0; i < ends.size(); i++) {
            ints.add(i);
        }
        perm(ints, 0, 8);
    }

    // perm through each subset, check
    static void perm(ArrayList<Integer> list, int i, int k) throws IOException {
        if (i == k) {
            solve(new ArrayList<Integer>(list.subList(0, k)));
            return;
        }

        for (int j = i; j < list.size(); j++) {
            Collections.swap(list, i, j);
            perm(list, i + 1, k);
            Collections.swap(list, i, j);
        }
    }

    // check alg
    static void solve(ArrayList<Integer> ins) throws IOException {
        // setup vars
        Scanner scan;
        ArrayList<String> ends2 = new ArrayList<String>(ends);
        String func, op1, op2, line, end;
        int steps = 0, num;

        Collections.swap(ends2, ins.get(0), ins.get(1));
        Collections.swap(ends2, ins.get(2), ins.get(3));
        Collections.swap(ends2, ins.get(4), ins.get(5));
        Collections.swap(ends2, ins.get(6), ins.get(7));

        while (true) {
            steps++;
            scan = new Scanner(new File("input2.txt"));
            num = 0;

            if (steps > 300) {
                System.out.print(steps);
                break;
            }

            int index = 0;
            while (scan.hasNext()) {
                line = scan.nextLine();

                op1 = line.substring(0, line.indexOf(" "));
                line = line.substring(line.indexOf(" ") + 1, line.length());

                func = line.substring(0, line.indexOf(" "));
                line = line.substring(line.indexOf(" ") + 1, line.length());

                op2 = line.substring(0, line.indexOf(" "));
                line = line.substring(line.indexOf(" ") + 1, line.length());

                end = ends2.get(index);

                if (!list.contains(op1) || !list.contains(op2)) {
                    num++;
                    continue;
                }

                int num1, num2, result = -1;
                if (func.equals("AND")) {
                    num1 = nums.get(list.indexOf(op1)).intValue();
                    num2 = nums.get(list.indexOf(op2)).intValue();
                    result = num1 & num2;
                }
                if (func.equals("XOR")) {
                    num1 = nums.get(list.indexOf(op1)).intValue();
                    num2 = nums.get(list.indexOf(op2)).intValue();
                    result = num1 ^ num2;
                }
                if (func.equals("OR")) {
                    num1 = nums.get(list.indexOf(op1)).intValue();
                    num2 = nums.get(list.indexOf(op2)).intValue();
                    result = num1 | num2;
                }

                if (!list.contains(end)) {
                    list.add(end);
                    nums.add(result);
                } else {
                    nums.set(list.indexOf(end), result);
                }
            }

            if (num == 0) {
                System.out.print(steps);
                break;
            }
            index++;
        }
        scan.close();
        System.out.println(": " + ins);

        // get zval
        long total = 0;
        for (String wire : list) {
            if (wire.charAt(0) == 'z') {
                num = nums.get(list.indexOf(wire)).intValue();
                total += num * Math.pow(2, Integer.parseInt(wire.substring(1, 3)));
            }
        }

        //check and print
        if (xval + yval == total) {
            for(int i = 0; i < ins.size(); i++){
                System.out.print(ends.get(ins.get(i)) + ",");
            }
        }
    }
}