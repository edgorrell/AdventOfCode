import java.util.*;
import java.io.*;

public class Day4 {
    final static int size = 138;
    static char[][] map = new char[size][size];
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("2025\\input.txt"));

        String line;
        long total = 0;

        for (int i = 0; i < size; i++) {
            line = scan.nextLine();
            for (int j = 0; j < size; j++) {
                map[i][j] = line.charAt(j);
                System.out.print(map[i][j]);

                if (map[i][j] == '@') {
                    total++;
                }
            }
            System.out.println();
        }
        System.out.println("-----");

        while (count(map) != count(remove(map))) {
            map = remove(map);
        }

        System.out.println(total - count(map));
        scan.close();
    }

    static char[][] remove(char[][] input) {
        char[][] output = input.clone();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (output[i][j] != '@') {
                    continue;
                }

                int[] dx = { 1, 1, 0, -1, -1, -1, 0, 1 };
                int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
                int near = 0;

                for (int k = 0; k < 8; k++) {
                    try {
                        if (output[i + dx[k]][j + dy[k]] == '@' || output[i + dx[k]][j + dy[k]] == 'x') {
                            near++;
                        }
                    } catch (Exception e) {
                    }
                }

                if (near < 4) {
                    output[i][j] = 'x';
                }
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(output[i][j]);

                if (output[i][j] == 'x') {
                    output[i][j] = '.';
                }
            }
            System.out.println();

        }
        System.out.println("-----");

        return output;
    }

    static int count(char[][] map){
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j]);
                if (map[i][j] == '@') {
                    count++;
                }
            }
            System.out.println();
        }

        return count;
    }
}