/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import beans.EStatut;
import beans.Etudiant;
import beans.Reclamation;
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
public class ReclamationService implements IDao<Reclamation> {

    private Connexion connexion;

    public ReclamationService() {
        connexion = Connexion.getInstance();
    }

    @Override
    public boolean create(Reclamation o) {
        String req = "insert into Reclamation (objet, description, date) values (?, ?, ?)";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, o.getObjet());
            ps.setString(2, o.getDescription());
            ps.setDate(3, new Date(o.getDate().getTime()));
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Reclamation o) {
        String req = "delete from Reclamation where id = ?";
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
    public boolean update(Reclamation o) {
        String req = "update Reclamation set objet = ?, description = ?, date = ? where id  = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, o.getObjet());
            ps.setString(2, o.getDescription());
            ps.setDate(3, new Date(o.getDate().getTime()));
            ps.setInt(4, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public Reclamation findById(int id) {
        String req = "select * from Reclamation where id  = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Reclamation(rs.getInt("id"), rs.getString("objet"), rs.getString("description"), rs.getDate("date"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Reclamation> findAll() {
        List<Reclamation> reclamations = new ArrayList<>();
        String req = "select * from Reclamation";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                reclamations.add(new Reclamation(rs.getInt("id"), rs.getString("objet"), rs.getString("description"), rs.getDate("date")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamations;
    }

}
