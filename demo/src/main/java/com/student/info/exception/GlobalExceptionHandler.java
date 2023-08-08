package com.student.info.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<String> handleNullRequestException(RequestValidationException ex) {
//        JSONObject jsonResponse = new JSONObject();
//        jsonResponse.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}

//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(RequestValidationException.class)
//    public ResponseEntity<String> handleNullRequestException(RequestValidationException ex) {
//        JSONObject jsonResponse = new JSONObject();
//        jsonResponse.put("error", ex.getMessage());
//
//        // Convert the JSON object to a JSON string
//        String jsonString = jsonResponse.toString();
//
//        // Return the response with the JSON string as the content
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(jsonString);
//    }
//
//}


