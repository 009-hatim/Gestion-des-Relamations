/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;

/**
 *
 * @author hp
 */
public class Reclamation {
    private int id;
    private String objet;
    private String description;
    private Date date;

    public Reclamation(int id, String objet, String description, Date date) {
        this.id = id;
        this.objet = objet;
        this.description = description;
        this.date = date;
    }

    public Reclamation(String objet, String description, Date date) {
        this.objet = objet;
        this.description = description;
        this.date = date;
    }

        

    public int getId() {
        return id;
    }

    public String getObjet() {
        return objet;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   

    
}
