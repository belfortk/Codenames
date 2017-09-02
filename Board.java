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
      
      printBoard(newBoard);
      
      return newBoard;
   
   }



   public void printBoard(Card[][] board){
      for (int count = 0; count < 5; count++){
         for (int i = 0; i < 5; i++)
         {
            System.out.print(board[count][i] + "  ");
         }
         System.out.println();
      }
   
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


   public static void revealCardsCM()
   {
   
      for (int z = 0; z < 5; z++){
         for (int y = 0; y < 5; y++)
         {
                           
            System.out.print(board[z][y].showCardDetails() + "   ");
            
            
         }
         System.out.println();
      }
   
   
   
   }


}