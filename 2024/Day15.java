import java.util.*;
import java.io.*;

public class Day15 {
    public static char[][] map;
    public static int size = 7;

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("input.txt"));
        String line;
        long total = 0;
        int num;

        // new vars
        map = new char[size][size*2];
        int[] pos = {-1,-1};

        //init map and pos
        num = 0;
        while (scan.hasNext()) {
            line = scan.nextLine();

            for(int i = 0; i < line.length(); i++){
                char type = line.charAt(i);

                switch(type){
                    case '.':
                        map[num][2*i] = '.';
                        map[num][2*i + 1] = '.';
                        break;
                    case '@':
                        map[num][2*i] = '@';
                        map[num][2*i + 1] = '.';
                        pos = new int[] {num, 2*i};
                        break;
                    case 'O':
                        map[num][2*i] = '[';
                        map[num][2*i + 1] = ']';
                        break;
                    case '#':
                        map[num][2*i] = '#';
                        map[num][2*i + 1] = '#';
                        break;
                }

            }

            num++;
        }
        scan.close();

        // init moves
        scan = new Scanner(new File("input2.txt"));
        line = "";
        while(scan.hasNext()){
            line += scan.nextLine();
        }

        printMap();
        System.out.println("------");

        // move things
        for(int i = 0; i < line.length(); i++){
            char dir = line.charAt(i);

            pos = move(pos,dir);

            System.out.println(dir);
            printMap();
            System.out.println("------");
        }
        
        // get total
        for(int i = 0; i < size; i++){
            for(int j = 0; j < 2*size; j++){
                if(map[i][j] == '['){
                    total += 100*i + j;
                }
            }
        }

        System.out.println(total);
        scan.close();
    }









    public static int[] move(int[] pos, char dir){
        int[] offset = null;
        switch(dir){
            case '^':
                offset = new int[] {-1,0};
                break;
            case 'v':
                offset = new int[] {1,0};
                break;
            case '<':
                offset = new int[] {0,-1};
                break;
            case '>':
                offset = new int[] {0,1};
                break;
        }

        ArrayList<int[]> targets = new ArrayList<int[]>();
        targets.add(pos);

        int[] newPos;
        boolean check = true;
        for(int i = 0; i < targets.size(); i++){
            newPos = targets.get(i);
            newPos[0] += offset[0]; newPos[1] += offset[1];

            boolean check2 = true;
            for(int[] point : targets){
                if(point[0] == newPos[0] && point[1] == newPos[1]){
                    check2 = false;
                    break;
                }
            }
            if(!check2){continue;}

            char type = map[newPos[0]][newPos[1]];
            if(type == '#'){
                check = false;
                break;
            }
            if(type == '['){
                targets.add(newPos);
                targets.add(new int[] {newPos[0],newPos[1]+1});
            }
            if(type == ']'){
                targets.add(newPos);
                targets.add(new int[] {newPos[0],newPos[1]-1});
            }
        }

        if(!check){
            return pos;
        }
        
        char[][] map2 = new char[map.length][map[0].length];
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                map2[i][j] = map[i][j];
            }
        }

        map[pos[0]][pos[1]] = '.';
        map[pos[0] + offset[0]][pos[1] + offset[1]] = '@';

        for(int i = 1; i < targets.size(); i++){
            newPos = targets.get(i);
            map[newPos[0]][newPos[1]] = '.';
        }
        for(int i = 1; i < targets.size(); i++){
            newPos = targets.get(i);
            map[newPos[0] + offset[0]][newPos[1]+offset[1]] = map2[newPos[0]][newPos[1]];
        }

        return new int[] {pos[0] + offset[0], pos[1] + offset[1]};
    }

    public static void printMap(){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}