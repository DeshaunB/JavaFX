package Home;

public class TextFormatting {

    public static boolean AnalyzeLine(String line, String check){
        if(line.contains(check))
            return true;
        return false;
    }

}
