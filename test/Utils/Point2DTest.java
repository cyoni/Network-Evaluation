/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author caron
 */
public class Point2DTest {

    public Point2DTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of x method, of class Point2D.
     */
    @Test
    public void testX() {
        System.out.println("x");
        Point2D instance = new Point2D(1,2);
        double expResult = 1;
        double result = instance.x();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of y method, of class Point2D.
     */
    @Test
    public void testY() {
        System.out.println("y");
        Point2D instance = new Point2D(1,2);
        double expResult = 2;
        double result = instance.y();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
    }

}
