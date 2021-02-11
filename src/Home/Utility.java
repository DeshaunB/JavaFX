package Home;

public class Utility {
    public static String[] AddToArray(String[] array, String arrayItem){
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            if (array[i] == null) {
                array[i] = arrayItem;
                break;
            }
        }
        return array;
    }
}
