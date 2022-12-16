package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class DeserGrilles3D {
    public Grille3D main() {
        Grille3D g;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            Scanner sc=new Scanner(System.in);
            System.out.println("nom du fichier ?");
            String nomF = sc.nextLine();
            final FileInputStream fichierIn = new FileInputStream(nomF);
            ois = new ObjectInputStream(fichierIn);
            g = (Grille3D) ois.readObject();
            System.out.println("Grilles : ");
            System.out.println(g.toString());
            System.out.println("score : " + g.score());
            return g;
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    return new Grille3D();
    }
}
