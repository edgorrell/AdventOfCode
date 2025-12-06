import java.util.*;
import java.io.*;

public class Day6{
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(new File("2025//input.txt"));
        String line;
        long total = 0;

        int rank = 5, length = 1000;
        String[][] nums = new String[rank-1][length];
        char[] ops = new char[length];
        int[] indexes = new int[length];

        //operations
        line = scan.nextLine();
        int sum = 0;
        for(int i = 0; i < length; i++){
            int num1 = line.indexOf("+");
            int num2 = line.indexOf("*");
            int index;

            if(num1 == -1 || num2 == -1){
                index = Math.max(num1,num2);
            } else {
                index = Math.min(num1,num2);
            }

            indexes[i] = index + sum;
            ops[i] = line.charAt(indexes[i]-sum);
            line = line.substring(index + 1);
            sum += indexes[i] + 1 - sum;
        }
        //numbers
        for(int i = 0; i < rank - 1; i++){
            line = scan.nextLine();
            for(int j = 1; j < length; j++){
                line = line.substring(0,indexes[j]-1) + "|" + line.substring(indexes[j]);
            }
            line = line.replaceAll(" ", "0");
            System.out.println(line);

            nums[i] = line.split("\\|");
        }
        //math
        String temp;
        long num;
        char c;
        for(int i = 0; i < length; i++){
            switch(ops[i]){
                case '+':
                    num = 0;
                    for(int j = 0; j < nums[0][i].length(); j++){
                        temp = "";
                        for(int k = 0; k < rank - 1; k++){
                            c = nums[k][i].charAt(j);
                            if(c == '0' && c == '0' && !temp.equals("")){continue;}
                            temp = temp + c;
                        }
                        num += Long.parseLong(temp);
                    }
                    total += num;
                    break;
                case '*':
                    num = 1;
                    for(int j = 0; j < nums[0][i].length(); j++){
                        temp = "";
                        for(int k = 0; k < rank - 1; k++){
                            c = nums[k][i].charAt(j);
                            if(c == '0' && !temp.equals("")){continue;}
                            temp = temp + c;
                        }
                        num *= Long.parseLong(temp);
                    }
                    total += num;
                    break;
            }
        }

        System.out.println("-----");
        System.out.println(total);
        scan.close();
    }
}