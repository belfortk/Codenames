import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.*;


public class Game {

    private Card[] cards;
    private Turn turn;
    private Board board;
    private Map map;

    private boolean victory;
    private int turnCounter;
    private int blueRemaining;
    private int redRemaining;
    private ArrayList<String> blueCluesList = new ArrayList<String>();
    private ArrayList<String> redCluesList = new ArrayList<String>();

    private String currentClue;
    private int currentGuesses;
    private Scanner console;

    private boolean debug;


    public Game() throws FileNotFoundException {

        victory = false;
        debug = false;
        console = new Scanner(System.in);

        generateCardList();
        board = new Board(cards);
        map = new Map();
        turn = map.getStart();
        setStartingTeam();
        board.setCardTeams(map.getCardTeams());


    }

    public void run() throws InterruptedException {
        System.out.println("Welcome to Codenames! Please pick your Code Master and have them sit nearest to the computer. \n");
        while (!victory) {
            System.out.printf(turn.getString(debug) + "! The Score is: %sBlue " + blueRemaining + "%s and %sRed " + redRemaining + "%s\n\n",
                    Colors.ANSI_BLUE, Colors.ANSI_RESET, Colors.ANSI_RED, Colors.ANSI_RESET);
            if (turn.toString().contains("CM")) {
                board.revealCardsCM();
                System.out.println();
                System.out.println("Ok! Now type out the word you'd like to use as your clue.");
                System.out.println();
                currentClue = console.nextLine();
                while (!verifyClue(currentClue)) {
                    System.out.println("Uh-oh! " + currentClue + " is not a valid clue. Please write a one-word," +
                            " not-on-the-board clue only!");
                    currentClue = console.nextLine();
                }
                if (turn.toString().contains("RED")) {
                    redCluesList.add(currentClue);
                } else {
                    blueCluesList.add(currentClue);
                }
                System.out.println(currentClue + " is your clue! I hope your team gets it...");
                Thread.sleep(1000);
                System.out.println("Now pick the number of guesses you'd like your team to have!");
                Thread.sleep(1000);
                currentGuesses = turnToInt(console.nextLine());
                while (currentGuesses < 1) {
                    currentGuesses = turnToInt(console.nextLine());
                }
                System.out.println("You have given your team " + currentGuesses + " guesses!");
                Thread.sleep(1000);
                System.out.println("Ok. It's almost time to hand it over to the players.");
                Thread.sleep(2000);
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println("Get ready to turn the computer around in...");
                if(!debug){
                    for (int i = 5; i > 0; i--) {
                        Thread.sleep(1000);
                        System.out.println(i + "...");

                    }
                }


            } else {


            System.out.println();System.out.println("Players! Your clue is '" + currentClue + "' and you have " + currentGuesses +
                    " guesses to find the cards associated with it. \nPlease input your guess " +
                    "below, one at a time, or type -1 to pass your guess! \n");if (turn.toString().contains("RED")) {

                    System.out.print("Previous clues: ");
                    for (int i = 0; i < redCluesList.size(); i++) {
                        System.out.print(redCluesList.get(i) + "   ");
                    }
                    System.out.println();

                } else {
                    System.out.print("Previous clues: ");
                    for (int i = 0; i < blueCluesList.size(); i++) {
                        System.out.print(blueCluesList.get(i) + "   ");
                    }
                    System.out.println();
                }

                while (currentGuesses > 0) {
               board.printBoard();
                    String guess = console.nextLine();

                    if(guess.equals("-1")){
                        currentGuesses=0;
                        System.out.println("You pass your remaining guesses.");
                    } else {

                        try {
                            CardTeam guessCardTeam = board.clickCard(guess, turn);
                            switch (guessCardTeam) {
                                case RED:
                                    redRemaining--;

                                    if (turn.toString().contains("BLUE"))
                                        currentGuesses = -1;

                                    else {

                                        currentGuesses--;
                                        if (currentGuesses == 0) {

                               System.out.println("\nYou have a bonus guess! Type in the word you want to guess or type -1 to pass");
                               board.printBoard();guess = console.nextLine();
                               if (guess.equals("-1")) {
                                                System.out.println("You pass your bonus guess.");
                                            } else {guessCardTeam = board.clickCard(guess, turn);

                                                if (guessCardTeam == CardTeam.BLUE) {
                                                    blueRemaining--;
                                                    System.out.println();
                                                } else if (guessCardTeam == CardTeam.RED) {
                                                    redRemaining--;
                                                    System.out.println();
                                                } else if (guessCardTeam == CardTeam.CIVILIAN) {
                                                    currentGuesses = -1;
                                                } else if (guessCardTeam == CardTeam.ASSASSIN) {
                                                    currentGuesses = -1;
                                                    victory = true;
                                                }
                                            }

                                        } else {
                                            System.out.println(" You have " + currentGuesses + " guesses remaining!");
                                        }
                                    }
                                    break;
                                case BLUE:
                                    blueRemaining--;
                                    if (turn.toString().contains("RED"))
                                        currentGuesses = -1;


                                    else {

                                    currentGuesses--;
                                    if (currentGuesses == 0) {
                                        System.out.println("\nYou have a bonus guess! Type in the word you want to guess or type -1 to pass");
                                        board.printBoard();guess = console.nextLine();
                                        if (guess.equals("-1")) {
                                                System.out.println("You pass your bonus guess.");
                                            } else {guessCardTeam = board.clickCard(guess, turn);
                                        if (guessCardTeam == CardTeam.RED) {
                                            redRemaining--;
                                            System.out.println();
                                        } else if (guessCardTeam == CardTeam.BLUE) {
                                            blueRemaining--;
                                            System.out.println();
                                        } else if (guessCardTeam == CardTeam.CIVILIAN) {
                                            currentGuesses = -1;
                                        } else if (guessCardTeam == CardTeam.ASSASSIN) {
                                            currentGuesses = -1;
                                            victory = true;
                                        }}

                                        } else {
                                            System.out.println(" You have " + currentGuesses + " guesses remaining!");

                                        }
                                    }
                                    break;
                                case CIVILIAN:
                                    currentGuesses = -1;
                                    break;
                                case ASSASSIN:
                                    currentGuesses = -1;
                                    victory = true;
                                    break;
                            }

                        } catch (NullPointerException e) {
                            System.out.println("Uh-oh! We couldn't find " + guess + " on the board." +
                                    " Perhaps there was a spelling error?");
                        }
                    }

                }

                if (redRemaining == 0 || blueRemaining == 0)
                    victory = true;
                if (victory)
                    break;

                System.out.println("And that's the turn, folks! Hand it back over to the next team's Code Masters");
                Thread.sleep(2000);
            }


            if (turnCounter == 3)
                turnCounter = 0;
            else
                turnCounter++;


            turn = Turn.values()[turnCounter];


        }

        if (redRemaining == 0) {
            System.out.println("Congrats Red team! You won!");
        } else if (blueRemaining == 0) {
            System.out.println("Congrats Blue team! You won!");
        }

    }

    private int turnToInt(String s) {
        try {
            int i = Integer.parseInt(s);
            if (i < 1)
                System.out.println("Uh-oh! Your guesses needs to be greater than zero!");
            else if (i > 9) {
                System.out.println("Uh-oh! Your guesses needs to be smaller than nine!");
                i = 0;
            }
            return i;
        } catch (NumberFormatException e) {
            System.out.println("That isn't a number!");
            return 0;
        }

    }


    private boolean verifyClue(String clue) {
        try {
            Integer.parseInt(clue);
            return false;
        } catch (NumberFormatException e) {
            if (clue.contains(" ") || board.isCardOnBoard(clue) || clue.isEmpty())
                return false;
            else
                return containsDashesAndIllegalWords(clue);
        }

    }

    private boolean containsDashesAndIllegalWords(String clue) {
        String[] words = clue.split("-");
        for(String w : words){
            if(board.isCardOnBoard(w))
                return false;
        }
        return true;

    }


    private void setStartingTeam() {
        if (turn == Turn.RED_CM) {
            redRemaining = 9;
            blueRemaining = 8;
        } else {
            redRemaining = 8;
            blueRemaining = 9;
        }

        turnCounter = turn.ordinal();
    }

    public void setDebug(boolean debug){
        this.debug = debug;
    }


    public void generateCardList() throws FileNotFoundException

    {
        cards = new Card[25];


        //get number of words in Dict file and add words to dict ArrayList
        File wordFile = new File("Dictionary.txt");
        Scanner inputFile1 = new Scanner(wordFile);
        int wordCount = 0;
        ArrayList<String> dict = new ArrayList<String>();


        try (Scanner sc = new Scanner(new FileInputStream(wordFile))) {

            while (sc.hasNext()) {
                dict.add(sc.nextLine());
                wordCount++;
            }

        }


        //randomly assign words from dict to cards[]

        Random r = new Random();
        Set<Integer> uniqueNumbers = new HashSet<>();
        while (uniqueNumbers.size() < 25) {
            uniqueNumbers.add(r.nextInt(wordCount));
        }
        int counter = 0;
        for (Integer i : uniqueNumbers) {
            cards[counter] = new Card(dict.get(i));
            counter++;
        }


    }

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        Game game = new Game();
        game.setDebug(args[0].equals("debug"));
        game.run();

    }

}