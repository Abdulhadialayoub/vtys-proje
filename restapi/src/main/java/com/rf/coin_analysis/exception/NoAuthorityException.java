package com.rf.coin_analysis.exception;

public class NoAuthorityException extends RuntimeException {
    public NoAuthorityException() {
        super("Yetki Yok");
    }
}
