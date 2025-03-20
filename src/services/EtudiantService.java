/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import beans.EStatut;
import beans.Etudiant;
import beans.TraitementReclamation;
import dao.IDao;
import java.sql.Date;
import connexion.Connexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class EtudiantService implements IDao<Etudiant> {

    private Connexion connexion;

    public EtudiantService() {
        connexion = Connexion.getInstance();
    }

    @Override
    public boolean create(Etudiant o) {
        String req = "insert into Etudiant (nom, prenom, email) values (?, ?, ?)";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setString(3, o.getEmail());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Etudiant o) {
        String req = "delete from Etudiant where id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Etudiant o) {
        String req = "update etudiant set nom = ?, prenom = ?, email = ? where id  = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setString(3, o.getEmail());
            ps.setInt(4, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public Etudiant findById(int id) {
        String req = "select * from Etudiant where id  = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Etudiant(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Etudiant> findAll() {
        List<Etudiant> etudiants = new ArrayList<>();
        String req = "select * from Etudiant";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                etudiants.add(new Etudiant(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return etudiants;
    }

    public List<Etudiant> findByStatut(EStatut statut) {
        List<Etudiant> etudiants = new ArrayList<>();
        String req = "SELECT e.id, e.nom, e.prenom, e.email "
                + "FROM Etudiant e "
                + "JOIN TraitementReclamation t ON e.id = t.etudiant_id "
                + "WHERE t.statut = ?";

        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, statut.name());  // Use statut.name() to get the string representation of the enum
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Etudiant etu = new Etudiant(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"));
                etudiants.add(etu);  // Add each Etudiant to the list
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return etudiants;  // Return the list of Etudiants
    }

    public List<Etudiant> findByNameOrEmail(String nomeOrEmail) {
        List<Etudiant> etudiants = new ArrayList<>();
        String req = "SELECT * FROM Etudiant WHERE nom LIKE ? OR email LIKE ? OR prenom LIKE ? "; 

        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, "%" + nomeOrEmail + "%");
            ps.setString(2, "%" + nomeOrEmail + "%"); 
            ps.setString(3, "%" + nomeOrEmail + "%");
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Etudiant etu = new Etudiant(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"));
                etudiants.add(etu); 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return etudiants; 
    }

   

}
