package Models;

import java.io.PrintWriter;
import java.util.ArrayList;

public class OpeningBook {
    private class Entry {
        public Entry(long key, short val)
        {
            this.key = key;
            this.val = val;
        }
        long key;
        short val;
    }

    private ArrayList<ArrayList> _openingBook;

    public OpeningBook() {
        _openingBook = new ArrayList<>();
        for(int i = 0; i < 49; i++) {
            _openingBook.add(new ArrayList<Entry>());
        }
    }

    public void add(long key, short val, short nbmove) {
        _openingBook.get(nbmove).add(new Entry(key, val));
    }

    public void save(String filePath) {
        try {
            PrintWriter writer = new PrintWriter(filePath, "UTF-8");
            int count = 0;
            for(ArrayList<Entry> move : _openingBook) {
                for(Entry entrie : move) {
                    writer.print(count);
                    writer.print(" " + entrie.key);
                    writer.println(" " + entrie.val);
                }
                count ++;
            }
            writer.close();
        }
        catch(Exception e) {
            System.out.println("Erreur d'écriture dans :" + filePath);
        }
    }
}
