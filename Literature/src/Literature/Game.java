package Literature;

import java.awt.*;
import javax.swing.*;

import Literature.Game;

import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class Game extends JFrame
{
    private JButton jbaskCard = new JButton("Ask");
    private JButton jbnewGame = new JButton("New game");
    private JTextField jtCardType = new JTextField();
    private JTextField jtCardNumber = new JTextField();
    private JTextField jtOpponentID = new JTextField();
    private JLabel jlCardType = new JLabel("Card Type:");  
    private JLabel jlCardNumber = new JLabel("Card number:");  
    private JLabel jlOpponent = new JLabel("Opponent's ID:");  
    private ImagePanel pp = new ImagePanel();
    private JLabel playerID = new JLabel("Current Player:");  
    private JLabel team = new JLabel("Curent Team:");  
    private JLabel points = new JLabel("Current points:");  
    private JLabel currentPlayer = new JLabel();  
    private JLabel currentTeam = new JLabel();  
    private JLabel currentPoints = new JLabel(); 
    private int    currentPlayerIdx = 1;
    List<Player> playerList = new ArrayList<Player>();
    Cards cards = new Cards();


    public Game()
    {
        JPanel p1 = new JPanel(new GridLayout(1,8));
//        p1.add(jbnewGame);
        p1.add(jlCardType);
        p1.add(jtCardType);
        p1.add(jlCardNumber);
        p1.add(jtCardNumber);
        p1.add(jlOpponent);
        p1.add(jtOpponentID);
        p1.add(jbaskCard);
        
        JPanel p3 = new JPanel(new GridLayout(1,6));
        p3.add(playerID);
        p3.add(currentPlayer);
        p3.add(team);
        p3.add(currentTeam);
        p3.add(points);
        p3.add(currentPoints);
        
        add(p1,BorderLayout.SOUTH);
        add(pp,BorderLayout.CENTER);
        add(p3,BorderLayout.NORTH);
 
        ButtonListener listener = new ButtonListener();
        jbaskCard.addActionListener(listener);
//        jbnewGame.addActionListener(listener);
		initialGame();
                     
    }
 
    class ButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == jbnewGame)
            {
                initialGame();
                jbnewGame.setVisible(false);
            }
            else if(e.getSource() == jbaskCard)
            {
                
                  int opponentID = Integer.parseInt(jtOpponentID.getText());
                  String cardType = jtCardType.getText();
                  String cardNumber = jtCardNumber.getText();
                  
                  if(opponentID != currentPlayerIdx) {
                	  
                  int cardAskedID = getAskedCardID(cardType,cardNumber);
                  if(isExist(opponentID,cardAskedID)) {
                	     askForCard(opponentID,cardAskedID);
             			Cards card = new Cards(cardAskedID);
             			playerList.get(opponentID-1).getPlayerCards().remove(card);
            		    for(int i = 0 , len= playerList.get(opponentID-1).getPlayerCards().size();i<len;++i){  
            		        if(playerList.get(opponentID-1).getPlayerCards().get(i).getPoints() == cardAskedID){  
            		        	playerList.get(opponentID-1).getPlayerCards().remove(i);  
            		             --len;  
            		             --i;
            		       }  
            		      } 
            		      removeSet();
                  }
                  else {
                     getNextPlayer(opponentID);
//                     if(playerList.get(currentPlayerIdx-1).getPlayerCards().size() == 0) {
//                         getNextPlayer(opponentID);
//                     }
                  }

      			if(playerList.get(0).getPlayerCards().size() == 0 && playerList.get(1).getPlayerCards().size() == 0 &&
    					playerList.get(2).getPlayerCards().size() == 0 && playerList.get(3).getPlayerCards().size() == 0 &&
    					playerList.get(4).getPlayerCards().size() == 0 && playerList.get(5).getPlayerCards().size() == 0){
      				compareWinner();
      			}
      			}
                  else {
                	  JOptionPane.showMessageDialog(null, "Cannot ask card from yourself!! ", " Alert", JOptionPane.ERROR_MESSAGE);
                  }
            }
            }
    }
    
        private int getAskedCardID(String cardType, String cardNumber) {
          int cardAskedID;
          int cardTypeID = 0;
          int cardNumberID = 0;
         
          if(cardType.matches("club")) {
        	    cardTypeID = 1;
          }
          else if(cardType.matches("spade")) {
      	    cardTypeID = 2;
          }
          else if(cardType.matches("heart")){
        	    cardTypeID = 3; 	  
          }
          else if(cardType.matches("diamond")) {
        	    cardTypeID = 4;
          }



        if(cardNumber.matches("A")) {
      	  cardNumberID = 1;
        }
        else if(cardNumber.matches("K")) {
        	  cardNumberID = 2;
          }
        else if(cardNumber.matches("Q")) {
      	  cardNumberID = 3;
        }
        else if(cardNumber.matches("J")) {
      	  cardNumberID = 4;
        }
        else if(cardNumber.matches("10")) {
      	  cardNumberID = 5;
        }
        else if(cardNumber.matches("9")) {
      	  cardNumberID = 6;
        }
        else if(cardNumber.matches("8")) {
      	  cardNumberID = 7;
        }
        else if(cardNumber.matches("7")) {
      	  cardNumberID = 8;
        }
        else if(cardNumber.matches("6")) {
      	  cardNumberID = 9;
        }
        else if(cardNumber.matches("5")) {
      	  cardNumberID = 10;
        }
        else if(cardNumber.matches("4")) {
      	  cardNumberID = 11;
        }
        else if(cardNumber.matches("3")) {
      	  cardNumberID = 12;
        }

          
          cardAskedID = (4 * (cardNumberID - 1)) + cardTypeID;
          return cardAskedID;
    }
      
		public void initialGame() {
	        cards.InitialCards();
	        cards.Shuffle();
	        playerList.add(null);
	        playerList.add(null);
	        playerList.add(null);
	        playerList.add(null);
	        playerList.add(null);
	        playerList.add(null);
	        InputPlayerInfo(playerList);
	        Deal(playerList,cards);      

            repaint();    
		}
		

		private void Deal(List<Player> playerList, Cards cards) {
	        Random random = new Random();
	        int index;
	        for (int i = 0; i < 8; i++) { 
	            for (int j = 0; j < playerList.size(); j++) {
	                index = random.nextInt(cards.getcardList().size());
	                playerList.get(j).TakeACard(cards.getcardList().get(index));
	                cards.getcardList().remove(index);
	            }
	        }
	}


		private void InputPlayerInfo(List<Player> playerList) {
	        for (int i = 0; i < playerList.size(); i++) {            
            int playerID = i+1;
            int playerTeam = i%2+1;
            int points = 0;
            playerList.set(i, new Player(playerID, playerTeam, points));       
        }
	}
		
		
		private void askForCard(int opponentID, int cardAskedID) {
			Cards card = new Cards(cardAskedID);
			playerList.get(currentPlayerIdx-1).getPlayerCards().add(card);
//		    for(int i = 0 , len= playerList.get(opponentID-1).getPlayerCards().size();i<len;++i){  
//		        if(playerList.get(opponentID-1).getPlayerCards().get(i).getPoints() == cardAskedID){  
//		        	playerList.get(opponentID-1).getPlayerCards().remove(card);  
//		             --len;//减少一个  
//		             --i;//多谢deny_guoshou指正，如果不加会出现评论1楼所说的情况。  
//		       }  
//		    }
			repaint();
		}
        
		private void removeSet() {
			boolean mA =false;
			boolean mK =false;
			boolean mQ =false;
			boolean mJ =false;
			boolean m10 =false;
			boolean m9 =false;
			boolean m8 =false;
			boolean m7 =false;
			boolean m6 =false;
			boolean m5 =false;
			boolean m4 =false;
			boolean m3 =false;
		    for(int i = 0 , len= playerList.get(currentPlayerIdx-1).getPlayerCards().size();i<len;++i){  
                    if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 1 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 2 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 3 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 4 ) {
                    	  mA = true;
                    }
                    if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 5 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 6 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 7 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 8 ) {
                    	  mK = true;
                    }
                    if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 9 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 10 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 11 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 12 ) {
                    	  mQ = true;
                    }
                    if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 13 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 14 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 15 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 16 ) {
                    	  mJ = true;
                    }
                    if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 17 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 18 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 19 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 20 ) {
                    	  m10 = true;
                    }
                    if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 21 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 22 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 23 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 24 ) {
                    	  m9 = true;
                    }
                    if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 25 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 26 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 27 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 28 ) {
                    	  m8 = true;
                    }
                    if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 29 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 30 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 31 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 32 ) {
                    	  m7 = true;
                    }
                    if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 33 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 34 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 35 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 36 ) {
                    	  m6 = true;
                    }
                    if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 37 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 38 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 39 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 40 ) {
                    	  m5 = true;
                    }
                    if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 41 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 42 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 43 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 44 ) {
                    	  m4 = true;
                    }
                    if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 45 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 46 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 47 ||
                    		playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 48 ) {
                    	  m3 = true;
                    }
		      }
		    if(mA && mK && mQ && mJ && m10 && m9) {
	      	    	for(int i = 0 , len= playerList.get(currentPlayerIdx-1).getPlayerCards().size();i<len;++i) {
	      	    		if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 1) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 2) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 3) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 4) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    	}
	      	    	
	      	    	for(int i = 0 , len= playerList.get(currentPlayerIdx-1).getPlayerCards().size();i<len;++i) {
	      	    		if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 5) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 6) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 7) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 8) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    	}
	      	    	
	      	    	for(int i = 0 , len= playerList.get(currentPlayerIdx-1).getPlayerCards().size();i<len;++i) {
	      	    		if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 9) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 10) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 11) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 12) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    	}
	      	    	
	      	    	for(int i = 0 , len= playerList.get(currentPlayerIdx-1).getPlayerCards().size();i<len;++i) {
	      	    		if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 13) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 14) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 15) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 16) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    	}
		    	
	      	    	for(int i = 0 , len= playerList.get(currentPlayerIdx-1).getPlayerCards().size();i<len;++i) {
	      	    		if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 17) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 18) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 19) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 20) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    	}
		    	
	      	    	for(int i = 0 , len= playerList.get(currentPlayerIdx-1).getPlayerCards().size();i<len;++i) {
	      	    		if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 21) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 22) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 23) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 24) {
                        	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                         i--;
                         len--;
                         break;
	      	    		}
	      	    	}
		      	playerList.get(currentPlayerIdx-1).setPoints(2);
		      	repaint();
		    }
		    if(m8 && m7 && m6 && m5 && m4 && m3) {
		    	
      	    	for(int i = 0 , len= playerList.get(currentPlayerIdx-1).getPlayerCards().size();i<len;++i) {
      	    		if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 25) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 26) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 27) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 28) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    	}
      	    	
      	    	for(int i = 0 , len= playerList.get(currentPlayerIdx-1).getPlayerCards().size();i<len;++i) {
      	    		if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 29) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 30) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 31) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 32) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    	}
      	    	
      	    	for(int i = 0 , len= playerList.get(currentPlayerIdx-1).getPlayerCards().size();i<len;++i) {
      	    		if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 33) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 34) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 35) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 36) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    	}
      	    	
      	    	for(int i = 0 , len= playerList.get(currentPlayerIdx-1).getPlayerCards().size();i<len;++i) {
      	    		if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 37) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 38) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 39) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 40) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    	}
	    	
      	    	for(int i = 0 , len= playerList.get(currentPlayerIdx-1).getPlayerCards().size();i<len;++i) {
      	    		if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 41) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 42) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 43) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 44) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    	}
	    	
      	    	for(int i = 0 , len= playerList.get(currentPlayerIdx-1).getPlayerCards().size();i<len;++i) {
      	    		if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 45) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 46) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 47) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    		else if(playerList.get(currentPlayerIdx-1).getPlayerCards().get(i).getPoints() == 48) {
                    	playerList.get(currentPlayerIdx-1).getPlayerCards().remove(i);  
                     i--;
                     len--;
                     break;
      	    		}
      	    	}
		      	playerList.get(currentPlayerIdx-1).setPoints(1);
		      	repaint();
		    }
		    
		    
		}
		
		private void compareWinner(){
			int teamPoints1 = playerList.get(0).getPoints() + playerList.get(2).getPoints() + playerList.get(4).getPoints();
			int teamPoints2 = playerList.get(1).getPoints() + playerList.get(3).getPoints() + playerList.get(5).getPoints();

				if(teamPoints1 > teamPoints2) {
					JOptionPane.showMessageDialog(null, "Team1 Wins!", " Congratulations", JOptionPane.ERROR_MESSAGE);
				}
				else if(teamPoints1 < teamPoints2) {
					JOptionPane.showMessageDialog(null, "Team2 Wins!", " Congratulations", JOptionPane.ERROR_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "Team1 and Team2 are on a tie! ", " Congratulations", JOptionPane.ERROR_MESSAGE);
				
			}
		}
        
	    private void getNextPlayer(int opponentID)
	    {
                currentPlayerIdx = opponentID;
                repaint();
	    }
   
        private boolean isExist (int opponentID, int cardAskedID) {
        	   boolean isExist = false;
        	   for(int i=0;i<playerList.get(opponentID - 1).getPlayerCards().size(); i++) {
        		   if(playerList.get(opponentID-1).getPlayerCards().get(i).getPoints() == cardAskedID) {
        			   isExist = true;
        			   break;
        		   }
        	   }
        	   return isExist;
        }
	
    class ImagePanel extends JPanel
    {

        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
//            initialGame();
            int i;
            int w;
            if(playerList.get((currentPlayerIdx-1)).getPlayerCards().size() >= 8) {
            	w = getWidth()/playerList.get((currentPlayerIdx-1)).getPlayerCards().size();
            }
            else{
            	w = 100;
			}

            int h = getHeight();
            int x = 0;
            int y = 0;

            for(i = 0;i < playerList.get((currentPlayerIdx-1)).getPlayerCards().size(); i++)
            {
                ImageIcon imageIcon = new ImageIcon("image/card/" + playerList.get((currentPlayerIdx-1)).getPlayerCards().get(i).getPoints() + ".png");
//                ImageIcon imageIcon = new ImageIcon("image/card/" + i + ".png");
                Image image = imageIcon.getImage();
                if(image != null)
                {
                    g.drawImage(image,x,y,w,h,this);
                }
                x += w;
            }
            currentPlayer.setText(Integer.toString(currentPlayerIdx));
            currentTeam.setText(Integer.toString((2 - currentPlayerIdx%2)));
            currentPoints.setText(Integer.toString(playerList.get(currentPlayerIdx-1).getPoints()));
        }
   
    }
 
}
    



