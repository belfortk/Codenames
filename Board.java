import java.util.ArrayList;

import static org.apache.commons.lang3.text.WordUtils.capitalizeFully;


public class Board {

    ArrayList<Card> blueWords;
    ArrayList<Card> redWords;
    ArrayList<Card> civilianWords;
    ArrayList<Card> assassin;
    int longestWordLength;

    private static Card[][] board;


    public Board(Card[] cards) {
        board = generateBoard(cards);

        blueWords = new ArrayList<Card>();
        redWords = new ArrayList<Card>();
        civilianWords = new ArrayList<Card>();
        assassin = new ArrayList<Card>();


    }


    public Card[][] generateBoard(Card[] cards) {

        Card[][] newBoard = new Card[5][5];

        int count = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                newBoard[i][j] = cards[count];
                count++;
            }
        }

        return newBoard;

    }

    public boolean isCardOnBoard(String word) {

        for (int z = 0; z < 5; z++) {
            for (int y = 0; y < 5; y++) {
                if (board[z][y].getWord().equals(word.toLowerCase())) {
                    return true;
                }
            }
        }

        return false;
    }

    public CardTeam clickCard(String word, Turn team) {
        for (int z = 0; z < 5; z++) {
            for (int y = 0; y < 5; y++) {
                Card c = board[z][y];
                if (c.getWord().equals(capitalizeFully(word))) {
                    if (team.toString().contains(c.getCardTeam().toString())) {
                        System.out.print("Yes! " + word + " was the correct card!");
                    } else {
                        if (c.getCardTeam() == CardTeam.CIVILIAN) {
                            System.out.println("Nope! The card was a civilian. Say goodbye to your turn...");
                        } else if (c.getCardTeam() == CardTeam.ASSASSIN) {
                            System.out.println("Oh no!!!! You picked the assassin! Game over! " + team.getOppositeTeam() +
                                    " wins!");
                        } else {
                            System.out.println("Whoops! You picked the other team's card!");
                        }
                    }
                    c.setCovered();
                    return c.getCardTeam();
                }

            }
        }

        return null;
    }



   public void printBoard(){
System.out.println();      for (int count = 0; count < 5; count++){
         for (int i = 0; i < 5; i++){

            System.out.print(board[count][i] + "  ");
         }
         System.out.println();
      }
      System.out.println();
   }
   
  
   public Card[][] getBoard()
   {
   
      return board;
   
   }
  

    public void setCardTeams(CardTeam[][] map) {

        for (int z = 0; z < 5; z++) {
            for (int y = 0; y < 5; y++) {

                board[z][y].setCardTeam(map[z][y]);

            }
        }

        putCardsIntoStacks();

    }


    public void revealCardsCM() {
        System.out.printf("Legend: %s %s\n\n", Colors.colorize("Civilian", "Civilian"),
                Colors.colorize("Assassin", "Assassin"));
        for (int z = 0; z < 5; z++) {
            for (int y = 0; y < 5; y++) {

                System.out.print(board[z][y].getColorizedWord() + "   ");


            }
            System.out.println();
        }


    }

    public void putCardsIntoStacks() {
        int biggest = 0;

        for (int i = 4; i > -1; i--) {
            for (int j = 4; j > -1; j--) {
                String word = board[i][j].getWord();
                if (biggest < word.length()) {
                    biggest = word.length();
                }
                if (board[i][j].getCardTeam() == CardTeam.BLUE) {
                    blueWords.add(board[i][j]);
                } else if (board[i][j].getCardTeam() == CardTeam.RED) {
                    redWords.add(board[i][j]);
                } else if (board[i][j].getCardTeam() == CardTeam.CIVILIAN) {
                    civilianWords.add(board[i][j]);
                } else if (board[i][j].getCardTeam() == CardTeam.ASSASSIN) {
                    assassin.add(board[i][j]);
                }
            }

        }
        longestWordLength = biggest;

        fillListsWithEmptyStrings();

    }

    private void fillListsWithEmptyStrings() {


        for (int i = 0; i < 9 - blueWords.size(); i++) {
            blueWords.add(new Card(""));
        }


        for (int i = 0; i < 9 - redWords.size(); i++) {
            redWords.add(new Card(""));
        }

        for (int i = 0; i < 2; i++) {
            civilianWords.add(new Card(""));
        }

        for (int i = 0; i < 8; i++) {
            assassin.add(new Card(""));
        }


    }





    public void printCardStacks() {

        System.out.printf("%s%s%s%s\n", buildSpaces("Blue"), buildSpaces("Red:"), buildSpaces("Civilian:"), buildSpaces("Assassin:"));

        for (int i = 0; i < biggestStack(); i++) {
            System.out.printf("%s%s%s%s\n", buildSpaces(blueWords.get(i).getWord()), buildSpaces(redWords.get(i).getWord()), buildSpaces((civilianWords.get(i).getWord())), buildSpaces((assassin.get(i).getWord())));

        }

    }

    private String buildSpaces(String word) {

        int blankspaces = longestWordLength - word.length();

        String space = " ";

        for (int i = 0; i < blankspaces; i++) {
            space = space + " ";
        }

        return word + space;

    }

    private int biggestStack() {

        int longest = blueWords.size();
        if (longest < redWords.size()) {
            longest = redWords.size();
        }
        if (longest < civilianWords.size()) {
            longest = civilianWords.size();
        }

        return longest;


    }


}
