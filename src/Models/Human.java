package Models;

import java.util.Scanner;

public class Human extends Player {
    private String _name;

    public Human(String _name, Integer _id) {
        super(_id);
        this._name = _name;
    }

    @Override
    public Integer getAction(int[][] tokens) {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Colonne visée:");
            String str = sc.nextLine();
            try{
                int col = Integer.parseInt(str);
                if (col<=6 && col>=0){
                    return col;
                }else{
                    System.out.println("Entier entre 0 et 6 uniquement");
                }
            }catch(Exception e){
                System.out.println("mauvais format");
            }
        }
    }
}
