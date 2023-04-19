/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.ramananjato.tpbanqueramananjato.jsf;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import mg.itu.ramananjato.tpbanqueramananjato.ejb.GestionnaireCompte;
import mg.itu.ramananjato.tpbanqueramananjato.entities.CompteBancaire;
import mg.itu.ramananjato.tpbanqueramananjato.jsf.util.Util;

/**
 *
 * @author h.ramananjato
 */
@Named(value = "updateAccount")
@ViewScoped
public class UpdateAccount implements Serializable {

    @EJB
    private GestionnaireCompte manageAccount;

    private Long id;
    private CompteBancaire compte;    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void loadCompte() {
        compte = manageAccount.getCompte(id.intValue());
    }
    
    public String action() {
        manageAccount.update(compte);
        Util.addFlashInfoMessage("Modification enregistré sur compte de " + compte.getNom());
        return "listeComptes?faces-redirect=true";
    }
    
}
