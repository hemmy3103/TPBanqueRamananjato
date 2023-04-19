/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.ramananjato.tpbanqueramananjato.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import mg.itu.ramananjato.tpbanqueramananjato.ejb.GestionnaireCompte;

/**
 *
 * @author h.ramananjato
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte implements Serializable {

    private String nom;
    @PositiveOrZero
    private int solde;

    @EJB
    private GestionnaireCompte manageAccount;
    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
    
    public String action() {
        return manageAccount.addAccount(nom, solde);
    }
}
