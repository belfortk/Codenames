import static org.apache.commons.lang3.text.WordUtils.capitalizeFully;

public class Card{


   private String word;
   private boolean covered;
   private CardTeam team;
   
   public Card(String word){
   
      this.word = word;
      covered = false;
      team = null;
   
   }
   
   public String getWord()
   {
      return word;
   }
   
   public void setWord(String word)
   {
      this.word = word;
   }
   
   public void setCovered()
   {
      if (covered)
      {
         covered = false;
      }
      else
      {
         covered = true;
      }
   }

   public CardTeam getCardTeam()
   {
      return team;
   
   }


   public void setCardTeam(CardTeam teamName)
   {
      this.team = teamName;
   }
   
   
   
   public String toString()
   {
   
      return covered ? team.toString() : word;
   
   }
   
   public String showCardDetails()
   
   {
   
   //will return attributes of Card Object
      return covered ? team.toString() : capitalizeFully(word) + " -- " + team;
   
   }
  

}