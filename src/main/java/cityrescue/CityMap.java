package cityrescue;

/**
 * Class used for storing the blocked grid and data about the city, such as size.
 */
public class CityMap {
    int[] gridSize;
    int w;
    int h;
    boolean[][] blocked;

    public CityMap(int width, int height) {
        this.w = width;
        this.h = height;
        this.gridSize = new int[] {this.w,this.h};
        this.blocked = new boolean[this.w][this.h];
    }
    /** 
     * Checks to see if a given coordinate is within the city bounds.
     * @param x
     * @param y
     * @return boolean
     */
    public boolean isInBounds(int x, int y){
        if (x < 0 || x > (this.w -1)){
            return false;
        }
        if (y < 0 || y > (this.h -1)){
            return false;
        }
        return true;
    }
}
