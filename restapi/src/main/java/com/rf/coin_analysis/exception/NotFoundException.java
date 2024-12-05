package com.rf.coin_analysis.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String type) {
        super(type+" Bulunamadi");
    }
}
