/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph_visualization;

/**
 * This class lets the users to filter certain relationships from the graph.
 * @author Yoni
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
public class FilterRelationship extends JFrame {
   
    private int maxGap;
    JComboBox horGapComboBox;
    JCheckBox checkbox[];
    String relationships[];
    
    JButton applyButton = new JButton("Filter");
    JButton cancelButton = new JButton("Close");

    GridLayout experimentLayout = new GridLayout(0,2);
    private Draw draw;
    
    public FilterRelationship(String name, String[] relationships, Draw draw) {
        super(name);
        setLocationRelativeTo(null);
        this.relationships = relationships;
        checkbox = new JCheckBox[relationships.length];
        maxGap = 3*checkbox.length;
        this.draw = draw;
        setAlwaysOnTop(true);
        setResizable(false);
    }
    
    public void initGaps() {
     
    }
    
    public void addComponentsToPane(final Container pane) {
        initGaps();
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(experimentLayout);
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(2,2));
        
        //Set up components preferred size
        JButton b = new JButton("Just fake button");
        Dimension buttonSize = b.getPreferredSize();
        compsToExperiment.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5)+maxGap,
                (int)(buttonSize.getHeight() * 3.5)+maxGap * 2));
        
        //Add buttons to experiment with Grid Layout
        
        for (int i=0; i<checkbox.length; i++){
           JCheckBox tmp = new JCheckBox(relationships[i]);
           
           if (draw.getRelationshipHashMap().contains(tmp.getText()))    
               tmp.setSelected(true);
           else
               tmp.setSelected(false);
        checkbox[i] = tmp;
        compsToExperiment.add(checkbox[i]);
        }
        
        //Add controls to set up horizontal and vertical gaps

        controls.add(applyButton);
        controls.add(cancelButton);
        //Process the Apply gaps button press
        applyButton.addActionListener(new ActionListener(){ // apply button click
            public void actionPerformed(ActionEvent e){
                for (int i=0; i<checkbox.length; i++){
                    if (checkbox[i].isSelected()){
                        draw.add_to_filter(checkbox[i].getText());
                    }
                    else{
                        draw.remove_from_filter(checkbox[i].getText());
                    } 
                }
                draw.drawGraph();
            }
        });

         cancelButton.addActionListener(new ActionListener(){ // apply button click
         public void actionPerformed(ActionEvent e){
             dispose();
            }
        });
        
        
        pane.add(compsToExperiment, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH);
    }
    

    private static void createAndShowGUI() {
        //Create and set up the window.
    }
    
    }
*/
