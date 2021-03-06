package hst.transporttracker;

public class StockPile
{
    private Product product;
    private int quantity;

    public StockPile(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public int getQuantity() {
        return this.quantity;
    }
}
