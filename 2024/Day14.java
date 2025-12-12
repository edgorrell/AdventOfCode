import java.util.*;
import java.io.*;

public class Day14 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("input.txt"));
        String line;
        int modX = 101, modY = 103;

        ArrayList<int[]> bots = new ArrayList<int[]>();
        ArrayList<int[]> vels = new ArrayList<int[]>();


        while (scan.hasNext()) {
            line = scan.nextLine();
            // pos=x,y vel=x,y
            String[] arStr = line.split(" ");

            int[] arInt = new int[2];
            arInt[0] = Integer.parseInt(arStr[0].substring(arStr[0].indexOf("=")+1,arStr[0].indexOf(",")));
            arInt[1] = Integer.parseInt(arStr[0].substring(arStr[0].indexOf(",")+1,arStr[0].length()));
            bots.add(arInt);

            arInt = new int[2];
            arInt[0] = Integer.parseInt(arStr[1].substring(arStr[1].indexOf("=")+1,arStr[1].indexOf(",")));
            arInt[1] = Integer.parseInt(arStr[1].substring(arStr[1].indexOf(",")+1,arStr[1].length()));
            vels.add(arInt);
        }

        int num = 0;
        while(true){
            num++;
            for(int j = 0; j < bots.size(); j++){
                int[] bot = bots.get(j);
                int[] vel = vels.get(j);
                bot[0] += vel[0] + modX; bot[0] %= modX;
                bot[1] += vel[1] + modY; bot[1] %= modY;
            }
            //System.out.println(num + ": ");
            // print if
            if(num == 7847){
                for(int x = 0; x < modX; x++){
                    for(int y = 0; y < modY; y++){
                        boolean check = false;
                        for(int[] bot : bots){
                            if(bot[0] == x && bot[1] == y){
                                check = true;
                                break;
                            }
                        }
    
                        if(check){
                            System.out.print("X");
                        } else {
                            System.out.print(".");
                        }
                    }
                    System.out.println();
                }
                System.out.println();
                break;
            }
        }

        int[] count = new int[] {0,0,0,0};
        int sizeX = (modX-1)/2;
        int sizeY = (modY-1)/2;
        for(int[] bot : bots){
            if(bot[0] < sizeX){
                if(bot[1] < sizeY){
                    count[0]++;
                }
                if(bot[1] > sizeY){
                    count[1]++;
                }
            }
            if(bot[0] > sizeX){
                if(bot[1] < sizeY){
                    count[2]++;
                }
                if(bot[1] > sizeY){
                    count[3]++;
                }
            }
        }

        //53894148 wrong pt1
        //225648864 fixed bounds

        System.out.println(count[0] + "|" + count[1] + "|" + count[2] + "|" + count[3]);
        System.out.println(count[0]*count[1]*count[2]*count[3]);
        scan.close();
    }
}