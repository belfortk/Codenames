import java.util.Random;
import java.util.Scanner;
import java.io.*;


public class Game
{

   private Card[] cards;
   private Turn turn;
   private Board board;
   private Map map;

   private boolean victory;
   private int turnCounter;
   private int blueRemaining;
   private int redRemaining;

   private String currentClue;
   private int currentGuesses;
   private Scanner console;
   
   

   public Game() throws FileNotFoundException
   {

      victory = false;
      console = new Scanner(System.in);

      generateCardList();
      board = new Board(cards);
      map =  new Map();
      turn = map.getStart();
      setStartingTeam();
      board.setCardTeams(map.getCardTeams());



   }

   public void run() throws InterruptedException {
      System.out.println("Welcome to Codenames! Please pick your Code Master and have them sit nearest to the computer.");
      while(!victory){
         turn.getString();
         System.out.println("The Score is: Blue " + blueRemaining + " and Red " + redRemaining);
         if(turn.toString().contains("CM")){
            board.revealCardsCM();
            System.out.println();
            System.out.println("Ok! Now type out the word you'd like to use as your clue.");
            System.out.println();
            currentClue = console.nextLine();
            while(!veritfyClue(currentClue)){
               System.out.println("Uh-oh! " + currentClue + " is not a valid clue. Please write a one-word," +
                       " not-on-the-board clue only!");
               currentClue = console.nextLine();
            }
            System.out.println(currentClue + " is your clue! I hope your team gets it...");
            Thread.sleep(1000);
            System.out.println("Now pick the number of guesses you'd like your team to have!");
            Thread.sleep(1000);
            currentGuesses = console.nextInt();
            while(currentGuesses < 1){
               System.out.println("Uh-oh! Your guesses needs to be greater than zero!");
               currentGuesses = console.nextInt();
            }
            System.out.println("You have given your team " + currentGuesses + " guesses!");
            Thread.sleep(1000);
            System.out.println("Ok. It's almost time to hand it over to the players.");
            Thread.sleep(2000);
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Get ready to turn the computer around in...");

            for(int i = 5; i >= 0; i--){
               Thread.sleep(1000);
               System.out.println(i + "...");

            }


         } else {

            board.printBoard();
            System.out.println("Players! Your clue is '" + currentClue + "' and you have " + currentGuesses +
                    " guesses to find the cards associated with it. Please input your guess " +
                    "below, one at a time.");
            while(currentGuesses > 0){
               console.nextLine();
               String guess = console.nextLine();
               try {
                  CardTeam guessCardTeam = board.getCardTeamFromString(guess, turn);
                  switch (guessCardTeam){
                     case RED:
                        if(turn.toString().contains("BLUE"))
                           currentGuesses=0;
                        else {
                           redRemaining--;
                           currentGuesses--;
                           System.out.println(" You have " + currentGuesses + " guesses remaining!");
                        }
                        break;
                     case BLUE:
                        if(turn.toString().contains("RED"))
                           currentGuesses=0;
                        else {
                           blueRemaining--;
                           currentGuesses--;
                           System.out.println(" You have " + currentGuesses + " guesses remaining!");
                        }
                        break;
                     case CIVILIAN:
                        currentGuesses=0;
                        break;
                     case ASSASSIN:
                        currentGuesses=0;
                        victory=true;
                        break;
                  }

               } catch (NullPointerException e){
                  System.out.println("Uh-oh! We couldn't find " + guess + " on the board." +
                          " Perhaps there was a spelling error?");
               }




            }

            if(redRemaining == 0 || blueRemaining == 0)
               victory = true;
            if(victory)
               break;

            System.out.println("And that's the turn, folks! Hand it back over to the next team's Code Masters");
            Thread.sleep(2000);
         }


            if(turnCounter==3)
               turnCounter=0;
            else
               turnCounter++;


         turn = Turn.values()[turnCounter];


      }

      if(redRemaining==0){
         System.out.println("Congrats Red team! You won!");
      } else if (blueRemaining==0){
         System.out.println("Congrats Blue team! You won!");
      }

   }


   private boolean veritfyClue(String clue) {
      if(clue.contains(" ") || board.isCardOnBoard(clue))
         return false;
      else
         return true;

   }


   private void setStartingTeam() {
      if(turn == Turn.RED_CM){
         redRemaining = 9;
         blueRemaining = 8;
      } else {
         redRemaining = 8;
         blueRemaining = 9;
      }

      turnCounter = turn.ordinal();
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

   public static void main(String[] args) throws FileNotFoundException, InterruptedException {
      Game game = new Game();
      game.run();

   }

}