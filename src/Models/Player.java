public class Player {
    private Integer _id;
    public Integer getAction(){
        return 3;//Integer between 0 and 6
    }

    public Player(Integer _id) {
        this._id = _id;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }
}
