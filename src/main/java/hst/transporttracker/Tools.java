package hst.transporttracker;

public class Tools
{
    public static final String EMPTY_STRING = "";

    public static boolean IsNullOrEmpty(String exp) {
        if (exp == null) {
            return true;
        }
        if (exp.isEmpty()) {
            return true;
        }
        return false;
    }
}
