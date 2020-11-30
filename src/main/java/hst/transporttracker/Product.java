package hst.transporttracker;

public class Product
{
    public int articleNumber;
    public String naming;
    public double price;
    public double vat;

    public Product(int articleNumber, String naming, double price, double vat) {
        this.articleNumber = articleNumber;
        this.naming = naming;
        this.price = price;
        this.vat = vat;
    }

    public int getArticleNumber() {
        return this.articleNumber;
    }

    public String getNaming() {
        return this.naming;
    }

    public double getPrice() {
        return this.price;
    }

    public double getVat() {
        return this.vat;
    }
}
