/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_structure;

/**
 * This class represent category in the network
 * @author caron
 */
public class Category {
    
    private int id;
    private String name;
    private int memberInterac; // num of members who interact with this category
    
    /**
     *
     * @param id
     */
    public Category(int id) {
        this.id = id;
    }
    
    /**
     *
     * @param id
     * @param name
     * @param memberInterac
     */
    public Category(int id, String name, int memberInterac) {
        this.id = id;
        this.name = name;
        this.memberInterac=memberInterac;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }
    
    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param memberInterac
     */
    public void setMemberInterac(int memberInterac) {
        this.memberInterac = memberInterac;
    }
    
    /**
     *
     * @return
     */
    public int getMemberInterac() {
        return memberInterac;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
       // If the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of Complex or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof Category)) { 
            return false; 
        } 
          
        // typecast o to Complex so that we can compare data members  
        Category c = (Category) o;
        
        return  c.id == this.id;
           
    }

    
    
    
}
