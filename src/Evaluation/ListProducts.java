/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

import Data_structure.ProductForAdv;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author caron
 */
public class ListProducts {

    ArrayList<ProductForAdv> products = new ArrayList<>();
  // ProductForAdv have: product_id;  profit, list category , expoForDay, dayForAdv; 
    
    public ListProducts() {
       ProductForAdv p1 = new ProductForAdv(1, 12, null, 5, 1);
       ProductForAdv p2 = new ProductForAdv(2, 3, null, 3, 3);
       ProductForAdv p3 = new ProductForAdv(2, 3, new ArrayList<Integer>(Arrays.asList(1,2)), 3, 3);

       this.products.add(p1);
       this.products.add(p2);
       this.products.add(p3);


    }
    
    

}
