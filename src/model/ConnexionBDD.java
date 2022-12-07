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
    
    //Constructeurs
    public ConnexionBDD(String host, String port, String dbname, String password, String username){
        this.host=host;
        this.port=port;
        this.dbname=dbname;
        this.password=password;
        this.username=username;
    }
    
    public ConnexionBDD(){
        this.host = "mysql-groupe5.alwaysdata.net";
        this.port = "3306";
        this.dbname = "groupe5_20483d";
        this.username = "groupe5_520483";
        this.password = "g520483D";
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
    private ArrayList<String> getClassement(){
        ArrayList<String> classement=null;
        String query = "SELECT Pseudo FROM Joueur WHERE Nombre_deplacements != 0 ORDER BY Nombre_deplacements ASC;";
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
            System.out.println("Problème avec la requete");
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
            System.out.println("Problème avec la requete d'insertion");
            System.out.println("Joueur deja existant");
        }finally{
            this.closeConnexion();
        }
    }
    
    //Inscription d'un nouveau joueur avec seulement son pseudo (avant qu'il ne commence à jouer) 
    //Le nombre de déplacements est à 0 par défaut, mais le nouveau joueur ne sera pas pris en compte dans le classement
    public void nouveauJoueur(String pseudo){
        String query = "INSERT INTO Joueur VALUES ('"+pseudo+"' ,0,0);";
        try{
            this.openConnexion();
            Statement stmt = this.con.createStatement();
            int n = stmt.executeUpdate(query);
            System.out.println("joueur inséré");
            stmt.close();
        }catch(SQLException e){
            System.out.println("Problème avec la requete d'insertion");
            System.out.println("Joueur deja existant");
        }finally{
            this.closeConnexion();
        }
    }
    
    //Affiche le classement
    public void affichageClassement(){
        ArrayList classement = this.getClassement();
        System.out.println("Classement des joueurs : ");
        for(int i=0; i<classement.size();i++){
            int j=i+1;
            System.out.println(j+". "+classement.get(i));
        }
    }
    
    //Permet d'actualiser le nombre de déplacements une fois que le joueur a gagné
    public void updateNbDeplacements(String pseudo, int nb){
        String query = "UPDATE Joueur SET Nombre_deplacements="+nb+" WHERE Pseudo='"+pseudo+"';";
        try{
            this.openConnexion();
            Statement stmt = this.con.createStatement();
            int n = stmt.executeUpdate(query);
            System.out.println("valeur modifiée");
            stmt.close();
        }catch(SQLException e){
            System.out.println("Problème avec la requete");
        }finally{
            this.closeConnexion();
        }
    }
    
    public void updateScore(String pseudo, int nb){
        String query = "UPDATE Joueur SET score="+nb+" WHERE Pseudo='"+pseudo+"';";
        try{
            this.openConnexion();
            Statement stmt = this.con.createStatement();
            int n = stmt.executeUpdate(query);
            System.out.println("valeur modifiée");
            stmt.close();
        }catch(SQLException e){
            System.out.println("Problème avec la requete");
        }finally{
            this.closeConnexion();
        }
    }
    
    //Retourne le meilleur score
    public String meilleurScore(){
        String query = "SELECT MAX(score) FROM Joueur WHERE Nombre_deplacements > 0;";
        String bestScore="";
        try{
            this.openConnexion();
            Statement stmt = this.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                bestScore=rs.getString(1);
            }
            stmt.close();
        }catch (SQLException e) {
            System.out.println("Problème avec la requete");
        } finally {
            this.closeConnexion();
        }
        return bestScore;
    }
    
    //renvoie vrai si le pseudo est libre, faux sinon
    public boolean availableUsername(String s){
        boolean a=false;
        int i=1;
        String query = "SELECT COUNT(Pseudo) FROM Joueur WHERE Pseudo=\""+s+"\";";
        try{
            this.openConnexion();
            Statement stmt = this.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                i=Integer.parseInt(rs.getString(1));
            }
            if(i==0){
                a = true;
            }else{
                a = false;
            }
            stmt.close();
        }catch (SQLException e) {
            System.out.println("Problème avec la requete");
        } finally {
            this.closeConnexion();
        }
        return(a);
    }
}
