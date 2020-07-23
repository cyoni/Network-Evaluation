/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

import Data_structure.ProductForAdv;
import Database.LocalDatabase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author caron
 */
public class ListProducts {

    ArrayList<ProductForAdv> products = new ArrayList<>();
    // ProductForAdv have: product_id;  profit, list category , expoForDay, dayForAdv; 

    public ListProducts() {
//       ProductForAdv p1 = new ProductForAdv(1, 12, null, 5, 5);
//       ProductForAdv p2 = new ProductForAdv(2, 3, null, 3, 3);
//       ProductForAdv p3 = new ProductForAdv(2, 3, new ArrayList<Integer>(Arrays.asList(1,2)), 3, 3);
//
//       this.products.add(p1);
//       this.products.add(p2);
//       this.products.add(p3);
         createList();

    }

    public void createList() {

        LocalDatabase connctDB = new LocalDatabase("input/ListOfProducts.accdb");
        Connection connect = connctDB.getConnection();
        Statement statment = connctDB.getStatment();

        try {
            ResultSet rs = statment.executeQuery("SELECT * FROM [T_ProductForAdv]");
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                double profit = rs.getDouble("profit");
                int expoForDay = rs.getInt("expoForDay");
                int dayForAdv = rs.getInt("dayForAdv");

                ProductForAdv p = new ProductForAdv(product_id, profit, null, expoForDay, dayForAdv);
                products.add(p);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ListProducts.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
