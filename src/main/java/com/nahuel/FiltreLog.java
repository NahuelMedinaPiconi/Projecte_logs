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
