package com.rf.coin_analysis.exception;

public class TokenException extends RuntimeException {
    public TokenException() {
        super("Geçersiz Token");
    }
}
