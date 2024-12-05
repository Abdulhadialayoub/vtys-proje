package com.rf.coin_analysis.exception;

public class CoinException extends RuntimeException{
    public CoinException() {
        super("Coin bilgisi alınamadi");
    }
}
