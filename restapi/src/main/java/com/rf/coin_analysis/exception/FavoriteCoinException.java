package com.rf.coin_analysis.exception;

public class FavoriteCoinException extends RuntimeException {
    public FavoriteCoinException() {
        super("Bu Coin Zaten favoriye eklenmiş");
    }
}
