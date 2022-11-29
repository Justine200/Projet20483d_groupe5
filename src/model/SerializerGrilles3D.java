package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializerGrilles3D {
    static Grille3D g;

    public SerializerGrilles3D(Grille3D grilleTest) {
        g=grilleTest;
    }

    public void main(){
        final Grille3D grille3D = g;
        ObjectOutputStream oos = null;
        try {
            final FileOutputStream fichier = new FileOutputStream("grilles3D.ser");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(grille3D);
            oos.flush();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}