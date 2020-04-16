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
    

    public Category(int id, String name, int memberInterac) {
        this.id = id;
        this.name = name;
        this.memberInterac=memberInterac;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setMemberInterac(int memberInterac) {
        this.memberInterac = memberInterac;
    }
    
    public int getMemberInterac() {
        return memberInterac;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    
}
