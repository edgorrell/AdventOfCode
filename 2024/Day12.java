import java.util.*;
import java.io.*;

public class Day12 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("input2.txt"));
        String line;
        long total = 0;
        int num, size = 10;
        boolean check;

        char[][] map = new char[size][size];
        boolean[][] vis = new boolean[size][size];
        ArrayList<int[]> q;
        // area, perm
        ArrayList<int[]> regs = new ArrayList<int[]>();

        num = 0;
        while (scan.hasNext()) {
            line = scan.nextLine();
            for (int i = 0; i < line.length(); i++) {
                map[num][i] = line.charAt(i);
            }

            num++;
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // skip if in reg
                if (vis[i][j]) {
                    continue;
                }

                // setup new region
                q = new ArrayList<int[]>();
                q.add(new int[] { i, j });

                // find all points in reg
                while (true) {
                    // find non vis start
                    int[] point = q.get(0);
                    for (int k = 0; k < q.size(); k++) {
                        if (!vis[q.get(k)[0]][q.get(k)[1]]) {
                            point = q.get(k);
                            break;
                        }
                    }

                    // better check func??
                    if (point[0] == q.get(0)[0] && point[1] == q.get(0)[1] && vis[point[0]][point[1]]) {
                        break;
                    }

                    // set point vis true
                    vis[point[0]][point[1]] = true;

                    // setup
                    int[] xdif = new int[] { 1, -1, 0, 0 };
                    int[] ydif = new int[] { 0, 0, 1, -1 };

                    // add surronding
                    for (int k = 0; k < 4; k++) {
                        try {
                            if (map[point[0] + xdif[k]][point[1] + ydif[k]] == map[point[0]][point[1]]) {
                                // this is stupid but no other way works for some reason
                                check = true;
                                for (int[] pos : q) {
                                    if (pos[0] == (point[0] + xdif[k]) && pos[1] == (point[1] + ydif[k])) {
                                        check = false;
                                        break;
                                    }
                                }
                                if (check) {
                                    q.add(new int[] { point[0] + xdif[k], point[1] + ydif[k] });
                                }
                            }
                        } catch (Exception e) {
                            continue;
                        }
                    }
                }

                // q is now all points in one region
                // need to count corners by finding num of orth points from outside region
                // find x,y min/max in prev loop, make new [][]

                int corners = 0;

                // add char to area perm
                regs.add(new int[] { q.size(), corners, map[q.get(0)[0]][q.get(0)[1]] });
            }
        }

        // area * edge
        for (int[] reg : regs) {
            System.out.println((char) reg[2] + ": " + reg[0] + "," + reg[1] + "=" + reg[0] * reg[1]);
            total += reg[0] * reg[1];
        }

        System.out.println(total);
        scan.close();

    }

    public static int[] getVal(char[][] map, int i, int j) {
        int[] xdif = new int[] { 1, -1, 0, 0 };
        int[] ydif = new int[] { 0, 0, 1, -1 };

        int perm = 4;
        for (int k = 0; k < 4; k++) {
            try {
                if (map[i + xdif[k]][j + ydif[k]] == map[i][j]) {
                    perm--;
                }
            } catch (Exception e) {
                continue;
            }
        }
        return new int[] { 1, perm };
    }

    public static int getNear(char[][] map, int i, int j){
        int[] xdif = new int[] { 1, -1, 0, 0 };
        int[] ydif = new int[] { 0, 0, 1, -1 };
        int total = 0;

        for(int k = 0; k < 4; k++){
            try {
                if (map[i + xdif[k]][j + ydif[k]] == map[i][j]) {
                    total++;
                }
            } catch (Exception e) {
                continue;
            }
        }

        return total;
    }

    public static int getDiag(char[][] map, int i, int j){
        int[] xdif = new int[] { 1, 1, -1, -1 };
        int[] ydif = new int[] { 1, -1, 1, -1 };
        int total = 0;

        for(int k = 0; k < 4; k++){
            try {
                if (map[i + xdif[k]][j + ydif[k]] == map[i][j]) {
                    total++;
                }
            } catch (Exception e) {
                continue;
            }
        }

        return total;
    }
}

/*
    //pt1 perm code
    // add result to val total
    int[] val = new int[] { 0, 0 };
    for (int k = 0; k < q.size(); k++) {
        int[] result = getVal(map, q.get(k)[0], q.get(k)[1]);
        val = new int[] { val[0] + result[0], val[1] + result[1] };
    }
*/