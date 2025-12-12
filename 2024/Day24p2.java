import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day24p2 {
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(new File("input2.txt"));
        String line, op1, op2, end;//, func;
        String text = "";

        ArrayList<String> points = new ArrayList<String>();

        while (scan.hasNext()) {
            line = scan.nextLine();

            op1 = line.substring(0, line.indexOf(" "));
            line = line.substring(line.indexOf(" ") + 1, line.length());

            //func = line.substring(0, line.indexOf(" "));
            line = line.substring(line.indexOf(" ") + 1, line.length());

            op2 = line.substring(0, line.indexOf(" "));
            line = line.substring(line.indexOf(" ") + 1, line.length());

            end = line.substring(line.indexOf(">")+2, line.length());

            String[] pos = new String[] {op1,op2,end};

            for(String str : pos){
                if(points.contains(str)){ continue; }
                switch (str.charAt(0)) {
                    case 'x':
                    case 'y':
                    case 'z':
                        text += str.charAt(0) + "_{" + str.substring(1,3) + "}=\\left(" + Integer.parseInt(str.substring(1, 3)) + "," + (int)str.charAt(0) + "\\right)" + "\n";
                        break;
                    default:
                        text += str.charAt(0) + "_{" + str.substring(1,3) + "}=\\left(" + (int)str.charAt(0) + "," + (int)str.charAt(1) + "\\right)" + "\n";
                        break;
                }
                points.add(str);
            }

            text += 
                "\\operatorname{polygon}\\left(" + 
                op1.charAt(0) + "_{" + op1.substring(1,3) + "}" + "," + 
                end.charAt(0) + "_{" + end.substring(1,3) + "}" + 
                "\\right)" + "\n";

            text += 
                "\\operatorname{polygon}\\left(" + 
                op2.charAt(0) + "_{" + op2.substring(1,3) + "}" + "," + 
                end.charAt(0) + "_{" + end.substring(1,3) + "}" + 
                "\\right)" + "\n";
            
        }
        scan.close();

        Files.write(Paths.get("nodes.txt"), text.getBytes());
    }
}
