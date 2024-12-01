package com.product_management.MS_PRODUCT_MANAGEMENT.exceptionhandler;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ValidateError extends StandardError{

    private Map<String, String> fieldErrors;

}
