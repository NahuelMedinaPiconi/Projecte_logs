package com.nahuel;

import java.util.Scanner;

public class FiltreLog {
    public static void main(String[] args) {

        //Introducció de dades amb Scanner i creació de variables
        Scanner sc = new Scanner(System.in);
        int contadorErrors = 0;

        //Llegir les dades i trobar els registres ERROR
        while (sc.hasNextLine()) {

            String paraulesError = sc.nextLine() + " ";
            String paraula = "ERROR";

            //Comprovam que el text no estigui buit
            if (paraulesError == null || paraulesError.isBlank()) {
                System.err.println("Error: Text buit");
                System.exit(1);
            } 

            for (int i = 0; i < paraulesError.length() - paraula.length(); i++) {
                if (paraulesError.substring(i, i + paraula.length()).equals("ERROR")) {
                    contadorErrors++;
                }
            }
        }   
        System.out.println(contadorErrors);
        sc.close();
    }
}
