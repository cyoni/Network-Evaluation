package data_structure;

/**
 * 
 * @author Yoni
 */
public class Ad {
    private boolean isActive;
    private double price; // how much the advertisor paid for this ad
    private double product_price;
    private int category;
    private int interest;
    
    public Ad(boolean isActive, double price, double product_price, int category, int interest){
        this.isActive = isActive;
        this.price = price;
        this.category = category-1;
        this.interest = interest;
        this.product_price = product_price;
    }
    
    public boolean getIsActive(){return isActive;}
    public double getPrice(){return price;}
    public int getCategoryID(){return category;}
    public int getInterest(){return interest;}
    public double getProductPrice() {return product_price;}
}
