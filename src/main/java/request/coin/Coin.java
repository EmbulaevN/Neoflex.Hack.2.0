package request.coin;

public class Coin {

   private String symbol;

   private Double price_rub;



   public void setSymbol(String symbol) {
      this.symbol = symbol;
   }



   public void setPrice_rub(Double price_rub) {
      this.price_rub = price_rub;
   }



   public String getSymbol() {
      return symbol;
   }



   public Double getPrice_rub() {
      return price_rub;
   }

   @Override
   public String toString() {
      return "Symbol: " + symbol + "\n" +
              "Price: " + price_rub + "\n\n";
   }

   /*@Override
   public String toString() {
      return String.format(
              "Name : %s" + "\n" +
                      "Symbol: %s" + "\n" +
                      "RUB : %s" + "\n" +
                      "\n",
              getSymbol(),  getPrice_rub());
   }*/
}

