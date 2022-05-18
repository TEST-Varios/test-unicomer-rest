package cl.testapi.unicomer.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cl.testapi.unicomer.service.error.ErrorHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ResponseHandlerJson {

    private static MethodArgumentNotValidException errorJson;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public static ResponseEntity<Object> generateResponse(HttpStatus status, boolean error, String message, Object responseObj) {
        Map<String, Object> map = new HashMap<>();
        Date fechaSys = new Date();


        try {
            map.put("Status Code", status.value());
            map.put("Fecha", new SimpleDateFormat("dd-MM-yyyy HH:mm").format(fechaSys));
            map.put("isSuccess", error);
            map.put("Message", message);
            map.put("Data", responseObj);

            return new ResponseEntity<>(map, status);
        } catch (Exception ex) {

            map.put("Status Code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("Fecha", new SimpleDateFormat("dd-MM-yyyy HH:mm").format(fechaSys));
            map.put("isSuccess", false);
            map.put("Message", ErrorHandler.customError(errorJson));
            map.put("Data", null);

            return new ResponseEntity<>(map, status);
        }
    }

}
