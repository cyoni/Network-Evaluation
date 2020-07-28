
package Graph.Algorithms;

import Utils.Point2D;

/**
 * This class represent zone in the graph
 * @author Yoni
 */
public class Zone {
    Point2D position;
    public int radius;
    
    public Zone(Point2D position, int radius){
        this.position = position;
        this.radius = radius;
    }
    
}
