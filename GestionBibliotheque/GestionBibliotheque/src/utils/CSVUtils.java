package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
    public static List<String[]> lireCSV(String cheminFichier) {
        List<String[]> lignes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                lignes.add(ligne.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lignes;
    }

    public static void ajouterLigneCSV(String cheminFichier, String[] nouvelleLigne) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(cheminFichier, true))) {
            bw.write(String.join(",", nouvelleLigne));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ecrireCSV(String cheminFichier, List<String[]> lignes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(cheminFichier))) {
            for (String[] ligne : lignes) {
                bw.write(String.join(",", ligne));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
