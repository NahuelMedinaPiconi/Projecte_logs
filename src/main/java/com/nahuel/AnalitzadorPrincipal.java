package com.nahuel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AnalitzadorPrincipal {
    public static void main(String[] args) {

        //Creació de la comanda del Fill i proves
        ProcessBuilder pb = new ProcessBuilder("java", "projectelogs/src/main/java/com/nahuel/FiltreLog.java");

        //Enviam els errors del proces fill a l'arxiu errors_filtre.log
        pb.redirectError(ProcessBuilder.Redirect.to(new File("projectelogs/src/main/java/com/nahuel/errors_filtre.log")));

        String[] llistaTexts = {
            "amncnjodn ERRORjjdso",
            "ERRORERRORmvjn",
            "knvjnin",
            "jdosjoERROR"
        };

        try  {
            //Iniciacio del proces fill
            Process process = pb.start();
            BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            
            //Escritura al fill
            for (String linia: llistaTexts) {
                escritor.write(linia);
                escritor.newLine();
            }

            escritor.flush();
            escritor.close();

            BufferedReader lector = new BufferedReader(new InputStreamReader(process.getInputStream()));

            //Lectura del fill
            String resposta;
            if ((resposta = lector.readLine()) != null) {
                IO.println("Fill > Pare: " + resposta);
            }

            //Esperam a que el fill termini per indicar com ha acabat el process fill.
            process.waitFor();
            IO.println("Pare: El procés a acabat amb codi: " + process.exitValue());
            
        } catch (IOException | InterruptedException e) {
            IO.println(e.getMessage());
        }
    }
}