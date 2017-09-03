import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList; 
import java.util.*;



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
      
      
      //get number of words in Dict file and add words to dict ArrayList
      File wordFile = new File("Dictionary.txt");
      Scanner inputFile1 = new Scanner(wordFile);        
      int wordCount = 0;   
      ArrayList<String> dict = new ArrayList<String>();
      
      
      try(Scanner sc = new Scanner(new FileInputStream(wordFile))){
         
         while(sc.hasNext()){
            dict.add(sc.nextLine());
            wordCount++;
         }
         System.out.println("Number of words: " + wordCount);
      }
      
      
      
      //randomly assign words from dict to cards[]
      
      Random r = new Random();
      Set<Integer> uniqueNumbers = new HashSet<>();
      while (uniqueNumbers.size()<25){
         uniqueNumbers.add(r.nextInt(wordCount));
      }
      int counter = 0;
      for (Integer i : uniqueNumbers)
      {
         cards[counter] = new Card(dict.get(i));
         counter++;
      }
      
     
      
      
      
      
      /***
      for(int i = 0; i <25; i++)
      {
         cards[i] = new Card(dict.get(i));
      
      }
     ***/
   
      
   }

   public static void main(String[] args) throws FileNotFoundException
   {
      Game test = new Game();
      
      board.revealCardsCM();
   
   
   }

}