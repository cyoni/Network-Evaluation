/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relationship;

import graph.Edge;

/**
 *
 * @author Yoni
 */
public class Share extends Edge{
        public Share(int x, int y, double weight){
        super(x, y, weight);
        setTag("Shares");
    }
}