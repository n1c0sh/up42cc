package org.n1c0sh.up42cc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FeatureNotFoundException extends RuntimeException {
    public FeatureNotFoundException() {
        super();
    }

    public FeatureNotFoundException(String message) {
        super(message);
    }

    public FeatureNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
