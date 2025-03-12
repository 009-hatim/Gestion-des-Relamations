/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author hp
 */
public class TraitementReclamation {

    private EStatut statut;
    private String commentaire;
    private Reclamation reclamation;
    private Etudiant etudiant;

    public TraitementReclamation(EStatut statut, String commentaire, Reclamation reclamation, Etudiant etudiant) {
        this.statut = statut;
        this.commentaire = commentaire;
        this.reclamation = reclamation;
        this.etudiant = etudiant;
    }

    public EStatut getStatut() {
        return statut;
    }

    public void setStatut(EStatut statut) {
        this.statut = statut;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Reclamation getReclamation() {
        return reclamation;
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

}
