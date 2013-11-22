package darkevilmac.utilities.utils;

public class Utils {

    public static int curBlockID = 1749;

    public static void addToBlockID(int add) {
        curBlockID = curBlockID + add;
    }

    public static int getBlockID() {
        curBlockID = curBlockID + 1;
        return curBlockID;
    }

    public static void print(String input) {
        System.out.print("[Darkevilmac:Utilities]" + input);
    }

    public static void println(String input) {
        System.out.println("[Darkevilmac:Utilities]" + input);
    }

}
