package au.com.avmaint.api.common;

/**
 * Created by Michael Coxon on 19/5/18.
 */
public class Response<T> {
    private T payload;
    private Code code;

    public static <T> Response of(T payload, Code code) {
        Response<T> response = new Response<>();
        response.payload = payload;
        response.code = code;
        return response;
    }

    public T getPayload() {
        return payload;
    }

    public Code getCode() {
        return code;
    }
}
