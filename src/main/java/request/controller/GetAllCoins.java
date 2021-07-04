package request.controller;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import request.coin.Coin;
import request.util.Connection;
import request.util.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static request.util.GettingStringFromJson.addCoinsToList;
import static request.util.GettingStringFromJson.getStringFromJsonArray;

public class GetAllCoins {

    public static List<Coin> run() throws IOException, ParseException {
        String url = Constant.GET_SIX;

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

    public static ArrayList<Coin> getAllSymbols() throws IOException {
        URL url = new URL("https://api.binance.com/api/v1/ticker/price");

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }

        org.json.JSONArray jsonArray = new org.json.JSONArray(result);

        int jsonArrLength = jsonArray.length();
        ArrayList<Coin> symbols = new ArrayList<>(jsonArrLength);

        for (int i = 0; i < jsonArrLength; i++) {
            Coin symbolModel = new Coin();
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            symbolModel.setSymbol(jsonObj.getString("symbol"));
            symbolModel.setPrice_rub(jsonObj.getDouble("price"));
            symbols.add(symbolModel);
        }

        return symbols;
    }

}
