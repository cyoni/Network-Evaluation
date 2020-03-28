/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph_visualization;

import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import algorithms.line;
import graph.DGraph;
import graph.Edge;
import graph.Graph;
import graph.Node;
import graph.Node_metadata;
import graph.edge_metadata;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import utils.Point2D;
import utils.StdDraw;
import utils.User_Dialog;

/**
 *
 * @author Yoni
 */
public class Gui_visualization extends javax.swing.JFrame {
    Graph g;
    static Color[] colors = {Color.YELLOW, Color.BLUE, Color.GREEN, Color.GRAY, Color.ORANGE, Color.PINK, Color.MAGENTA, Color.cyan};
    static int color_index = 0;
    HighlightPath_Thread highlightThread;
    
    private Graph_Algo graphAlgo;
    /**
     * Creates new form Gui_visualization
     */
    public Gui_visualization() {
        initComponents();
         setLocationRelativeTo(null);
          g = new DGraph();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jButton1.setText("Highlight Path");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Draw Graph");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Clean Graph");
        jButton3.setToolTipText("");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Longest Path");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText(".");

        jButton7.setText(".");

        jButton8.setText(".");

        jButton9.setText("Pause/Resume");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static Color changeColor(){
        color_index= (color_index+1)%colors.length;
        return colors[color_index];
    }
    
    public void constructGraph(){
                 
         
        
        Node x = new Node_metadata(0, new  Point2D(10, 50));
        Node x2 = new Node_metadata(1, new Point2D(50, 50));
        Node x3 = new Node_metadata(2, new Point2D(10, 20));
        Node x4 = new Node_metadata(3, new Point2D(50, 20));    
        Node x5 = new Node_metadata(4, new Point2D(60, 50));    
        Node x6 = new Node_metadata(5, new Point2D(50, 40));    
        Node x7 = new Node_metadata(6, new Point2D(40, 30));    
        Node x8 = new Node_metadata(7, new Point2D(40, 80));    
        Node x9 = new Node_metadata(8, new Point2D(70, 76));    
        Node x10 = new Node_metadata(9, new Point2D(75, 10));    

        
        g.addNode(x);
        g.addNode(x2);
        g.addNode(x3);
        g.addNode(x4);
     /*   g.addNode(x5);
        g.addNode(x6);
        g.addNode(x7);
        g.addNode(x8);
        g.addNode(x9);
        g.addNode(x10);
 */
        
        g.connect(0, 1, 50);
        g.connect(1, 3, 50);
        g.connect(2, 3, 50);
        g.connect(2, 0, 50);
        
     /*   g.connect(4, 5, 50);
        g.connect(5, 1, 50);
        g.connect(6, 5, 50);
        g.connect(6, 7, 50);
        g.connect(7, 8, 50);
        g.connect(9, 3, 50);*/
        
         graphAlgo = new Graph_Algo(g);
        
        
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         StdDraw.setCanvasSize(700, 500);
         StdDraw.setXscale(0, 100);
         StdDraw.setYscale(0, 100);

         constructGraph();
         StdDraw.clear();
         
         List<Edge> currentEdge = new ArrayList<>();
         currentEdge = g.getEdges();
                         
            for (int j=0; j<currentEdge.size(); j++){
                int src = currentEdge.get(j).getSrc();
                int dest = currentEdge.get(j).getDest();
                    
                StdDraw.setPenRadius(0.005);
                StdDraw.setPenColor(Color.yellow);
                StdDraw.point(g.getNode(src).getLocation().x(), g.getNode(src).getLocation().y());
                StdDraw.point(g.getNode(dest).getLocation().x(), g.getNode(dest).getLocation().y());
                StdDraw.setPenColor(Color.black);
                StdDraw.setPenRadius(0.007);
                StdDraw.line(g.getNode(src).getLocation().x(), g.getNode(src).getLocation().y(), g.getNode(dest).getLocation().x(), g.getNode(dest).getLocation().y());
                
                StdDraw.setPenColor(StdDraw.BLACK);
                Point2D text_pos = line.getPointOnLine(new Point2D(g.getNode(src).getLocation().x(), g.getNode(src).getLocation().y()),
                     new Point2D(g.getNode(dest).getLocation().x(), g.getNode(dest).getLocation().y()), 50);
                StdDraw.text(text_pos.x()-1.5, text_pos.y()+1.5, g.getEdge(src, dest).getWeight() + "");
                
                StdDraw.setPenColor(StdDraw.ORANGE);
                StdDraw.text(g.getNode(src).getLocation().x()+1, g.getNode(src).getLocation().y()+1.5, g.getNode(src).getKey()+"");
                StdDraw.text(g.getNode(dest).getLocation().x()+1, g.getNode(dest).getLocation().y()+1.5, g.getNode(dest).getKey()+"");
                }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        String x = User_Dialog.getInputDialog("Choose first node.");
        String y = User_Dialog.getInputDialog("Choose end node.");
        int src = Integer.parseInt(x);
        int dest = Integer.parseInt(y);
        
        String ask = User_Dialog.getInputDialog("Animation mode? 1=yes, 0=no");

        highlightThread = new HighlightPath_Thread(g,  src, dest,  (ask.equals("1")));
        highlightThread.action();

    

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        StdDraw.clear();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        boolean animation = false;
        List<Queue> q = graphAlgo.longestPath();
        if (q.size() > 0){
            animation = (User_Dialog.getInputDialog("Found " + q.size() +" path/s. Apply animation mode? [1=yes, 0=no]")
                     .equals("1")) ? true : false;
        
            highlightThread = new HighlightPath_Thread(g, q, animation);
            highlightThread.action();
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

    if (highlightThread == null) return;
    else 
        highlightThread.pauseOrResume(); 
    }//GEN-LAST:event_jButton9ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gui_visualization.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gui_visualization.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gui_visualization.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui_visualization.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui_visualization().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

  
}
