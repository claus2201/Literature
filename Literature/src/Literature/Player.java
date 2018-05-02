package Literature;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int  playerId;
    private int  playerTeam;
    private int  points;
//    private int[] cards;
    

    public Player(int playerid, int playTeam, int points){
        this.playerId = playerid;
        this.playerTeam = playTeam;
        this.points = points;
    }
    
    public int getPlayerId()
    {
            return playerId;
    }

    public void setPlayerId(int playerId)
    {
            this.playerId = playerId;
    }

    public int getPlayerTeam()
    {
            return playerTeam;
    }

    public void setPlayerTeam(int playerTeam)
    {
            this.playerTeam = playerTeam;
    }

    public int getPoints()
    {
            return points;
    }

    public void setPoints(int points)
    {
            this.points += points;
    }


    
    List<Cards> PlayerCards = new ArrayList<Cards>();
    
    public List<Cards> getPlayerCards() {
        return PlayerCards;
    }
    public void setPlayerCards(List<Cards> playerCards) {
        PlayerCards = playerCards;
    }
    
    public void TakeACard(Cards card){
        PlayerCards.add(card);
    }
}
