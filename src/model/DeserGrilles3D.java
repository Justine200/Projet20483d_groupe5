package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DeserGrilles3D {
    public Grille3D main() {
        Grille3D g;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            final FileInputStream fichierIn = new FileInputStream("grilles3D.ser");
            ois = new ObjectInputStream(fichierIn);
            g = (Grille3D) ois.readObject();
            System.out.println("Grilles : "+g.toString());
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
