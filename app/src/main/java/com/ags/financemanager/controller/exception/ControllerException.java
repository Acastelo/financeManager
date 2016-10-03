package com.ags.financemanager.controller.exception;

/**
 * Created by Max on 30/09/2016.
 */
public class ControllerException extends RuntimeException {

    public ControllerException() {

    }

    public ControllerException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ControllerException(String detailMessage) {
        super(detailMessage);
    }

    public ControllerException(Throwable throwable) {
        super(throwable);
    }

}
