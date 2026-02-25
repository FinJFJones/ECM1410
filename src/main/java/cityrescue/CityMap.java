package cityrescue;

// Grid size
// Blocked + Grid
// Legal Move

public class CityMap {
    int[] gridSize;
    int w;
    int h;
    boolean[][] blocked;

    public CityMap(int[] gridSize) {
        this.gridSize = gridSize;
        this.w = this.gridSize[0];
        this.h = this.gridSize[1];
        this.blocked = new boolean[this.w][this.h];
    }

    public boolean isLegalMove() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
