package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EmpruntView extends JFrame {
    private JComboBox<String> comboUtilisateurs;
    private JComboBox<String> comboLivres;
    private JTextField txtDateRetourPrevue;
    private JButton btnEnregistrerEmprunt;
    private JTable tableEmprunts;

    public EmpruntView() {
        // Configuration de la fenêtre
        setTitle("Gestion des Emprunts");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Haut : formulaire d'emprunt
        JPanel panelForm = new JPanel(new GridLayout(4, 2));
        panelForm.add(new JLabel("Utilisateur :"));
        comboUtilisateurs = new JComboBox<>();
        panelForm.add(comboUtilisateurs);

        panelForm.add(new JLabel("Livre :"));
        comboLivres = new JComboBox<>();
        panelForm.add(comboLivres);

        panelForm.add(new JLabel("Date retour prévue :"));
        txtDateRetourPrevue = new JTextField();
        panelForm.add(txtDateRetourPrevue);

        btnEnregistrerEmprunt = new JButton("Enregistrer Emprunt");
        panelForm.add(btnEnregistrerEmprunt);

        add(panelForm, BorderLayout.NORTH);

        // Centre : tableau des emprunts
        String[] colonnes = {"Utilisateur", "Livre", "Date Emprunt", "Date Retour Prévue", "Date Retour Effective"};
        Object[][] donnees = {}; // À remplir dynamiquement
        tableEmprunts = new JTable(donnees, colonnes);
        add(new JScrollPane(tableEmprunts), BorderLayout.CENTER);

        setVisible(true);
    }

    // Méthodes pour ajouter des écouteurs aux boutons
    public void addEnregistrerEmpruntListener(ActionListener listener) {
        btnEnregistrerEmprunt.addActionListener(listener);
    }

    public JComboBox<String> getComboUtilisateurs() {
        return comboUtilisateurs;
    }

    public JComboBox<String> getComboLivres() {
        return comboLivres;
    }

    public JTextField getTxtDateRetourPrevue() {
        return txtDateRetourPrevue;
    }

    public JTable getTableEmprunts() {
        return tableEmprunts;
    }

    public void setTableData(Object[][] donnees) {
        String[] colonnes = {"Utilisateur", "Livre", "Date Emprunt", "Date Retour Prévue", "Date Retour Effective"};
        tableEmprunts.setModel(new javax.swing.table.DefaultTableModel(donnees, colonnes));
    }
}
