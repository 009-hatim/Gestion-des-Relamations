/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import beans.EStatut;
import beans.Etudiant;
import beans.Reclamation;
import beans.TraitementReclamation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import services.EtudiantService;
import services.ReclamationService;
import services.TraitementReclamationService;

/**
 *
 * @author hp
 */
public class Test {

    public static void main(String[] args) throws ParseException {
        EtudiantService es = new EtudiantService();

        es.create(new Etudiant("Koubri", "Hatim", "H.koubri@gmail.com"));
        es.create(new Etudiant("Ahlam", "Laalami", "A.ALAMI@gmail.com"));

        es.delete(es.findById(2));

        Etudiant e = es.findById(1);
        e.setNom("Amal");
        e.setPrenom("Raji");
        e.setEmail("amal@gmail.com");
        es.update(e);

        System.out.println("Etudiant : ");
        for (Etudiant et : es.findAll()) {
            System.out.println("   " + et.getNom());
        }

        ReclamationService rs = new ReclamationService();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        rs.create(new Reclamation("Obj", "description of the obj", sdf.parse("10-11-2025")));
        rs.create(new Reclamation("Problème d'inscription", "Mon inscription n'a pas été validée", sdf.parse("03-01-2025")));

        rs.delete(rs.findById(3));

        Reclamation r = rs.findById(5);
        r.setObjet("objet3");
        r.setDescription("description");
        r.setDate(sdf.parse("25-02-2025"));

        rs.update(r);

        System.out.println("");
        System.out.println("Reclamation : ");
        for (Reclamation rcl : rs.findAll()) {
            System.out.println("   " + rcl.getObjet());
        }

        TraitementReclamationService ts = new TraitementReclamationService();

        ts.create(new TraitementReclamation(EStatut.TRAITEE, "####", rs.findById(2), es.findById(1)));
        ts.create(new TraitementReclamation(EStatut.NON_TRAITEE, "////", rs.findById(5), es.findById(3)));

        TraitementReclamation tr = ts.findAll().get(6);
        ts.delete(tr);

    }

}