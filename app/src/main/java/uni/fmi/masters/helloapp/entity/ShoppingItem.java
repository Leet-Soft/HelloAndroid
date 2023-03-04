package uni.fmi.masters.helloapp.entity;

public class ShoppingItem {

    private String name;
    private double quantity;
    private String measure;
    private boolean isBought;

    public ShoppingItem(String name, double quantity, String measure){
        this.name = name;
        this.quantity = quantity;
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }
}
