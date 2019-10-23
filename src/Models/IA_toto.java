package Models;

public class IA_toto extends Player {
    public IA_toto(Integer _id) {
        super(_id);
    }

    @Override
    public Integer getAction(int[][] tokens) {
        return 0;
    }
}
