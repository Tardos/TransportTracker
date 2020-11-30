package hst.transporttracker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ProductTest
{
    @Test
    public void constructorTest() {
        Product product;

        // Given
        product = new Product(8, "Rock chair",
                12_000d, 27d);
        // Then
        assertThat(product.getArticleNumber(), equalTo(8));
        assertThat(product.getNaming(), equalTo("Rock chair"));
        assertThat(product.getPrice(), equalTo(12_000d));
        assertThat(product.getVat(), equalTo(27d));
    }
}
