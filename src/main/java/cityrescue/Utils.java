package cityrescue;

public class Utils {
    public boolean linearSearch(int[] arr, int x) {
        for (int elem : arr) {
            if (elem == x){
                return true;
            }
        }
        return false;
    }
}
