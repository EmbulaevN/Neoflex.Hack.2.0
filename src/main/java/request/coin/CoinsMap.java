package request.coin;

import java.util.HashMap;
import java.util.Map;

public class CoinsMap {

    public static Map<String, String> mapOfCoins = new HashMap<String, String>();

    static {
        mapOfCoins.put("BTCRUB", "Bitcoin");
        mapOfCoins.put("ETHRUB", "Ethereum");
        mapOfCoins.put("BNBRUB", "Binance Coin");
        mapOfCoins.put("DOGERUB", "Dogecoin");
        mapOfCoins.put("DOTRUB", "Dotcoin");
        mapOfCoins.put("ADARUB", "Cardano");
    }

    public static String getCoinByCode(String code) {

        if(mapOfCoins.containsKey(code))
            return mapOfCoins.get(code);
        else
            return "Unknown";
    }
    public static String getCoinByKey(String key) {

        if(mapOfCoins.containsKey(key))
            return mapOfCoins.get(key);
        else
            return "Unknown";
    }


}
