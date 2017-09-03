import static org.apache.commons.lang3.text.WordUtils.capitalizeFully;

public enum Turn
{

   BLUE_CM, BLUE_PLAYER, RED_CM, RED_PLAYER;


   public String getOppositeTeam(){
      if(this.toString().contains("RED"))
         return "Blue";
      else
         return "Red";
   }

   public void getString() throws InterruptedException {
      String[] wordArray = this.toString().split("_"); //hi kyle my name is erik
      System.out.println(capitalizeFully(wordArray[0]) + " " + (wordArray[1].equals("CM") ? "Code Masters" : "Players")
              + ", it's your turn to " + (wordArray[1].equals("CM") ? "pick a word! Remember, you can only give ONE word," +
              " no more!" : "choose your cards!"));
      if(wordArray[1].equals("CM")){
         System.out.println("We'll give you 5 seconds to turn the computer away from the prying eyes of the players...");
         for(int i = 5; i >= 0; i--){
               Thread.sleep(1000);
               System.out.println(i + "...");

         }
      }

   }

}

