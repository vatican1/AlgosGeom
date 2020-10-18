import com.sun.org.apache.bcel.internal.generic.LLOAD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Dot {
    int x;
    int y;

    Dot() {
        x = 0;
        y = 0;
    }

    Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int inSquare() {
        return x * x + y * y;
    }

}

class Vector {
    Dot start;
    Dot end;
    int x;
    int y;

    Vector() {
        start = new Dot();
        end = new Dot();
        x = 0;
        y = 0;
    }

    Vector(Dot start, Dot end) {
        this.start = start;
        this.end = end;
        x = end.x - start.x;
        y = end.y - start.y;
    }

    Vector(int x, int y) {
        this.x = x;
        this.y = y;
        start = new Dot();
        end = new Dot(x, y);
    }

    Vector(int start_x, int start_y, int end_x, int end_y) {
        start = new Dot(start_x, start_y);
        end = new Dot(end_x, end_y);
        x = end_x - start_x;
        y = end_y - start_y;
    }


    double length() {
        return Math.sqrt(x * x + y * y);
    }

    static double scalarProduct(Vector vector1, Vector vector2) {
        return vector1.x * vector2.x + vector1.y * vector2.y;
    }

    static double GetAngle(Vector vector1, Vector vector2) {
        return Math.acos(scalarProduct(vector1, vector2) /(vector1.length()*vector2.length()));
    }
}

class Line {
    int a,b,c;
    Line(Dot dot1, Dot dot2){
        a = dot2.y - dot1.y;
        b = dot2.x - dot1.x;
        c = -(a*dot1.x+b*dot1.y);
    }
    static double[] intersectDot(Line line1, Line line2){
        double x = (line1.b*line2.c - line2.b*line1.c)/(line1.a*line2.b-line2.a*line1.b);
        double y = (line1.a*line2.c-line2.a*line1.c)/(line1.a*line2.b-line2.a*line1.b);
        return new double[] {x,y};
    }
}

public class main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().trim().split("\\s+");
        int a = Integer.parseInt(strs[0]);
        int b = Integer.parseInt(strs[1]);
        int c = Integer.parseInt(strs[2]);
        int d = Integer.parseInt(strs[3]);

        strs = br.readLine().trim().split("\\s+");
        int x = Integer.parseInt(strs[0]);
        int y = Integer.parseInt(strs[1]);
        int z = Integer.parseInt(strs[2]);
        int k = Integer.parseInt(strs[3]);

        double [] arr = Line.intersectDot(new Line(new Dot(a,b), new Dot(c,d)) , new Line(new Dot(x,y), new Dot(z,k)));
        System.out.println(arr[0]+" "+ arr[1]);
    }
}
