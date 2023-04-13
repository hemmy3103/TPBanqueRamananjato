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
import mg.itu.ramananjato.tpbanqueramananjato.entities.CompteBancaire;
import mg.itu.ramananjato.tpbanqueramananjato.jsf.util.Util;

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
    
    public void validerTransfert() {
        transferer(idSource, idDestinataire, montant);
    }

    public String transferer(int idSource, int idDestination, int montant) {
        boolean erreur = false;
        CompteBancaire source = manageAccount.getCompte(idSource);
        CompteBancaire destinataire = manageAccount.getCompte(idDestination);
        if (source == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
            erreur = true;
        }
        if (source == null || destinataire == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:destinataire");
            erreur = true;
        } else {
            if (source.getSolde() < montant) {
                Util.messageErreur("Le solde du compte avec l'id " + idSource + " est insuffisant pour ce transfert", "Solde insuffisant !", "form:montant");
                erreur = true;
            }
        }
        if (erreur) {
            return null;
        } else {
            transferer(source, destinataire, montant);
            Util.messageInfo("Transfert correctement effectué");
            return "transfert";
        }
    }


    public void transferer(CompteBancaire source, CompteBancaire destination,
            int montant) {
        source.retirer(montant);
        destination.deposer(montant);
        manageAccount.update(source);
        manageAccount.update(destination);
    }
}
