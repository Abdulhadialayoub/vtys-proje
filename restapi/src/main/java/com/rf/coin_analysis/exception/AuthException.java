package com.rf.coin_analysis.exception;

public class AuthException extends RuntimeException {
    public AuthException() {
        super("Şifre veye Email yanlış Lütfen Tekrar Deneyin");
    }
}
