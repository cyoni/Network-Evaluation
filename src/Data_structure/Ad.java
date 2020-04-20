package Data_structure;

/**
 * 
 * @author Yoni
 */
public class Ad {
    private boolean isActive;
    private double price; // how much the advertisor paid for this ad
    private double product_price;
    private int category;
    private int views;
    private int clicks;
    
    public Ad(boolean isActive, double price, double product_price, int category, int views, int clicks){
        this.isActive = isActive;
        this.price = price;
        this.category = category-1;
        this.views = views;
        this.clicks = clicks;
        this.product_price = product_price;
    }
    
    public boolean getIsActive(){return isActive;}
    public double getPrice(){return price;}
    public int getCategoryID(){return category;}
    public int getViews(){return views;}
    public double getProductPrice() {return product_price;}
    public int getClicks() { return clicks;}
    
}
