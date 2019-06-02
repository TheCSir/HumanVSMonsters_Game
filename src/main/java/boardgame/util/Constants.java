package boardgame.util;


/**
 * Default settings to avoid hardcoding variables in other classes.
 */
public class Constants {


    //Default board settings
    public static final int DEFAULTBOARDROWS = 10;

    public static final int DEFAULTBOARDCOLUMNS = 10;

    public static final double xStartOffset = 40;

    public static final double yStartOffset = 40;
    public static final double TILERADIUS = 40;


    //Default players data.
    public static final String PLAYER1 = "HumanPlayer";
    public static final String PLAYER2 = "MonsterPlayer";

    public static  final int INITIALHEALTH = 10;
    public static  final int INITIALMINIONHEALTH = 2;
    public static final String IDEALSTATUS = "normal";

    public static final  int DEFAULTABILITYCD = 10;


    public static final double SHIELDDEFENCE = 2;
    public static final int MAXTURNS = 50;
}
