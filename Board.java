import static org.apache.commons.lang3.text.WordUtils.capitalizeFully;

public class Board
{

   private static Card[][] board;
   
   
   public Board(Card[] cards)
   {
      board = generateBoard(cards);          
   }
   
   
   public Card[][] generateBoard(Card[] cards)
   {
   
      Card[][] newBoard = new Card[5][5];
      
      int count = 0;
      for(int i = 0; i < 5; i++)
      {
         for (int j = 0; j < 5; j++)
         {
            newBoard[i][j] = cards[count];
            count++;
         }    
      }
      
      return newBoard;
   
   }

   public boolean isCardOnBoard(String word){

      for (int z = 0; z < 5; z++) {
         for (int y = 0; y < 5; y++) {
            if (board[z][y].getWord().equals(word.toLowerCase())) {
               return true;
            }
         }
      }

      return false;
   }

   public CardTeam clickCard(String word, Turn team){
      for (int z = 0; z < 5; z++) {
         for (int y = 0; y < 5; y++) {
            Card c = board[z][y];
            if (c.getWord().equals(capitalizeFully(word))) {
               if(team.toString().contains(c.getCardTeam().toString())){
                  System.out.print("Yes! " + word + " was the correct card!");
               } else {
                  if (c.getCardTeam() == CardTeam.CIVILIAN){
                     System.out.println("Nope! The card was a civilian. Say goodbye to your turn...");
                  } else if (c.getCardTeam() == CardTeam.ASSASSIN){
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

      System.out.println();
      for (int count = 0; count < 5; count++){
         for (int i = 0; i < 5; i++)
         {
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
  

   public void setCardTeams(CardTeam[][] map)
   {
   
      for (int z = 0; z < 5; z++){
         for (int y = 0; y < 5; y++)
         {
            
            board[z][y].setCardTeam(map[z][y]);
                        
         }
      }
   
   }


   public void revealCardsCM()
   {
      System.out.printf("Legend: %s %s\n\n", Colors.colorize("Civilian", "Civilian"),
              Colors.colorize("Assassin", "Assassin"));
      for (int z = 0; z < 5; z++){
         for (int y = 0; y < 5; y++)
         {
                           
            System.out.print(board[z][y].getColorizedWord() + "   ");
            
            
         }
         System.out.println();
      }
   
   
   
   }


}