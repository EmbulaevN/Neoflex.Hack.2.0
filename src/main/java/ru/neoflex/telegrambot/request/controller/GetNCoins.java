package ru.neoflex.telegrambot.request.controller;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.neoflex.telegrambot.request.coin.Coin;
import ru.neoflex.telegrambot.request.util.Connection;
import ru.neoflex.telegrambot.request.util.Constant;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static ru.neoflex.telegrambot.request.util.GettingStringFromJson.addCoinsToList;
import static ru.neoflex.telegrambot.request.util.GettingStringFromJson.getStringFromJsonArray;


public class GetNCoins{

    public static List<Coin> run(String s) throws IOException, ParseException {

        String url = String.format(Constant.GET_N_COUNT, s);

        StringBuffer  response = new StringBuffer();
        HttpURLConnection connection = Connection.getConnection(url);

        getStringFromJsonArray(response, connection);

        Object obj = new JSONParser().parse(response.toString());

        JSONArray jo = (JSONArray)  obj;

        Iterator phonesItr = jo.iterator();
        System.out.println("Cripto:");

        List<Coin> coins = new ArrayList<Coin>();

        addCoinsToList(phonesItr, coins);

        return coins;

    }



}
