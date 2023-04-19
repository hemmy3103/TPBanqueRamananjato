/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.ramananjato.tpbanqueramananjato.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import mg.itu.ramananjato.tpbanqueramananjato.ejb.GestionnaireCompte;

/**
 *
 * @author h.ramananjato
 */
@Named(value = "transfertMoney")
@RequestScoped
public class TransfertMoney implements Serializable {

    private int idSource;
    private int idDestinataire;
    private int montant;

    @EJB
    private GestionnaireCompte manageAccount;

    /**
     * Get the value of montant
     *
     * @return the value of montant
     */
    public int getMontant() {
        return montant;
    }

    /**
     * Set the value of montant
     *
     * @param montant new value of montant
     */
    public void setMontant(int montant) {
        this.montant = montant;
    }

    /**
     * Get the value of idDestinataire
     *
     * @return the value of idDestinataire
     */
    public int getIdDestinataire() {
        return idDestinataire;
    }

    /**
     * Set the value of idDestinataire
     *
     * @param idDestinataire new value of idDestinataire
     */
    public void setIdDestinataire(int idDestinataire) {
        this.idDestinataire = idDestinataire;
    }

    /**
     * Get the value of idSource
     *
     * @return the value of idSource
     */
    public int getIdSource() {
        return idSource;
    }

    /**
     * Set the value of idSource
     *
     * @param idSource new value of idSource
     */
    public void setIdSource(int idSource) {
        this.idSource = idSource;
    }

    /**
     * Creates a new instance of ListeComptes
     */
    public TransfertMoney() {
    }
    
    public String validerTransfert() {
        return manageAccount.transferer(idSource, idDestinataire, montant);
    }    
}
