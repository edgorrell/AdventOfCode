import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.*;
import java.io.*;

public class Day3{
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(new File("input.txt"));
        ArrayList<String> list = new ArrayList<String>();
        String line, str, rex;
        int num1, num2, total = 0;
        boolean check;
        
        line = scan.nextLine();
        rex = "mul\\([0-9]+,[0-9]+\\)|do\\(\\)|don't\\(\\)";
        Matcher m = Pattern.compile(rex).matcher(line);
        while(m.find()){
            list.add(m.group());
            System.out.println(list.get(list.size()-1));
        }
        check = true;
        for(int i = 0; i < list.size(); i++){
            str = list.get(i);
            if(str.equals("do()")){
                check = true;
                continue;
            }
            if(str.equals("don't()")){
                check = false;
                continue;
            }
            if(check){
                num1 = Integer.parseInt(str.substring(str.indexOf("(") + 1, str.indexOf(",")));
                num2 = Integer.parseInt(str.substring(str.indexOf(",") + 1, str.indexOf(")")));
                total += num1 * num2;
                System.out.println(str + ": " + num1 + ", " + num2);
            }
        }
        
        System.out.println(total);
        scan.close();
    }
}
