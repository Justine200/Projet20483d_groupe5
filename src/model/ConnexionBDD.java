/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author justineherolt
 */
public class ConnexionBDD {
    private final String host, port, dbname, username, password;
    private Connection con = null;
    
    //Constructeur
    public ConnexionBDD(String host, String port, String dbname, String password, String username){
        this.host=host;
        this.port=port;
        this.dbname=dbname;
        this.password=password;
        this.username=username;
    }
    
    //Termine la connection avec la base de données 
    public void closeConnexion(){
        if(this.con!=null){
            try{
                this.con.close();
                System.out.println("Database connection terminated");
            }catch(Exception e){ }
        }
    }
    
    //Permet de se connecter à la base de données 
    private void openConnexion() {
        String connectUrl = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dbname;
        if (this.con != null) {
            this.closeConnexion();
        }
        try {
            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
            this.con = DriverManager.getConnection(connectUrl, this.username, this.password);
            System.out.println("Database connection established.");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Cannot load db driver: com.mysql.jdbc.Driver");
            cnfe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erreur inattendue");
            e.printStackTrace();
        }
    }
    
    //Permet de récupérer le classement des joueurs de l'application 
    public ArrayList<String> getClassement(){
        ArrayList<String> classement=null;
        String query = "SELECT Pseudo FROM Joueur ORDER BY Nombre_deplacements ASC;";
        try{
            this.openConnexion();
            Statement stmt = this.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData metadata = rs.getMetaData();
            String tuple;
            classement = new ArrayList<>();
            while (rs.next()) {
                tuple = "";
                for (int i = 1; i <= metadata.getColumnCount(); i++) {
                    tuple += rs.getString(i);
                    if (i<metadata.getColumnCount()) {
                        tuple +=";";
                    }
                }
                classement.add(tuple);
            }
            stmt.close();
        }catch (SQLException e) {
            System.out.println("Probleme avec la requete");
        } finally {
            this.closeConnexion();
        }
        return classement;
    }
    
    //Permet d'ajouter un joueur dans la base de donnée
    public void insertJoueur(String pseudo, int nbDeplacements){
        String query = "INSERT INTO Joueur VALUES ('"+pseudo+"' ,"+nbDeplacements+");";
        try{
            this.openConnexion();
            Statement stmt = this.con.createStatement();
            int n = stmt.executeUpdate(query);
            System.out.println("joueur inséré");
            stmt.close();
        }catch(SQLException e){
            System.out.println("Probleme avec la requete d'insertion");
            System.out.println("Joueur deja existant");
            System.out.println(e);
        }finally{
            this.closeConnexion();
        }
    }
}
