package com.example.parts_list.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ControllerUtils {
    static Map<String, String> getErrors(BindingResult bindingResult) {
    /* bindingResult.getFieldErrors().stream().collect(Collectors.toMap(
             fieldError -> fieldError.getField() + "Error",
             FieldError::getDefaultMessage));

            // Данная запись анагогична той, что ниже*/
        Collector<FieldError, ?, Map<String, String>> errorMapCollector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage);
        //Получаем список ошибок валидации, для отображения
        return bindingResult.getFieldErrors().stream().collect(errorMapCollector);
    }
}
