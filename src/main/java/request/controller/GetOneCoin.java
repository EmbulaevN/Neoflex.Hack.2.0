package request.controller;

import org.json.JSONObject;
import request.coin.Coin;
import request.util.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class GetOneCoin {


    public static String getSymbolInfo(String symbol, Coin coin) throws IOException {
        URL url = new URL(Constant.GET_ONE + symbol);

        JSONObject jsonObject = GetOneCoin.getJSONFromURL(url);

        coin.setSymbol(jsonObject.getString("symbol"));
        coin.setPrice_rub(jsonObject.getDouble("price"));

        return "Symbol: " + coin.getSymbol() + "\n" +
                "Price: " + coin.getPrice_rub();
    }

    public static JSONObject getJSONFromURL(URL url) throws IOException {
        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";

        while (in.hasNext()) {
            result += in.nextLine();
        }

        return new JSONObject(result);
    }


}
