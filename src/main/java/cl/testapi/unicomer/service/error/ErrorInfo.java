package cl.testapi.unicomer.service.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
public class ErrorInfo {

    @JsonProperty("message")
    private String message;

    @JsonProperty("status_code")
    private int statusCode;

    @JsonProperty("uri")
    private String uriRequested;

    public ErrorInfo(String message, int statusCode, String uriRequested) {
        super();

        this.message = message;
        this.statusCode = statusCode;
        this.uriRequested = uriRequested;
    }
}