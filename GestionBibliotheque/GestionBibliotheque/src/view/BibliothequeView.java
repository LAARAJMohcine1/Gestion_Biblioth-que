package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BibliothequeView extends JFrame {
    private JButton btnGestionLivres;
    private JButton btnGestionUtilisateurs;
    private JButton btnGestionEmprunts;

    public BibliothequeView() {
        // Configuration de la fenêtre principale
        setTitle("Application de Gestion de Bibliothèque");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1)); // Disposition des boutons

        // Boutons pour accéder aux différentes fonctionnalités
        btnGestionLivres = new JButton("Gestion des Livres");
        btnGestionUtilisateurs = new JButton("Gestion des Utilisateurs");
        btnGestionEmprunts = new JButton("Gestion des Emprunts");

        // Ajout des boutons à la fenêtre
        add(btnGestionLivres);
        add(btnGestionUtilisateurs);
        add(btnGestionEmprunts);

        setVisible(true);
    }

    // Méthodes pour ajouter des actions aux boutons
    public void addGestionLivresListener(ActionListener listener) {
        btnGestionLivres.addActionListener(listener);
    }

    public void addGestionUtilisateursListener(ActionListener listener) {
        btnGestionUtilisateurs.addActionListener(listener);
    }

    public void addGestionEmpruntsListener(ActionListener listener) {
        btnGestionEmprunts.addActionListener(listener);
    }
}
