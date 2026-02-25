package cityrescue;
// Blocked + Grid
// Legal Move

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

    public boolean isLegalMove() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
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
