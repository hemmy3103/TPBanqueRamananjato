/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.ramananjato.tpbanqueramananjato.jsf;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import mg.itu.ramananjato.tpbanqueramananjato.ejb.GestionnaireCompte;
import mg.itu.ramananjato.tpbanqueramananjato.entities.CompteBancaire;
import mg.itu.ramananjato.tpbanqueramananjato.jsf.util.Util;

/**
 *
 * @author h.ramananjato
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    private List<CompteBancaire> accountList;

    @EJB
    private GestionnaireCompte manageAccount;

    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }

    public List<CompteBancaire> getAllComptes() {
        if (accountList == null) {
            accountList = manageAccount.getAllComptes();
        }
        return accountList;
    }

    public String supprimerCompte(CompteBancaire compteBancaire) {
        manageAccount.supprimerCompte(compteBancaire);
        Util.addFlashInfoMessage("Compte de " + compteBancaire.getNom() + " supprimé");
        return "listeComptes?faces-redirect=true";
    }
}
