package Relationship;

import Graph.Edge;
import java.awt.Color;

/**
 * This class represent an advertising relationship
 * @author Yoni
 */
public class Advertise extends Edge {
    
    public Advertise(int x, int y, double weight){
        super(x, y, weight, Color.ORANGE);
        setTag("Advertise");
    }
    
    
}
