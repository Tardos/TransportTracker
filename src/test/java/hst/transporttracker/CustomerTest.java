package hst.transporttracker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CustomerTest
{
    @Test
    public void constructorTest() {
        Customer customer;

        // Given
        customer = new Customer("Háromszék, Kakas utca 13.", "+26-51/555-4568",
                "Sarah Connor", "sarah.connor@galaxynet.org");
        // Then
        assertThat(customer.getAddress(), equalTo("Háromszék, Kakas utca 13."));
        assertThat(customer.getPhoneNumber(), equalTo("+26-51/555-4568"));
        assertThat(customer.getName(), equalTo("Sarah Connor"));
        assertThat(customer.getEmail(), equalTo("sarah.connor@galaxynet.org"));
    }
}
