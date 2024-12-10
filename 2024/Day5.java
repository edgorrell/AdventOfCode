import java.util.*;
import java.io.*;

public class Day5{
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(new File("input.txt"));
        ArrayList<Pair> pairs = new ArrayList<Pair>();
        ArrayList<Integer> list;
        Pair pair;
        String line;
        int total = 0;
        
        while(scan.hasNext()){
            line = scan.nextLine();
            pair = new Pair(
             Integer.parseInt(line.substring(0,line.indexOf("|"))),
             Integer.parseInt(line.substring(line.indexOf("|")+1, line.length()))
            );
            pairs.add(pair);
        }
        scan.close();
        
        scan = new Scanner(new File("input2.txt"));
        while(scan.hasNext()){
            list = new ArrayList<Integer>();
            line = scan.nextLine();
            
            while(line.contains(",")){
                list.add(Integer.parseInt(line.substring(0,line.indexOf(","))));
                line = line.substring(line.indexOf(",")+1,line.length());
            }
            list.add(Integer.parseInt(line));
            
            if(!checkList(pairs,list)){
                total += fix(pairs,list);
            }
        }
        
        System.out.println(total);
        scan.close();
    }
    
    public static int fix(ArrayList<Pair> pairs, ArrayList<Integer> nums){
        while(!checkList(pairs,nums)){
            for(int i = 0; i < nums.size(); i++){
                if(!checkNum(pairs,nums,i)){
                    Collections.swap(nums, i, i+1);
                }
            }
        }
        
        return nums.get((int) nums.size()/2);
    }
    
    public static boolean checkList(ArrayList<Pair> pairs, ArrayList<Integer> nums){
        boolean check = true;
        for(int i = 0; i < nums.size(); i++){
            check &= checkNum(pairs,nums,i);
        }
        
        return check;
    }
    
    public static boolean checkNum(ArrayList<Pair> pairs, ArrayList<Integer> nums, int index){
        int num = nums.get(index);
                
        for(int i = 0; i < nums.size(); i++){
            for(Pair pair : pairs){
                if(pair.num2 != num){continue;}
                if(nums.subList(index,nums.size()).contains(pair.num1)){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public static class Pair{
        public int num1, num2;
        
        public Pair(int num1, int num2){
            this.num1 = num1;
            this.num2 = num2;
        }
    }
}
