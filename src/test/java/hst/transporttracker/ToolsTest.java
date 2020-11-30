package hst.transporttracker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ToolsTest
{
    @Test
    public void emptyStringTest() {
        String exp;

        // Given
        exp = Tools.EMPTY_STRING;
        // Then
        assertThat(exp, equalTo(""));
    }

    @Test
    public void isNullOrEmptyTest1() {
        String exp;
        boolean result;

        // Given
        exp = null;
        // When
        result = Tools.IsNullOrEmpty(exp);
        // Then
        assertThat(result, equalTo(true));
    }

    @Test
    public void isNullOrEmptyTest2() {
        String exp;
        boolean result;

        // Given
        exp = Tools.EMPTY_STRING;
        // When
        result = Tools.IsNullOrEmpty(exp);
        // Then
        assertThat(result, equalTo(true));
    }
}
