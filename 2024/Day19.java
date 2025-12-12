import java.util.*;
import java.io.*;

public class Day19{
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(new File("input.txt"));
        String line;

        String[] parts = scan.nextLine().split(", ");
        scan.close();

        scan = new Scanner(new File("input2.txt"));
        int total = 0, index = 0;
        while(scan.hasNext()){
            index++;
            line = scan.nextLine();
            if(find(parts, line,"",0,index)){
                total++;
            }
            System.out.println(index);
        }
        scan.close();

        System.out.println(total);
    }

    public static boolean find(String[] parts, String target, String temp, int depth, int index){
        if(temp.equals(target)){
            return true;
        }

        System.out.print(index + ": ");
        for(int i = 0; i < depth; i++){
            System.out.print("-");
        }
        System.out.println();
        
        for(int i = 0; i < parts.length; i++){
            String temp2 = temp + parts[i];
            
            if(temp2.length() > target.length()){
                continue;
            }
            if(target.indexOf(temp2) != 0){
                continue;
            }
            if(find(parts,target,temp2, depth++, index)){
                return true;
            };
        }
        return false;
    }
}