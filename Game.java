import java.util.Random;
import java.util.Scanner;
import java.io.*;


public class Game
{

   private Card[] cards;

   
   private Turn turn;
   private boolean victory;
   private int blueRemaining;
   private int redRemaining;
   Random rand = new Random();
   private static Board board;
   private Map map;
   
   

   public Game() throws FileNotFoundException
   {
   
      generateCardList();
   
      board = new Board(cards);
      map =  new Map();
      board.setCardTeams(map.getCardTeams());
      
   
   
   }    
   
   
   public void generateCardList() throws FileNotFoundException
   
   {
      cards = new Card[25];
      File wordFile = new File("Dictionary.txt");
      Scanner inputFile = new Scanner(wordFile);
      
      for(int i = 0; i <25; i++)
      {
         cards[i] = new Card(inputFile.nextLine());
      
      }
     
   
      
   }

   public static void main(String[] args) throws FileNotFoundException
   {
      Game test = new Game();
      
      board.revealCardsCM();
   
   
   }

}