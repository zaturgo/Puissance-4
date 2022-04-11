package Models;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Views.GameWindow;

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
    public short get(long key, short nbmove) {
        for(ArrayList<Entry> move : _openingBook) {
            for(Entry entrie : move) {
                if(entrie.key == key)
                    return entrie.val;
            }
        }
        return 9999;
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
    public void load(String filePath) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String datas[] = scanner.nextLine().split(" ");
                short nbMoves = Short.parseShort(datas[0]);
                long key = Long.parseLong(datas[1]);
                short score = Short.parseShort(datas[2]);
                System.out.println(datas[2]);
                System.out.println(datas[1]);
                System.out.println(datas[0]);
                this.add(key, score, nbMoves);
            }
            scanner.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
