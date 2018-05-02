package Literature;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cards {
    private int cardID;
    
    public int getPoints() {
        return cardID;
    }

    public void setPoints(int cardID) {
        this.cardID = cardID;
    }

    List<Cards> cardList = new ArrayList<Cards>();    
    
    public List<Cards> getcardList() {
        return cardList;
    }

    public void setcardList(List<Cards> cardList) {
        this.cardList = cardList;
    }

    /**
     * Default construction
     */
    public Cards() {
    }

    /**
     * Parameter Construction
     */
    public Cards(int cardID) {
        setPoints(cardID);
    }
    
    public void InitialCards() {

        int[] point =  new int[48];
        for(int i=0;i<48;i++) {
            point[i] = i + 1;
         }
        for (int i = 0; i < point.length; i++) {
              cardList.add(new Cards(point[i]));
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Cards card : cardList) {
            stringBuffer.append(card.cardID);            
        }

    }
    
    public void Shuffle(){
        Random random = new Random();
        int size = cardList.size();
        for (int i = 0; i < cardList.size(); i++) {
            int index = random.nextInt(size);
            if (index != i) {
                Cards currentCard = new Cards();
                currentCard = cardList.get(i);

                Cards randomCard = new Cards();
                randomCard = cardList.get(index);
                cardList.set(index, currentCard);
                cardList.set(i, randomCard);
            }
        }

    }
}
