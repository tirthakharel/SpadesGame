public class Card{

    private int cardValue;
    private String cardType;

        public Card(int val, String type){
            cardType = type.toUpperCase().trim().replace("_","");
            cardValue = val;
        }

        public int getValue(){
            return cardValue;
        }

        public String getCardType(){
            //return Suit.valueOf(cardType);
            return cardType;
        }
}
