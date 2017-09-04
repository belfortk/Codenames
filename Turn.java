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

   public String getString() throws InterruptedException {
      String team = getTeam();
      String role = getRole();
      String teamAndRoleString = getTeamAndRoleString();

      if(role.equals("CM")){
         System.out.println(teamAndRoleString + ", it's your turn to pick a word! Remember, you can only give ONE word," +
                 " no more!");
         System.out.println("We'll give you 5 seconds to turn the computer away from the prying eyes of the players...");
         for(int i = 5; i > 0; i--){
               Thread.sleep(1000);
               System.out.println(i + "...");

         }
      }

      System.out.println();

      return teamAndRoleString;

   }

   public String getTeam() {
      return capitalizeFully(this.toString().split("_")[0]);
   }

   public String getRole(){
      return this.toString().split("_")[1];
   }

   public String getTeamAndRoleString(){
      return Colors.colorize(getTeam(), getTeam() + " " + (getRole().equals("CM") ? "Code Masters" : "Players"));

   }

}

