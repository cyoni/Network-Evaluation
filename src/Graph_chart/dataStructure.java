
package Graph_chart;

/**
 *
 * @author Yoni
 */
public class dataStructure {
    
    private String title;
    private int value;

    public dataStructure(String title, int value){
        this.title = title;
        this.value = value;
    }
    
    
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "dataStructure{" + "title=" + title + ", value=" + value + '}';
    }
    
    
    
}
