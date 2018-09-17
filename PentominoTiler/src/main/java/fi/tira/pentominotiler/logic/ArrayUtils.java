package fi.tira.pentominotiler.logic;

/**
 *
 * @author juha
 */
public class ArrayUtils {
    
    public static <T> T[] copyArray(T[] array){
    Object[] newArray = new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
    return (T[]) newArray;
}
    
}
