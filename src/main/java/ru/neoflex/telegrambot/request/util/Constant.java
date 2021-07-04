package ru.neoflex.telegrambot.request.util;

public interface Constant {


    String GET_ONE = "https://api.binance.com/api/v1/ticker/price?symbol=";

    String GET_SIX = "https://api.binance.com/api/v1/ticker/price";

    String GET_N_COUNT = "https://api.binance.com/api/v1/ticker/price";

    String ERROR_VAR = "Sorry, symbol is not found" + "\n" +
            "Please see this table of symbols" + "\n" +
            "____________________________________" + "\n" +
            "btcrub : Bitcoin" + "\n" +
            "ethrub : Ethereum" + "\n" +
            "bnbrub : Binance Coin" + "\n" +
            "dogerub : Dogecoin" + "\n" +
            "dotrub : Dotcoin" + "\n" +
            "adarub : Cardano" + "\n" +
            "_____________________________________" + "\n" +
            "Bots commands:" + "\n" +
            "/all: Get info about main cryptocoins" + "\n" +
            "/getN: Get top-list currency cryptocoins info of size N " + "\n" +
            "For example /get2" + "\n" +
            "_____________________________________" + "\n" +
            "Thanks. " + "\n" +
            "Created by ©DevDucks Team";
}
