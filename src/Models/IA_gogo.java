package Models;

public class IA_gogo extends Player {
    public IA_gogo(Integer _id) {
        super(_id);
    }

    @Override
    public int getAction(Grid grid) {
        return 0;
    }
}
