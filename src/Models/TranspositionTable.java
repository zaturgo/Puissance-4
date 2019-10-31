package Models;

import java.util.ArrayList;

///
/// Hash table to store position regardless of collisions
///
public class TranspositionTable {
    private class Entry {
        public Entry(long key, short val)
        {
            this.key = key;
            this.val = val;
        }
        long key;
        short val;
    }
    Entry _tab[];
    ///
    /// Compute the index according to the key, it accelerates searching
    ///
    private int index(long key) {
        return (int)(key%_tab.length);
    }
    public TranspositionTable(int size) {
        _tab = new Entry[size];
    }
    public void reset() {
        //_tab.clear();
    }
    public void put(long key, short val) {
        int i = index(key);
        _tab[i] = new Entry(key, val);
    }
    public int get(long key) {
        int i = index(key);
        if(_tab[i] == null)
            return 0;
        if(_tab[i].key == key) {
            return _tab[i].val;
        }
        else
            return 0;
    }
    public void save() {

    }
}
