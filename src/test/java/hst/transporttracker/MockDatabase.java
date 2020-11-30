package hst.transporttracker;

import java.util.ArrayList;
import java.util.List;

public class MockDatabase
{
    private List<Product> products = new ArrayList<>();
    private List<StockPile> stockPiles = new ArrayList<>();

    public MockDatabase() {
        Product product;
        StockPile stockPile;

        this.products.clear();
        this.stockPiles.clear();
        product = new Product(1, "Drone", 240000d, 12d);
        stockPile = new StockPile(product, 36);
        this.products.add(product);
        this.stockPiles.add(stockPile);
        product = new Product(2, "Fetal tissue", 120000d, 12d);
        stockPile = new StockPile(product, 203);
        this.products.add(product);
        this.stockPiles.add(stockPile);
        product = new Product(3, "iPad", 42000d, 27d);
        stockPile = new StockPile(product, 57);
        this.products.add(product);
        this.stockPiles.add(stockPile);
        product = new Product(4, "Desk lamp", 3000d, 27d);
        stockPile = new StockPile(product, 21);
        this.products.add(product);
        this.stockPiles.add(stockPile);
    }

    public Product queryProduct(int articleNumber) {
        if (articleNumber <= 1) {
            return this.products.get(0);
        }
        if (4 <= articleNumber) {
            return this.products.get(3);
        }
        return this.products.get(articleNumber - 1);
    }

    public StockPile queryStockPile(int articleNumber) {
        if (articleNumber <= 1) {
            return this.stockPiles.get(0);
        }
        if (4 <= articleNumber) {
            return this.stockPiles.get(3);
        }
        return this.stockPiles.get(articleNumber - 1);
    }

    public boolean checkBasket(List<StockPile> basket) {
        boolean result;

        result = true;
        for (StockPile item : basket) {
            if (queryStockPile(item.getProduct().getArticleNumber()).getQuantity() < item.getQuantity()) {
                result = false;
            }
        }
        return result;
    }
}
