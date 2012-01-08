import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;
/**
 * Fenêtre principale de l'application Bibliothèque avec le menu
 * @author IUT,  A.Culet
 * @version 1.0
 */
public class VueMenuBiblio  extends Vue{
    
	private static final long serialVersionUID = 1L;

    public VueMenuBiblio(Controleur controleur) {
        super (controleur);
        this.initialize();
    }
    
    private void initialize() {
    	getFrame().setTitle("Gestion de bibliothèque");
        getFrame().setBounds(100, 100, 550, 250);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 550, 20);
        getFrame().getContentPane().add(menuBar);
       
        JMenu mnApplication = new JMenu("Application");
        mnApplication.setHorizontalAlignment(SwingConstants.LEFT);
        menuBar.add(mnApplication);
                
        JMenuItem menuItemQuitter = new JMenuItem("Quitter");
        menuItemQuitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getControleur().quitter(VueMenuBiblio.this);
            }
        });
        mnApplication.add(menuItemQuitter);
       
        JMenu mnOuvrage = new JMenu("Ouvrage");
        menuBar.add(mnOuvrage);
       
        JMenuItem MenuItemOuv = new JMenuItem("Nouvel ouvrage");
       
        MenuItemOuv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //  affichage de la fenetre de saisie d'un ouvrage
            	getControleur().fermerVue(VueMenuBiblio.this);
                getControleur().saisirOuvrage() ;
            }
        });
        mnOuvrage.add(MenuItemOuv);

        JMenuItem MenuItemExemp = new JMenuItem("Nouvel exemplaire");
        MenuItemExemp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //  affichage de la fenetre de saisie d'un exemplaire
            	getControleur().fermerVue(VueMenuBiblio.this);
                getControleur().saisirExemplaire() ;}
        });
        mnOuvrage.add(MenuItemExemp);
       
        JMenuItem menuItemConsult = new JMenuItem("Consulter ouvrage");
        menuItemConsult.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // affichage de la fenetre de consultation d'un ouvrage
            	getControleur().fermerVue(VueMenuBiblio.this);
                getControleur(). consulterOuvrage() ;}
        });
        mnOuvrage.add(menuItemConsult);
       
       
        JMenu mnPeriodiques = new JMenu("Periodiques");
        menuBar.add(mnPeriodiques);
        
        JMenu mnParutions = new JMenu("Parutions");
        menuBar.add(mnParutions);

        JMenu mnRecherches = new JMenu("Recherches");
        menuBar.add(mnRecherches);
       
       
        JMenuItem menuItemConsultPerio = new JMenuItem("Consulter Periodique");
        menuItemConsultPerio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // affichage de la fenetre de consultation d'un periodique
            	getControleur().fermerVue(VueMenuBiblio.this);
                getControleur(). consulterPeriodique() ;}
        });
        mnPeriodiques.add(menuItemConsultPerio);


        JMenuItem menuItemNewPerio = new JMenuItem("Nouveau Periodique");
        menuItemNewPerio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // affichage de la fenetre de saisie d'un periodique
            	getControleur().fermerVue(VueMenuBiblio.this);
                getControleur(). saisiePeriodique() ;}
        });
        mnPeriodiques.add(menuItemNewPerio);
        
        
        JMenuItem menuItemNewArticle = new JMenuItem("Nouvel Article");
        menuItemNewArticle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // affichage de la fenetre de saisie d'un periodique
            	getControleur().fermerVue(VueMenuBiblio.this);
                getControleur(). saisieArticle() ;}
        });
        mnParutions.add(menuItemNewArticle);
        
        
        JMenuItem menuItemNewParu = new JMenuItem("Nouvelle Parution");
        menuItemNewParu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // affichage de la fenetre de saisie d'un periodique
            	getControleur().fermerVue(VueMenuBiblio.this);
                getControleur(). saisirParution() ;}
        });
        mnParutions.add(menuItemNewParu);        
        
        
        JMenuItem menuItemRechAut = new JMenuItem("Recherche par Auteur");
        menuItemRechAut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // affichage de la fenetre de saisie d'un periodique
            	getControleur().fermerVue(VueMenuBiblio.this);
                getControleur(). rechAuteur() ;}
        });
        mnRecherches.add(menuItemRechAut);
        
        
        JMenuItem menuItemRechMot = new JMenuItem("Recherche par Mots Clés");
        menuItemRechMot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // affichage de la fenetre de saisie d'un periodique
            	getControleur().fermerVue(VueMenuBiblio.this);
                getControleur(). rechMotCle() ;}
        });
        mnRecherches.add(menuItemRechMot);   
        
        getFrame().setVisible(true);
    }
}