package hst.transporttracker;

public class Customer
{
    private String address;
    private String phoneNumber;
    private String name;
    private String email;

    public Customer(String address, String phoneNumber, String name, String email) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }
}
