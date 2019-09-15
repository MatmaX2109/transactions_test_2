package com.parent.validation.controller;

import com.parent.validation.kafka.Producer;
import com.parent.validation.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ValidationController {

    @Autowired
    Producer producer;

    /**
     * Controller pentru serviciul de validare
     * @param transaction
     * @return
     */
    @PostMapping("sendTransaction")
    public ResponseEntity<Map<String, Object>> sendTransaction(@Valid @RequestBody Transaction transaction){

        producer.produceMessage(transaction);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("message", "Tranzactia a fost trimisa");

        return ResponseEntity.ok(body);
    }


    /**
     * Prinde eroarea de validare si returneaza body-ul sub forma dorita
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", "Validation Error");
        body.put("timestamp", new Date());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        body.put("errors", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}