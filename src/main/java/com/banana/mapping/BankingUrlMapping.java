package com.banana.mapping;

public interface BankingUrlMapping {

    String BALANCE = "/balance";
    String HISTORY = "/history";
    String USER = "/user/{userId}";
    String BALANCE_USER = BALANCE + USER;
    String BALANCE_USER_INCREASE = BALANCE + "/increase" + USER;
    String BALANCE_USER_DECREASE = BALANCE + "/decrease" + USER;
    String HISTORY_USER = HISTORY + USER;
}
