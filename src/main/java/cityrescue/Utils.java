package cityrescue;

public class Utils {
    /** 
     * @param arr
     * @param x
     * @return boolean
     */
    public static boolean linearSearch(int[] arr, int x) {
        for (int elem : arr) {
            if (elem == x){
                return true;
            }
        }
        return false;
    }

    /** 
     * @param coord1
     * @param coord2
     * @return int
     */
    public static int taxiCab(int[] coord1,int[] coord2){
        return (Math.abs(coord2[0] - coord1[0]) + Math.abs(coord2[1] - coord1[1]));
    }
}
