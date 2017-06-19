package com.singidunum.projectKDP.exception;

public class WarehouseException extends Exception{

    public WarehouseException(String poruka) {
        super(poruka);
    }

    public WarehouseException(String message, Throwable cause) {
        super(message, cause);
    }
}
