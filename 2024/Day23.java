import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day23 {
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(new File("input.txt"));
        String line;

        // used matematica for pt2
        String text = "g = Graph[{";
        while(scan.hasNext()){
            line = scan.nextLine();
            String n1 = line.substring(0,line.indexOf("-"));
            String n2 = line.substring(line.indexOf("-")+1,line.length());
            text += n1 + "<->" + n2 + ",";
        }
        text = text.substring(0,text.length()-1);
        scan.close();
        text += "}]";
        Files.write(Paths.get("nodes.txt"), text.getBytes());
    }
}
