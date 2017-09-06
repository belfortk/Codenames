package code;

import static org.apache.commons.lang3.text.WordUtils.capitalizeFully;

public class Card {


    private String word;
    private boolean covered;
    private CardTeam team;

    public Card(String word) {

        this.word = capitalizeFully(word);
        covered = false;
        team = null;

    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setCovered() {
        if (covered) {
            covered = false;
        } else {
            covered = true;
        }
    }

    public boolean getCovered() {
        return covered;
    }


    public CardTeam getCardTeam() {
        return team;

    }


    public void setCardTeam(CardTeam teamName) {
        this.team = teamName;
    }


    public String toString() {
        return covered ? getCoveredWord() : word;

    }

    public String showCodeMasterCards() {
        return covered ? getCoveredWord() : getColorizedWord();
    }

    public String getColorizedWord() {
        return Colors.colorize(capitalizeFully(team.toString()), word);
    }

    public String getCoveredWord() {
        return Colors.colorize(capitalizeFully(team.toString()), word) + " is Covered";
    }


}