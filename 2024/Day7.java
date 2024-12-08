import java.util.*;
import java.io.*;

public class Day7{
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(new File("day7.txt"));
        String line, str;
        ArrayList<Integer> list;
        long total = 0, num;

        while(scan.hasNext()){
            line = scan.nextLine();
            
            num = Long.parseLong(line.substring(0,line.indexOf(":")));
            str = line.substring(line.indexOf(":")+2,line.length());
            
            list = new ArrayList<Integer>();
            for(String val : str.split(" ")){
                list.add(Integer.parseInt(val));
            }
            
            for(int i = 0; i < Math.pow(3,list.size()-1); i++){
                long test = eval(list,i); 
                if(test == (num)){
                    total += num;
                    break;
                }
            }
        }
        scan.close();

        System.out.println(total);
        System.out.println("61561126043536");
    }
    
    public static long eval(ArrayList<Integer> nums, int i){
        String ops = toBase(i,3), pad = "00000000000000000000";
        ops = pad.substring(0,nums.size()-ops.length()-1) + ops;

        long total = nums.get(0);
        //System.out.print(total);
        for(int j = 0; j < ops.length(); j++){
            if(ops.charAt(j) == '0'){
                total = Long.parseLong(total + "" + nums.get(j+1));
                //System.out.print("||");
            } else if (ops.charAt(j) == '1'){
                total *= nums.get(j+1);
                //System.out.print("*");
            } else {
                total += nums.get(j+1);
                //System.out.print("+");
            }
            //System.out.print(nums.get(num));
        }
        //System.out.println();
        return total;
    }

    // its different if order is different?
    //61537436584106: 012
    //61537234654659: 210
    //61561126043536 <-

    public static String toBase(int i, int base){
        long result = 0;
        int factor = 1;

        while(i > 0){
            result += i % base * factor;
            i /= base;
            factor *= 10;
        }
        
        return "" + result;
    }
}
