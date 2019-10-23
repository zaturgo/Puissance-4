public class Game {
    private Grid _grid;
    public Game() {
        _grid = new Grid();
    }
    public void start() {
        _grid.placeToken(1, 3);
        _grid.debugGrid();
    }
}
