/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import beans.User;
import connexion.Connexion;
import dao.IUserDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utiles.SecurityUtil;

public class UserService implements IUserDao {

    private Connexion connexion;

    public UserService() {
        connexion = Connexion.getInstance();
    }

    @Override
    public boolean addUser(User user) {
        String req = "INSERT INTO user (login, password) VALUES (?, SHA1(?))";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public User findUserByLogin(String login) {
        String req = "SELECT * FROM user WHERE login = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("login"), rs.getString("password"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean authenticate(String login, String password) {
        String req = "SELECT * FROM user WHERE login = ? AND password = SHA1(?)";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    public boolean userExists(String login) {
        String req = "SELECT * FROM user WHERE login = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            return rs.next(); 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }  
     public boolean updatePassword(String login, String newPassword) {
        String req = "UPDATE user SET password = SHA1(?) WHERE login = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, newPassword);
            ps.setString(2, login);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
     public boolean verifySecurityQuestion(String login, String reponse) {
        String req = "SELECT reponse FROM user WHERE login = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String hashedReponseStockee = rs.getString("reponse");
                String hashedReponseUtilisateur = SecurityUtil.hashSHA1(reponse);

                return hashedReponseStockee.equals(hashedReponseUtilisateur);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public String getSecurityQuestion(String login) {
        String req = "SELECT question FROM user WHERE login = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("question");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    public boolean changerMotDePasse(String login, String nouveauMotDePasse) {
        String req = "UPDATE user SET password = SHA1(?) WHERE login = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, nouveauMotDePasse);
            ps.setString(2, login);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println("Erreur lors du changement de mot de passe : " + ex.getMessage());
        }
        return false;
    }

    public boolean checkUserExists(String login) {
        String req = "SELECT * FROM user WHERE login = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la vérification de l'utilisateur : " + ex.getMessage());
        }
        return false;
    }
    
    public boolean updatePassword(String newPassword) {
    // Exemple fictif - adapte selon ta base de données
    try {
        // Requête SQL fictive
        String query = "UPDATE users SET password = ? WHERE username = 'user1'";
        System.out.println("Mot de passe mis à jour : " + newPassword);
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

        
        

}

