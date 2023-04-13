/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.ramananjato.tpbanqueramananjato.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import mg.itu.ramananjato.tpbanqueramananjato.entities.CompteBancaire;

/**
 *
 * @author h.ramananjato
 */
@Singleton
@Startup
public class Init {

    @EJB
    GestionnaireCompte gestionnaireCompte;

    @PostConstruct
    public void createDefaultAccount() {
        if (this.gestionnaireCompte.nbComptes() == 0) {
            CompteBancaire[] comptes = new CompteBancaire[4];
            comptes[0] = new CompteBancaire("John Lennon", 150000);
            comptes[1] = new CompteBancaire("Paul McCartney", 950000);
            comptes[2] = new CompteBancaire("Ringo Starr", 20000);
            comptes[3] = new CompteBancaire("Georges Harrisson", 100000);
            for (CompteBancaire compte : comptes) {
                this.gestionnaireCompte.creerCompte(compte);
            }
        }
    }

}
