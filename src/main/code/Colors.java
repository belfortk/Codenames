package code;

public class Colors {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    public static String colorize(String team, String word){
        String str = "";
        switch(team){
            case "Blue":
                str += Colors.ANSI_BLUE + word;
                break;
            case "Red":
                str += Colors.ANSI_RED + word;
                break;
            case "Civilian":
                str += Colors.ANSI_GREEN + word;
                break;
            case "Assassin":
                str += Colors.ANSI_PURPLE + word;
                break;
        }

        return str += Colors.ANSI_RESET;
    }

}
