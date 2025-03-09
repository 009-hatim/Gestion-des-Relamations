/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import beans.EStatut;
import beans.Etudiant;
import beans.TraitementReclamation;
import connexion.Connexion;
import dao.IDao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class TraitementReclamationService implements IDao<TraitementReclamation>{
    
    private Connexion connexion;
    private ReclamationService rcs;

    public TraitementReclamationService() {
        connexion = Connexion.getInstance();
        rcs = new ReclamationService();
    }
    

    @Override
    public boolean create(TraitementReclamation o) {
         String req = "insert into TraitementReclamation (statut, commentaire, reclamation_id) values (?, ?, ?)"; 
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, o.getStatut().toString());
            ps.setString(2, o.getCommentaire());
            ps.setInt(3, o.getReclamation().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(TraitementReclamation o) {
         String req = "delete from TraitementReclamation where reclamation_id = ?"; 
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, o.getReclamation().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(TraitementReclamation o) {
        String req = "update TraitementReclamation set statut = ?, commentaire = ?, reclamation_id  = ? where reclamation_id = ?"; 
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, o.getStatut().toString());
            ps.setString(2, o.getCommentaire());
            ps.setInt(3, o.getReclamation().getId());
            ps.setInt(4, o.getReclamation().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public TraitementReclamation findById(int id) {
         String req = "select * from TraitementReclamation where reclamation_id = ?"; 
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return new TraitementReclamation(EStatut.valueOf(rs.getString("statut")), rs.getString("commentaire"),rcs.findById(rs.getInt("reclamation_id")));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
                
    }

    @Override
    public List<TraitementReclamation> findAll() {
        List<TraitementReclamation>  traitements = new ArrayList<>();
        String req = "select * from TraitementReclamation"; 
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                traitements.add(new TraitementReclamation(EStatut.valueOf(rs.getString("statut")), rs.getString("commentaire"),rcs.findById(rs.getInt("reclamation_id"))));
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }
        return traitements;
    }
    
}
