/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import beans.EStatut;
import beans.Etudiant;
import beans.Reclamation;
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
public class TraitementReclamationService implements IDao<TraitementReclamation> {

    private Connexion connexion;
    private ReclamationService rcs;
    private EtudiantService es;

    public TraitementReclamationService() {
        connexion = Connexion.getInstance();
        rcs = new ReclamationService();
        es = new EtudiantService();
    }

    @Override
    public boolean create(TraitementReclamation o) {
        String req = "insert into TraitementReclamation (statut, commentaire, reclamation_id , etudiant_id) values (?, ?, ?,?)";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, o.getStatut().toString());
            ps.setString(2, o.getCommentaire());
            ps.setInt(3, o.getReclamation().getId());
            ps.setInt(4, o.getEtudiant().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(TraitementReclamation o) {
        String req = "delete from TraitementReclamation where reclamation_id = ? AND etudiant_id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, o.getReclamation().getId());
            ps.setInt(2, o.getEtudiant().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(TraitementReclamation o) {
        String req = "update TraitementReclamation SET statut = ?, commentaire = ?  where reclamation_id = ? AND etudiant_id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, o.getStatut().toString());
            ps.setString(2, o.getCommentaire());
            ps.setInt(3, o.getReclamation().getId());
            ps.setInt(4, o.getEtudiant().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public TraitementReclamation findById(int id) {

        return null;

    }

    @Override
    public List<TraitementReclamation> findAll() {
        List<TraitementReclamation> traitements = new ArrayList<>();
        String req = "select * from TraitementReclamation";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reclamation reclamation = rcs.findById(rs.getInt("reclamation_id"));
                Etudiant etudiant = es.findById(rs.getInt("etudiant_id"));
                traitements.add(new TraitementReclamation(EStatut.valueOf(rs.getString("statut").toUpperCase()), rs.getString("commentaire"), reclamation, etudiant));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return traitements;
    }

    public List<TraitementReclamation> findByEtudiant(Etudiant etudiant) {
        List<TraitementReclamation> resultList = new ArrayList<>();
        String req = "SELECT * FROM TraitementReclamation WHERE etudiant_id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, etudiant.getId());
            ResultSet rs = ps.executeQuery();

            ReclamationService reclamationService = new ReclamationService();

            while (rs.next()) {
                int reclamationId = rs.getInt("reclamation_id");

                Reclamation reclamation = reclamationService.findById(reclamationId);

                EStatut statut = EStatut.valueOf(rs.getString("statut").toUpperCase());

                TraitementReclamation traitement = new TraitementReclamation(statut, rs.getString("commentaire"), reclamation, etudiant);

                resultList.add(traitement);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return resultList;
    }

    public int countByStatut(EStatut statut) {
        int count = 0;
        String req = "SELECT COUNT(*) FROM TraitementReclamation WHERE statut = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, statut.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return count;
    }

}
