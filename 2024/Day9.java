import java.util.*;
import java.io.*;

public class Day9 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("day9.txt"));
        String line = "";
        ArrayList<Part> list = new ArrayList<Part>();
        int num = 0;
        boolean check;

        check = true;
        line = scan.nextLine();

        for (int i = 0; i < line.length(); i++) {
            num = Integer.parseInt("" + line.charAt(i));

            if (check) {
                list.add(new Part(i / 2, num));
            } else {
                list.add(new Part(-1, num));
            }

            check = !check;
        }

        // prints correctly
        for (int i = list.size() - 1; i > -1; i--) {
            if(list.get(i).val == -1 || list.get(i).moved){continue;}

            for(int j = 0; j < i; j++){
                if(list.get(j).val != -1){continue;}

                if (list.get(j).size >= list.get(i).size) {
                    list.add(j, new Part(list.get(i)));
                    list.set(i+1,new Part(-1,list.get(j).size));
                    list.set(j+1, new Part(-1,list.get(j+1).size-list.get(j).size));
                    //printList(list);
                    break;
                }
            }
        }

        long total = 0;
        int index = 0;
        for (Part p : list) {
            if(p.size == 0){continue;}
            if(p.val == -1){index += p.size; continue;}
            for(int i = 0; i < p.size; i++){
                total += index * p.val;
                index++;
            }
        }

        System.out.println(total);
        scan.close();
    }

    public static void printList(ArrayList<Part> list) {
        String result = "";
        for (Part p : list) {
            for (int i = 0; i < p.size; i++) {
                if (p.val == -1) {
                    result += ".";
                } else {
                    result += p.val;
                }
            }
        }
        System.out.println(result);
    }

    public static class Part {
        int val, size;
        boolean moved = false;

        public Part(int val, int size) {
            this.val = val;
            this.size = size;
        }

        public Part(Part p) {
            this.val = p.val;
            this.size = p.size;
        }

        public String toString() {
            return "(" + val + "," + size + ")";
        }
    }

    /*
     * while(true){
     * num = list.size()-1;
     * while(list.get(num) == -1){num--;}
     * 
     * list.set(list.indexOf(new Integer(-1)), list.get(num));
     * list.remove(num);
     * //System.out.println(list);
     * //System.out.println(list.indexOf(new Integer(-1)) + "," +
     * list.lastIndexOf(new Integer(-1)) + ":" + list.size());
     * 
     * if(!list.contains(new Integer(-1))){
     * break;
     * }
     * }
     * System.out.println(list);
     */
}