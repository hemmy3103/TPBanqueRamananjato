/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.ramananjato.tpbanqueramananjato.ejb;

/**
 *
 * @author h.ramananjato
 */
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import mg.itu.ramananjato.tpbanqueramananjato.entities.CompteBancaire;
import mg.itu.ramananjato.tpbanqueramananjato.jsf.util.Util;

@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "hemmy", // nom et
        password = "123456", // mot de passe que vous avez donnés lors de la création de la base de données
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true"
        }
)
@Stateless
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    public void persist(CompteBancaire compteBancaire) {
        em.persist(compteBancaire);
    }

    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }

    public List<CompteBancaire> getAllComptes() {
        String requete = "select c from CompteBancaire c";
        Query query = em.createQuery(requete);
        return query.getResultList();
    }

    public CompteBancaire getCompte(int id) {
        try {
            String requete = "SELECT c FROM CompteBancaire c WHERE c.id = :id";
            Query query = em.createQuery(requete);
            query.setParameter("id", id);
            return (CompteBancaire) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public int nbComptes() {
        String s = "select count(c) from CompteBancaire c";
        Query query = em.createQuery(s);
        int accountLenght = ((Long) query.getSingleResult()).intValue();
        return accountLenght;
    }

    public CompteBancaire update(CompteBancaire compteBancaire) {
        return em.merge(compteBancaire);
    }

    public String transferer(int idSource, int idDestination, int montant) {
        boolean erreur = false;
        CompteBancaire source = getCompte(idSource);
        CompteBancaire destinataire = getCompte(idDestination);
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
            return "";
        } else {
            transferer(source, destinataire, montant);
            Util.addFlashInfoMessage("Transfert correctement effectué de "+source.getNom()+" à "+destinataire.getNom()+" avec comme montant : "+montant);
            return "listeComptes?source="+idSource+"&destinataire="+idDestination+"montant="+montant+"&faces-redirect=true";
        }
    }

    public void transferer(CompteBancaire source, CompteBancaire destination,
            int montant) {
        source.retirer(montant);
        destination.deposer(montant);
        update(source);
        update(destination);
    }
}
