import java.util.Random;

public class Map
{

   Turn start;
   CardTeam[][] map;
   Random rand = new Random();



   public Map()
   {
      start = Turn.values()[2 * rand.nextInt(2)] ;
      
      map = new CardTeam[5][5];
      
      int countBlue;
      int countRed;
      int countCiv = 7;
      int assassin = 1;
      
        
      if (start == Turn.RED_CM)
      {
         countRed = 9;
         countBlue = 8;
      }
      
      else
      {
         countBlue=9;
         countRed = 8;
      }
      
      int[] counters =  new int[]{ countBlue, countRed, countCiv, assassin};
      
      int count = 25;
      
      for (int i = 0; i < 5; i++)
      {
         for (int j = 0; j <5; j++)
         {
            int index =  rand.nextInt(4);
          
            while (counters[index] == 0)
            {
               index =  rand.nextInt(4);
            }
            
            map[i][j] = CardTeam.values()[index];
            counters[index]--;
          
         
         }
      }
   
      
   }
   
   public CardTeam[][] getCardTeams()
   {
   
      return map;
   
   }

   public Turn getStart() {
      return start;
   }


   public static void main(String[] args)
   {
   
      Map map = new Map();
   }



}