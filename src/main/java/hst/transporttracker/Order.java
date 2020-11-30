package hst.transporttracker;

import java.util.List;

public class Order
{
    private Customer customer;
    private PurchaseType purchaseType;
    private DeliveryType deliveryType;
    private PaymentType paymentType;
    private List<StockPile> basket;
    private OrderState state;
    private String comment;

    public Order(Customer customer, PurchaseType purchaseType, DeliveryType deliveryType,
                 PaymentType paymentType, List<StockPile> basket) {
        this.customer = customer;
        this.purchaseType = purchaseType;
        this.deliveryType = deliveryType;
        if (purchaseType == PurchaseType.IN_SHOP) {
            this.deliveryType = DeliveryType.TAKEOVER_IN_SHOP;
        }
        this.paymentType = paymentType;
        this.basket = basket;
        if (purchaseType == PurchaseType.IN_SHOP) {
            this.state = OrderState.DELIVERED;
        } else {
            if ((purchaseType == PurchaseType.ON_WORLD_WIDE_WEB) &&
                    (deliveryType == DeliveryType.TAKEOVER_IN_SHOP)) {
                this.state = OrderState.DELIVERED;
            }
            if ((purchaseType == PurchaseType.ON_WORLD_WIDE_WEB) &&
                    (deliveryType == DeliveryType.HOME_DELIVERY)) {
                this.state = OrderState.BOOKED;
            }
        }
        this.comment = Tools.EMPTY_STRING;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public PurchaseType getPurchaseType() {
        return this.purchaseType;
    }

    public DeliveryType getDeliveryType() {
        return this.deliveryType;
    }

    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    public List<StockPile> getBasket() {
        return this.basket;
    }

    public OrderState getState() {
        return this.state;
    }

    public String getComment() {
        return this.comment;
    }

    public void putToCourier() throws InvalidOrderOperationException {
        if ((this.state != OrderState.BOOKED)) {
            throw new InvalidOrderOperationException(ErrorCode.CANNOT_PUT_TO_COURIER, "Cannot put to courier.", this);
        }
        this.state = OrderState.IN_PROGRESS;
    }

    public void deliver() throws InvalidOrderOperationException {
        if (this.state != OrderState.IN_PROGRESS) {
            throw new InvalidOrderOperationException(ErrorCode.CANNOT_DELIVER, "Cannot deliver.", this);
        }
        this.state = OrderState.DELIVERED;
    }

    public void haveDeliveryFailed(String comment) throws InvalidOrderOperationException {
        if (this.state != OrderState.IN_PROGRESS) {
            throw new InvalidOrderOperationException(ErrorCode.CANNOT_HAVE_DELIVERY_FAILED,
                    "Cannot have delivery failed.", this);
        }
        if (Tools.IsNullOrEmpty(comment)) {
            throw new InvalidOrderOperationException(ErrorCode.MISSING_COMMENT,
                    "Missing comment at failed delivery.", this);
        }
        this.comment = comment;
        this.state = OrderState.FAILED_DELIVERY;
    }
}
