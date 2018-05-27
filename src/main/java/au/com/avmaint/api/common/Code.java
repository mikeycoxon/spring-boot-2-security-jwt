package au.com.avmaint.api.common;

/**
 * Created by Michael Coxon on 19/5/18.
 */
public enum Code {
    OK(0, "OK");

    private final int number;

    private final String text;

    Code(final int number, final String text) {
        this.number = number;
        this.text = text;
    }


}
