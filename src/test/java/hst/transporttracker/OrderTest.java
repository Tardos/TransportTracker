package hst.transporttracker;

import org.junit.Test;

import java.net.Authenticator;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class OrderTest
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void constructorTest() {
        MockDatabase mockDatabase = new MockDatabase();
        Product product;
        StockPile stockPile;
        List<StockPile> basket = new ArrayList<>();
        Order order;

        // Given
        basket.clear();
        product = mockDatabase.queryProduct(1);
        stockPile = new StockPile(product, 2);
        basket.add(stockPile);
        product = mockDatabase.queryProduct(4);
        stockPile = new StockPile(product, 5);
        basket.add(stockPile);
        order = new Order(
                new Customer("Háromszék, Kakas utca 13.", "+26-51/555-4568",
                        "Sarah Connor", "sarah.connor@galaxynet.org"),
                PurchaseType.IN_SHOP, DeliveryType.TAKEOVER_IN_SHOP, PaymentType.CASH,
                basket);
        //Then
        assertThat(order.getCustomer().getAddress(), equalTo("Háromszék, Kakas utca 13."));
        assertThat(order.getPurchaseType(), equalTo(PurchaseType.IN_SHOP));
        assertThat(order.getDeliveryType(), equalTo(DeliveryType.TAKEOVER_IN_SHOP));
        assertThat(order.getPaymentType(), equalTo(PaymentType.CASH));
        assertThat(order.getBasket().get(0).getProduct().getNaming(), equalTo("Drone"));
        assertThat(order.getBasket().get(1).getQuantity(), equalTo(5));
        assertThat(order.getState(), equalTo(OrderState.DELIVERED));
        assertThat(order.getComment(), equalTo(Tools.EMPTY_STRING));
        // Given
        order = new Order(
                new Customer("Háromszék, Kakas utca 13.", "+26-51/555-4568",
                        "Sarah Connor", "sarah.connor@galaxynet.org"),
                PurchaseType.IN_SHOP, DeliveryType.HOME_DELIVERY, PaymentType.CARD,
                basket);
        // Then
        assertThat(order.getPurchaseType(), equalTo(PurchaseType.IN_SHOP));
        assertThat(order.getDeliveryType(), equalTo(DeliveryType.TAKEOVER_IN_SHOP));
        assertThat(order.getState(), equalTo(OrderState.DELIVERED));
        // Given
        order = new Order(
                new Customer("Háromszék, Kakas utca 13.", "+26-51/555-4568",
                        "Sarah Connor", "sarah.connor@galaxynet.org"),
                PurchaseType.ON_WORLD_WIDE_WEB, DeliveryType.TAKEOVER_IN_SHOP, PaymentType.CASH,
                basket);
        // Then
        assertThat(order.getPurchaseType(), equalTo(PurchaseType.ON_WORLD_WIDE_WEB));
        assertThat(order.getDeliveryType(), equalTo(DeliveryType.TAKEOVER_IN_SHOP));
        assertThat(order.getState(), equalTo(OrderState.DELIVERED));
        // Given
        order = new Order(
                new Customer("Háromszék, Kakas utca 13.", "+26-51/555-4568",
                        "Sarah Connor", "sarah.connor@galaxynet.org"),
                PurchaseType.ON_WORLD_WIDE_WEB, DeliveryType.HOME_DELIVERY, PaymentType.CASH,
                basket);
        // Then
        assertThat(order.getPurchaseType(), equalTo(PurchaseType.ON_WORLD_WIDE_WEB));
        assertThat(order.getDeliveryType(), equalTo(DeliveryType.HOME_DELIVERY));
        assertThat(order.getState(), equalTo(OrderState.BOOKED));
    }

    @Test
    public void putToCourierTest() {
        MockDatabase mockDatabase = new MockDatabase();
        Product product;
        StockPile stockPile;
        List<StockPile> basket = new ArrayList<>();
        Order order;

        // Given
        basket.clear();
        product = mockDatabase.queryProduct(1);
        stockPile = new StockPile(product, 2);
        basket.add(stockPile);
        product = mockDatabase.queryProduct(4);
        stockPile = new StockPile(product, 5);
        basket.add(stockPile);
        order = new Order(
                new Customer("Háromszék, Kakas utca 13.", "+26-51/555-4568",
                        "Sarah Connor", "sarah.connor@galaxynet.org"),
                PurchaseType.ON_WORLD_WIDE_WEB, DeliveryType.HOME_DELIVERY, PaymentType.CASH,
                basket);
        // When
        order.putToCourier();
        // Then
        assertThat(order.getState(), equalTo(OrderState.IN_PROGRESS));
    }

    @Test
    public void deliverTest() {
        MockDatabase mockDatabase = new MockDatabase();
        Product product;
        StockPile stockPile;
        List<StockPile> basket = new ArrayList<>();
        Order order;

        // Given
        basket.clear();
        product = mockDatabase.queryProduct(1);
        stockPile = new StockPile(product, 2);
        basket.add(stockPile);
        product = mockDatabase.queryProduct(4);
        stockPile = new StockPile(product, 5);
        basket.add(stockPile);
        order = new Order(
                new Customer("Háromszék, Kakas utca 13.", "+26-51/555-4568",
                        "Sarah Connor", "sarah.connor@galaxynet.org"),
                PurchaseType.ON_WORLD_WIDE_WEB, DeliveryType.HOME_DELIVERY, PaymentType.CASH,
                basket);
        // When
        order.putToCourier();
        order.deliver();
        // Then
        assertThat(order.getState(), equalTo(OrderState.DELIVERED));
        assertThat(order.getComment(), equalTo(Tools.EMPTY_STRING));
    }

    @Test
    public void haveDeliveryFailedTest() {
        MockDatabase mockDatabase = new MockDatabase();
        Product product;
        StockPile stockPile;
        List<StockPile> basket = new ArrayList<>();
        Order order;

        // Given
        basket.clear();
        product = mockDatabase.queryProduct(1);
        stockPile = new StockPile(product, 2);
        basket.add(stockPile);
        product = mockDatabase.queryProduct(4);
        stockPile = new StockPile(product, 5);
        basket.add(stockPile);
        order = new Order(
                new Customer("Háromszék, Kakas utca 13.", "+26-51/555-4568",
                        "Sarah Connor", "sarah.connor@galaxynet.org"),
                PurchaseType.ON_WORLD_WIDE_WEB, DeliveryType.HOME_DELIVERY, PaymentType.CASH,
                basket);
        // When
        order.putToCourier();
        order.haveDeliveryFailed("Nuclear disaster.");
        // Then
        assertThat(order.getState(), equalTo(OrderState.FAILED_DELIVERY));
        assertThat(order.getComment(), equalTo("Nuclear disaster."));
    }

    @Test
    public void putToCourierShouldThrowException() {
        MockDatabase mockDatabase = new MockDatabase();
        Product product;
        StockPile stockPile;
        List<StockPile> basket = new ArrayList<>();
        Order order;

        basket.clear();
        product = mockDatabase.queryProduct(1);
        stockPile = new StockPile(product, 2);
        basket.add(stockPile);
        product = mockDatabase.queryProduct(4);
        stockPile = new StockPile(product, 5);
        basket.add(stockPile);
        order = new Order(
                new Customer("Háromszék, Kakas utca 13.", "+26-51/555-4568",
                        "Sarah Connor", "sarah.connor@galaxynet.org"),
                PurchaseType.ON_WORLD_WIDE_WEB, DeliveryType.HOME_DELIVERY, PaymentType.CASH,
                basket);
        order.putToCourier();
        this.exception.expect(InvalidOrderOperationException.class);
        order.putToCourier();
    }

    @Test
    public void haveDeliveryFailedTestException() {
        MockDatabase mockDatabase = new MockDatabase();
        Product product;
        StockPile stockPile;
        List<StockPile> basket = new ArrayList<>();
        Order order;

        basket.clear();
        product = mockDatabase.queryProduct(1);
        stockPile = new StockPile(product, 2);
        basket.add(stockPile);
        product = mockDatabase.queryProduct(4);
        stockPile = new StockPile(product, 5);
        basket.add(stockPile);
        order = new Order(
                new Customer("Háromszék, Kakas utca 13.", "+26-51/555-4568",
                        "Sarah Connor", "sarah.connor@galaxynet.org"),
                PurchaseType.ON_WORLD_WIDE_WEB, DeliveryType.HOME_DELIVERY, PaymentType.CASH,
                basket);
        order.putToCourier();
        try {
            order.haveDeliveryFailed(Tools.EMPTY_STRING);
        } catch (InvalidOrderOperationException iooe) {
            assertThat(iooe.getErrorCode(), equalTo(ErrorCode.MISSING_COMMENT));
            assertThat(iooe.getOrder().getState(), equalTo(OrderState.IN_PROGRESS));
        }
    }
}
