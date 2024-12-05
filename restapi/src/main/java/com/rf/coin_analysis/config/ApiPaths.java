package com.rf.coin_analysis.config;

public class ApiPaths {
    public static final String VERSION="api/v1/";
    public static final String AUTH=VERSION+"auth/";
    public static final String COIN=VERSION+"coin/";
    public static final String FAVORITE=VERSION+"favorite/";
    public static final String USER=VERSION+"user/";
    public static final String GET="{id}";
    public static final String LIST="list";
    public static final String ORDER_BY_ASC="sorted/asc";
    public static final String GET_COIN_PRICE="price/{name}";
    public static final String REGISTER="register";
    public static final String DELETE = "delete/{id}";
    public static final String ADD="add/{coinID}/{userId}";
    public static final String FAVORITE_LIST="list/{userId}";
    public static final String LOGIN = "login";
    public static final String LOGOUT="logout";
}
