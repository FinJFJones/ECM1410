package cityrescue;

public class Utils {
    public static boolean linearSearch(int[] arr, int x) {
        for (int elem : arr) {
            if (elem == x){
                return true;
            }
        }
        return false;
    }

    public static int taxiCab(int[] coord1,int[] coord2){
        return (Math.abs(coord2[0] - coord1[0]) + Math.abs(coord2[1] - coord1[0]));
    }
}
