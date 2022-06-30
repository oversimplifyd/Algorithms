/**
A crack team of love scientists from OkEros (a hot new dating site) have devised a way to represent dating profiles as rectangles on a two-dimensional plane.

They need help writing an algorithm to find the intersection of two users' love rectangles. They suspect finding that intersection is the key to a matching algorithm so powerful it will cause an immediate acquisition by Google or Facebook or Obama or something.
 */
 
package algorthms;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class RectLove {

    public static class Rectangle {

        // coordinates of bottom left corner
        private int leftX;
        private int bottomY;

        // dimensions
        private int width;
        private int height;

        public Rectangle() {}

        public Rectangle(int leftX, int bottomY, int width, int height) {
            this.leftX = leftX;
            this.bottomY = bottomY;
            this.width  = width;
            this.height = height;
        }

        public int getLeftX() {
            return leftX;
        }

        public int getBottomY() {
            return bottomY;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        @Override
        public String toString() {
            return String.format("(left: %d, bottom: %d, width: %d, height: %d)",
                leftX, bottomY, width, height);
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Rectangle)) {
                return false;
            }
            final Rectangle r = (Rectangle) o;
            return leftX == r.leftX && bottomY == r.bottomY
                   && width == r.width && height == r.height;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = result * 31 + leftX;
            result = result * 31 + bottomY;
            result = result * 31 + width;
            result = result * 31 + height;
            return result;
        }
    }
    
    //Missed (2) some test cases
    //E.g. OnlyEdge touching cases; 
   /* private static class BoundedRectangle {
        
        private Rectangle rectangle; 
        
        public BoundedRectangle(Rectangle rec) {
            this.rectangle = rec; 
        }
        
         public int getLeftX() {
            return rectangle.leftX;
        }

        public int getBottomY() {
            return rectangle.bottomY;
        }

        public int getWidth() {
            return rectangle.width;
        }

        public int getHeight() {
            return rectangle.height;
        }
        
        public int getRightX() {
            return getWidth() + getLeftX();
        }
        
        public int getTopY() {
            return getHeight() + getBottomY();
        }
        
        public Rectangle getRectangle() {
            return rectangle;  
        }
    }
    
    public static Rectangle findRectangularOverlap(Rectangle rect1, Rectangle rect2) {

        // calculate the overlap between the two rectangles
        
        BoundedRectangle r1 = new BoundedRectangle(rect1);
        BoundedRectangle r2 = new BoundedRectangle(rect2);
        
        int newRectleftX;
        int newRectBottomY;
        int newRectWidth;
        int newRectHeight; 
        
        
        if (r2.getLeftX() >= r1.getLeftX() && r2. getLeftX() <= r1.getRightX() && r2.getBottomY() >= r1.getBottomY() && r2.getBottomY() < r1.getTopY()) {
            newRectleftX = r2.getLeftX();
            newRectBottomY = r2.getBottomY();
            newRectWidth = Math.min(r2.getRightX(), r1.getRightX()) - newRectleftX;
            newRectHeight = Math.min(r2.getTopY(), r1.getTopY()) - newRectBottomY;
        } else if (r2.getLeftX() >= r1.getLeftX() && r2.getLeftX() <= r1.getRightX() && r2.getTopY() <= r1.getTopY() && r2.getTopY() >= r1.getBottomY()) {
            newRectleftX = r2.getLeftX();
            newRectBottomY = r1.getBottomY();
            newRectWidth = Math.min(r2.getRightX(), r1.getRightX()) - newRectleftX;
            newRectHeight = Math.min(r2.getBottomY(), r1.getBottomY()) - newRectBottomY;
        } else if (r2.getRightX() <= r1.getRightX() && r2.getRightX() >= r1.getLeftX() && r2.getBottomY() >= r1.getBottomY() && r2.getBottomY() <= r1.getTopY()) {
            newRectleftX = r1.getLeftX();
            newRectBottomY = r2.getBottomY();
            newRectWidth = Math.max(r2.getLeftX(), r1.getLeftX()) - newRectleftX;
            newRectHeight = Math.max(r2.getBottomY(), r1.getBottomY()) - newRectBottomY;
        } else if (r2.getRightX() <= r1.getRightX() && r2.getRightX() >= r1.getLeftX() && r2.getTopY() <= r1.getTopY() && r2.getTopY() >= r1.getBottomY()) {
            newRectleftX = r1.getLeftX();
            newRectBottomY = r1.getBottomY();
            newRectWidth = Math.max(r2.getLeftX(), r1.getLeftX()) - newRectleftX;
            newRectHeight = Math.max(r2.getBottomY(), r1.getBottomY()) - newRectBottomY;
        } else {
            return new Rectangle(0, 0, 0, 0);
        }
        
        
        
        return new Rectangle(newRectleftX, newRectBottomY, newRectWidth, newRectHeight);
    }*/
    
    // There is always a better approach 
    // Organize your thoughs, draw up scenarios 
    // Break problem down into sub-problem 
    private static class RangeOverlap {
    
        private int startPoint;
        private int length;
    
        public RangeOverlap(int startPoint, int length) {
            this.startPoint = startPoint;
            this.length = length;
        }
    
        public int getStartPoint() {
            return startPoint;
        }
    
        public int getLength() {
            return length;
        }
    }
    
    private static RangeOverlap findRangeOverlap(int point1, int length1, int point2, int length2) {
    
        // find the highest start point and lowest end point.
        // the highest ("rightmost" or "upmost") start point is
        // the start point of the overlap.
        // the lowest end point is the end point of the overlap.
        int highestStartPoint = Math.max(point1, point2);
        int lowestEndPoint = Math.min(point1 + length1, point2 + length2);
    
        // return empty overlap if there is no overlap
        if (highestStartPoint >= lowestEndPoint) {
            return new RangeOverlap(0, 0);
        }
    
        // compute the overlap length
        int overlapLength = lowestEndPoint - highestStartPoint;
    
        return new RangeOverlap(highestStartPoint, overlapLength);
    }
    
    public static Rectangle findRectangularOverlap(Rectangle rect1, Rectangle rect2) {
    
        // get the x and y overlap points and lengths
        RangeOverlap xOverlap = findRangeOverlap(rect1.getLeftX(), rect1.getWidth(),
                                                 rect2.getLeftX(), rect2.getWidth());
        RangeOverlap yOverlap = findRangeOverlap(rect1.getBottomY(), rect1.getHeight(),
                                                 rect2.getBottomY(), rect2.getHeight());
    
        // return "zero" rectangle if there is no overlap
        if (xOverlap.getLength() == 0 || yOverlap.getLength() == 0) {
            return new Rectangle();
        }
    
        return new Rectangle(
            xOverlap.getStartPoint(),
            yOverlap.getStartPoint(),
            xOverlap.getLength(),
            yOverlap.getLength()
        );
    }
    
    // tests

    @Test
    public void overlapAlongBothAxesTest() {
        final Rectangle actual = findRectangularOverlap(
            new Rectangle(1, 1, 6, 3), new Rectangle(5, 2, 3, 6));
        final Rectangle expected = new Rectangle (5, 2, 2, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void oneRectangleInsideAnotherTest() {
        final Rectangle actual = findRectangularOverlap(
            new Rectangle(1, 1, 6, 6), new Rectangle(3, 3, 2, 2));
        final Rectangle expected = new Rectangle(3, 3, 2, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void bothRectanglesTheSameTest() {
        final Rectangle actual = findRectangularOverlap(
            new Rectangle(2, 2, 4, 4), new Rectangle(2, 2, 4, 4));
        final Rectangle expected = new Rectangle(2, 2, 4, 4);
        assertEquals(expected, actual);
    }

    @Test
    public void touchOnHorizontalEdgeTest() {
        final Rectangle actual = findRectangularOverlap(
            new Rectangle(1, 2, 3, 4), new Rectangle(2, 6, 2, 2));
        final Rectangle expected = new Rectangle();
        assertEquals(expected, actual);
    }

    @Test
    public void touchOnVerticalEdgeTest() {
        final Rectangle actual = findRectangularOverlap(
            new Rectangle(1, 2, 3, 4), new Rectangle(4, 3, 2, 2));
        final Rectangle expected = new Rectangle();
        assertEquals(expected, actual);
    }

    @Test
    public void touchAtCornerTest() {
        final Rectangle actual = findRectangularOverlap(
            new Rectangle(1, 1, 2, 2), new Rectangle(3, 3, 2, 2));
        final Rectangle expected = new Rectangle();
        assertEquals(expected, actual);
    }

    @Test
    public void noOverlapTest() {
        final Rectangle actual = findRectangularOverlap(
            new Rectangle(1, 1, 2, 2), new Rectangle(4, 6, 3, 6));
        final Rectangle expected = new Rectangle();
        assertEquals(expected, actual);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(RectLove.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}