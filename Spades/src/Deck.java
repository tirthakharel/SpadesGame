import java.io.File;
import java.util.*;


public class Deck {
    private Map<File, Card> Cards;

    public Deck(){
        Cards = new HashMap<>();
        File[] images = new File("resources/card_deck/").listFiles();
        for (File f : images){
            if (f.getName().contains(".png")) {
                String c = f.getName().substring(0, f.getName().length() - 4); //removes ".png" from file name
                char[] s = c.toCharArray();
                int cardval = Integer.parseInt(c.substring(0,c.indexOf("_")));
                String cardtype = c.substring(c.indexOf("_")+1);
                Card card = new Card(cardval, cardtype);
                Cards.put(f, card);
            }
        }
    }

    public File getCardFile(Card c){
        for (Map.Entry<File, Card> entry : Cards.entrySet()){
            if (Objects.equals(c, entry.getValue()))
                return entry.getKey();
        }
        return null;
    }

    public Collection<Card> getPlayingCards() {
        List<Card> playingCards = new LinkedList<>();
        List<File> cardFiles = new ArrayList<>(Cards.keySet());
        for(int i = 0; i <= 12; i++){
            int size = cardFiles.size();
            File item = cardFiles.get(new Random().nextInt(size));
            playingCards.add(i, Cards.get(item));
            cardFiles.remove(item);
            Cards.remove(item);
        }
        return playingCards;
    }

    public int size(){
        return Cards.size();
    }

    private void remove(Card c){
        Cards.remove(getCardFile(c));
    }
}
