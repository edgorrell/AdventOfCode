import java.io.*;
import java.util.*;

public class Day10 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("2025\\input.txt"));
        String line;
        long total = 0;

        Deque<Light> q;
        ArrayList<Integer> buttons;
        HashSet<Integer> seen;
        Light l;

        while (scan.hasNextLine()) {
            line = scan.nextLine();
            line = line.replaceAll("\\(", "");
            line = line.replaceAll("\\)", "");

            String[] parts = line.split(" ");
            parts = Arrays.copyOfRange(parts, 0, parts.length - 1);

            l = new Light(
                    parts[0].substring(1, parts[0].length() - 1),
                    parts[0].length() - 2,
                    0);

            buttons = new ArrayList<>();
            for (int i = 1; i < parts.length - 1; i++) {
                int num = 0;
                for (String s : parts[i].split(",")) {
                    num |= 1 << Integer.parseInt(s);
                }
                buttons.add(num);

                int tmp = num;
                String str = "";
                for (int j = 0; j < l.length; j++) {
                    str = ((tmp & 1) == 1 ? "#" : ".") + str;
                    tmp >>= 1;
                }
                // System.out.println(parts[i] + " | " + str);
            }
            System.out.println(l.toString() + " | " + l.state + " | " + l.length);

            q = new ArrayDeque<>();
            q.add(l);
            seen = new HashSet<>();
            seen.add(l.state);
            while (!q.isEmpty()) {
                l = q.poll();

                if (l.state == (1 << l.length) - 1) {
                    total += l.steps;
                    while(l.previous != null){
                        System.out.println(l.toString());
                        l = l.previous;
                    }
                    break;
                }

                for (int button : buttons) {
                    if (!seen.contains(l.state ^ button)) {
                        seen.add(l.state ^ button);
                        Light temp = new Light(l.state ^ button, l.length, l.steps + 1);
                        temp.previous = l;
                        q.add(temp);
                    }
                }
            }

            System.out.println("-----");
        }

        System.out.println(total);
        scan.close();
    }

    public static class Light {
        public Light previous;
        public int state;
        public int length;
        public int steps = 0;

        public Light(String state, int length, int steps) {
            this.length = length;
            this.steps = steps;

            this.state = 0;
            for (int i = 0; i < this.length; i++) {
                this.state += (1 << i) * (int) (state.charAt(i) == '#' ? 1 : 0);
            }
        }

        public Light(int state, int length, int steps) {
            this.state = state;
            this.length = length;
            this.steps = steps;
        }

        @Override
        public String toString() {
            String str = "";
            int num = state;
            for (int i = 0; i < length; i++) {
                str = (num % 2 == 0 ? "." : "#") + str;
                num = num >> 1;
            }
            return str;
        }
    }
}
