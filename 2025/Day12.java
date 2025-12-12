
import java.io.*;
import java.util.*;

public class Day12 {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("2025\\input.txt"));
        String line = "";
        long total = 0;

        ArrayList<Present> presents = new ArrayList<>();
        Present p = null;

        while (scan.hasNextLine()) {
            line = scan.nextLine();
            if (line.equals("")) {
                continue;
            }
            if (line.contains("x")) {
                break;
            }

            if (p == null || line.contains(":")) {
                line = scan.nextLine();
                p = new Present(line.length());

                for (int i = 0; i < p.size; i++) {
                    for (int j = 0; j < p.size; j++) {
                        p.shape[i][j] = (line.charAt(j) == '#');
                    }
                    line = scan.nextLine();
                }

                presents.add(p);
            }
        }

        for (Present pres : presents) {
            System.out.println(pres);
        }

        while (true) {
            int width = Integer.parseInt(line.substring(0, line.indexOf("x")));
            int height = Integer.parseInt(line.substring(line.indexOf("x") + 1, line.indexOf(":")));
            String[] amounts = line.substring(line.indexOf(":") + 2).split(" ");

            System.out.println(width + "|" + height + "|" + Arrays.toString(amounts));

            total += startPlace(width, height, amounts, presents);

            if (scan.hasNextLine()) {
                line = scan.nextLine();
            } else {
                break;
            }
        }

        System.out.println(total);
        scan.close();
    }

    public static int startPlace(int width, int height, String[] amounts, ArrayList<Present> presents) {
        boolean[][] map = new boolean[height][width];
        int[] nums;
        int sum = 0;

        for (String str : amounts) {
            sum += Integer.parseInt(str);
        }

        nums = new int[sum];

        int index = 0;
        for (int i = 0; i < amounts.length; i++) {
            for (int j = 0; j < Integer.parseInt(amounts[i]); j++) {
                nums[index] = i;
                index++;
            }
        }

        System.out.println("" + Arrays.toString(nums));

        return (place(map, nums, presents, 0) ? 1 : 0);
    }

    public static boolean place(boolean[][] map, int[] nums, ArrayList<Present> presents, int depth) {
        Present p = presents.get(nums[depth]);
        Present temp = new Present(p.size);
        temp.shape = p.shape.clone();
        boolean[][] backup = new boolean[map.length][map[0].length];

        for (int i = 0; i < map.length; i++) {
            backup[i] = Arrays.copyOf(map[i], map[i].length);
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                for (int r = 0; r < 4; r++) {
                    try {
                        temp.place(map, j, i);

                        if (depth == nums.length - 1 || place(map, nums, presents, depth + 1)) {
                            if (depth == 0) {
                                printMap(map);
                                System.out.println("-----");
                            }
                            return true;
                        }
                    } catch (Exception e) {
                    }

                    for (int n = 0; n < map.length; n++) {
                        System.arraycopy(backup[n], 0, map[n], 0, map[n].length);
                    }

                    temp.rotate();
                }
            }
        }

        return false;
    }

    public static void printMap(boolean[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] ? "#" : ".");
            }
            System.out.println();
        }
    }

    public static class Present {

        public boolean[][] shape;
        public int size;

        public Present(int size) {
            this.size = size;
            this.shape = new boolean[this.size][this.size];
        }

        public void rotate() {
            boolean[][] temp = new boolean[this.size][this.size];

            for (int r = 0; r < this.size; r++) {
                for (int c = 0; c < this.size; c++) {
                    temp[c][this.size - 1 - r] = this.shape[r][c];
                }
            }

            shape = temp;
        }

        public boolean place(boolean[][] map, int x, int y) throws ArrayIndexOutOfBoundsException {
            int h = map.length;
            int w = map[0].length;

            // check if works first
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    if (!shape[r][c]) {
                        continue;
                    }

                    if (y + r >= h || x + c >= w) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    if (map[y + r][x + c]) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                }
            }

            // then place if possible
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    if (shape[r][c]) {
                        map[y + r][x + c] = shape[r][c];
                    }
                }
            }

            return true;
        }

        @Override
        public String toString() {
            String str = "";
            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.size; j++) {
                    str += (shape[i][j] ? "#" : ".");
                }
                str += "\n";
            }

            return str;
        }
    }
}
