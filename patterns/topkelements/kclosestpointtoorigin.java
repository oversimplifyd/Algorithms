package algorithms; 

/**
Given an array of points in the a 2D2D plane, find ‘K’ closest points to the origin.
 */
 import java.util.*;

class Point {
  int x;
  int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int distFromOrigin() {
    // ignoring sqrt
    return (x * x) + (y * y);
  }
}

class KClosestPointsToOrigin {

  public static List<Point> findClosestPoints(Point[] points, int k) {
    ArrayList<Point> result = new ArrayList<>();
    
    PriorityQueue<Point> maxHeap = new PriorityQueue<Point>(k, (a, b) -> b.distFromOrigin() - a.distFromOrigin()); 

    for (int i = 0; i < k; i++) {
      maxHeap.offer(points[i]);
    }

    for (int i = k; i < points.length; i++) {
      if (Math.sqrt(points[i].distFromOrigin()) < Math.sqrt(maxHeap.peek().distFromOrigin())) {
        maxHeap.poll();
        maxHeap.offer(points[i]);
      }
    }

    return new ArrayList<>(maxHeap);
  }

  public static void main(String[] args) {
    Point[] points = new Point[] { new Point(1, 3), new Point(3, 4), new Point(2, -1) };
    List<Point> result = KClosestPointsToOrigin.findClosestPoints(points, 2);
    System.out.print("Here are the k points closest the origin: ");
    for (Point p : result)
      System.out.print("[" + p.x + " , " + p.y + "] ");
  }
}
