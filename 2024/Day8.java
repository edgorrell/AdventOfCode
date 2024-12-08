import java.util.*;
import java.io.*;

public class Day8{
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(new File("day8.txt"));
        String line;
        ArrayList<Point> points = new ArrayList<Point>(), nodes = new ArrayList<Point>();
        int num, size = 0;

        num = 0;
        while(scan.hasNext()){ 
           line = scan.nextLine();
            size = line.length();
           for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) == '.'){continue;}
                points.add(new Point(num,i,line.charAt(i)));
                //System.out.println(points.get(points.size()-1));
            }
           num++;
        }

        for(Point p1 : points){
            for(Point p2 : points){
                if(p1.equals(p2) || p1.val != p2.val){continue;}
                Point node = p1, diff = Point.getDiff(p1, p2);

                while(node.x >= 0 && node.y >= 0 && node.x < size && node.y < size){
                    node = node.add(diff);
                    if(!nodes.contains(node) && node.x >= 0 && node.y >= 0 && node.x < size && node.y < size){
                        nodes.add(node);
                        System.out.println(node);
                    }
                }

                node = p1;
                while(node.x >= 0 && node.y >= 0 && node.x < size && node.y < size){
                    node = node.add(diff);
                    if(!nodes.contains(node) && node.x >= 0 && node.y >= 0 && node.x < size && node.y < size){
                        nodes.add(node);
                        System.out.println(node);
                    }
                }

                
            }
        }

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(nodes.contains(new Point(i,j,'#'))){
                    System.out.print('#');
                } else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }

        System.out.println(nodes.size());
        scan.close();
    }

    public static class Point{
        int x, y;
        char val;

        public Point(int x, int y, char val){
            this.x = x;
            this.y = y;
            this.val = val;
        }

        public Point add(Point p){
            return new Point(this.x+p.x,this.y+p.y,'#');
        }

        public Point sub(Point p){
            return new Point(this.x-p.x,this.y-p.y,'#');
        }

        public boolean equals(Object o){
            Point point = (Point) o;
            return this.x == point.x && this.y == point.y && this.val == point.val;
        }

        public String toString(){
            return "(" + this.x + "," + this.y + "): " + this.val;
        }

        public static Point getNode(Point p1, Point p2){
            return new Point(2*p2.x - p1.x, 2*p2.y - p1.y,'#');
        }

        public static Point getDiff(Point p1, Point p2){
            return new Point(p2.x - p1.x, p2.y - p1.y, '#');
        }
    }
}
