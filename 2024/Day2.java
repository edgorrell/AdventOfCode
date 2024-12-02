import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Day2{
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(new File("day2.txt"));
        String str;
        ArrayList<Integer> list = new ArrayList<Integer>(), list2;
        int num, total = 0;
        boolean inc, check, check2;
        
        while(scan.hasNext()){
            str = scan.nextLine();
            list.clear();
            while(str.contains(" ")){
                list.add(Integer.parseInt(str.substring(0,str.indexOf(" "))));
                str = str.substring(str.indexOf(" ")+1,str.length());
            }
            list.add(Integer.parseInt(str));
            
            check = false;
            for(int j = 0; j < list.size(); j++){
                list2 = new ArrayList<Integer>(list);
                list2.remove(j);
                
                check2 = true; inc = true;
                for(int i = 0; i < list2.size()-1; i++){
                    num = list2.get(i) - list2.get(i+1);
                    if(i == 0){
                        inc = num < 0;
                    } else {
                        if(num < 0 != inc){
                            check2 = false;
                            break;
                        }
                    }
                    if(Math.abs(num) > 3 || num == 0){
                        check2 = false;
                        break;
                    }
                }
                check |= check2;
                System.out.println(list2 + ": " + check);
            }
            System.out.println("----- " + check);
            
            if(check == true){total++;}
        }
        System.out.println(total);
    }
}