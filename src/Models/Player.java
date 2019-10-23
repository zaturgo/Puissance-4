package Models;

import java.util.Scanner;

public class Player {
    private Integer _id;
    public Integer getAction(){
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
