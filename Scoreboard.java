import java.util.ArrayList;

public class Scoreboard {

    ArrayList<Card> blueWords;
    ArrayList<Card> redWords;
    ArrayList<Card> civilianWords;
    ArrayList<Card> assassin;
    int longestWordLength;

    public ArrayList<Card> getBlueWords() {
        return blueWords;
    }

    public void setBlueWords(ArrayList<Card> blueWords) {
        this.blueWords = blueWords;
    }

    public ArrayList<Card> getRedWords() {
        return redWords;
    }

    public void setRedWords(ArrayList<Card> redWords) {
        this.redWords = redWords;
    }

    public ArrayList<Card> getCivilianWords() {
        return civilianWords;
    }

    public void setCivilianWords(ArrayList<Card> civilianWords) {
        this.civilianWords = civilianWords;
    }

    public ArrayList<Card> getAssassin() {
        return assassin;
    }

    public void setAssassin(ArrayList<Card> assassin) {
        this.assassin = assassin;
    }

    public int getLongestWordLength() {
        return longestWordLength;
    }

    public void setLongestWordLength(int longestWordLength) {
        this.longestWordLength = longestWordLength;
    }


    public Scoreboard() {

        blueWords = new ArrayList<Card>();
        redWords = new ArrayList<Card>();
        civilianWords = new ArrayList<Card>();
        assassin = new ArrayList<Card>();
        longestWordLength = 0;

    }

    public String toString() {


        System.out.printf("%s%s%s%s\n", buildSpaces("Blue"), buildSpaces("Red:"), buildSpaces("Civilian:"), buildSpaces("Assassin:"));

        for (int i = 0; i < 9; i++) {
            System.out.printf("%s%s%s%s\n", buildSpaces(blueWords.get(i).getWord()), buildSpaces(redWords.get(i).getWord()), buildSpaces((civilianWords.get(i).getWord())), buildSpaces((assassin.get(i).getWord())));

        }

        return "";

    }


    public int findLongestWordLength() {
        int longest = 0;
        for (int i = 0; i < blueWords.size(); i++) {
            if (blueWords.get(i).getWord().length() < longest) {
                longest = blueWords.get(i).getWord().length();
            }
        }

        for (int i = 0; i < redWords.size(); i++) {
            if (redWords.get(i).getWord().length() < longest) {
                longest = redWords.get(i).getWord().length();

            }
        }
        for (int i = 0; i < civilianWords.size(); i++) {
            if (civilianWords.get(i).getWord().length() < longest) {
                longest = civilianWords.get(i).getWord().length();
            }
        }

        for (int i = 0; i < assassin.size(); i++) {
            if (assassin.get(i).getWord().length() < longest) {
                longest = assassin.get(i).getWord().length();

            }

        }
        return longest;
    }


    public String buildSpaces(String word) {

        int blankspaces = longestWordLength - word.length();

        String space = " ";

        for (int i = 0; i < blankspaces; i++) {
            space = space + " ";
        }

        return word + space;

    }

    private int biggestStack() {

        int longest = blueWords.size();
        if (longest < redWords.size()) {
            longest = redWords.size();
        }
        if (longest < civilianWords.size()) {
            longest = civilianWords.size();
        }

        return longest;


    }


    public void fillListsWithEmptyStrings() {


        for (int i = 0; i < 9 - blueWords.size(); i++) {
            blueWords.add(new Card(""));
        }


        for (int i = 0; i < 9 - redWords.size(); i++) {
            redWords.add(new Card(""));
        }

        for (int i = 0; i < 2; i++) {
            civilianWords.add(new Card(""));
        }

        for (int i = 0; i < 8; i++) {
            assassin.add(new Card(""));
        }


    }


   public void changeColorScoreboard(String guess, CardTeam team){

        switch (team){

            case BLUE:

                for (int i = 0; i < blueWords.size(); i++){
                    if(blueWords.get(i).getWord().toLowerCase().equals(guess)){
                       blueWords.get(i).setWord("");
                    }
                }
            break;

            case RED:
                for (int i = 0; i < redWords.size(); i++){
                    if(redWords.get(i).getWord().toLowerCase().equals(guess)){
                        redWords.get(i).setWord("");
                    }
                }
                break;

            case CIVILIAN:
                for (int i = 0; i < civilianWords.size(); i++){
                    if(civilianWords.get(i).getWord().toLowerCase().equals(guess)){
                        civilianWords.get(i).setWord("");
                    }
                }
                break;

            case ASSASSIN:
                for (int i = 0; i < assassin.size(); i++){
                    if(assassin.get(i).getWord().toLowerCase().equals(guess)){
                        assassin.get(i).setWord("");
                    }
                }
                break;
        }



   }


}

