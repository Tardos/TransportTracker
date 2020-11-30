package hst.transporttracker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class StockPileTest
{
    @Test
    public void constructorTest() {
        Product product;
        StockPile stockPile;

        // Given
        product = new Product(8, "Rock chair",
                12_000d, 27d);
        stockPile = new StockPile(product, 23);
        // Then
        assertThat(stockPile.getProduct().getArticleNumber(), equalTo(8));
        assertThat(stockPile.getProduct().getNaming(), equalTo("Rock chair"));
        assertThat(stockPile.getProduct().getPrice(), equalTo(12_000d));
        assertThat(stockPile.getProduct().getVat(), equalTo(27d));
        assertThat(stockPile.getQuantity(), equalTo(23));
    }
}
