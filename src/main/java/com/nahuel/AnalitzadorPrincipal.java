package com.nahuel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AnalitzadorPrincipal {
    public static void main(String[] args) {

        ProcessBuilder pb = new ProcessBuilder("java", "FiltreLog.java");
        String[] llistaTexts = {
            "amncnjodn ERRORjjdso",
            "ERRORERRORmvjn",
            "knvjnin",
            "jdosjoERROR"
        };
        
        try {
            Process process = pb.start();
            BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            
            for (String linia: llistaTexts) {
                escritor.write(linia);
                escritor.newLine();
            }

            escritor.flush();
            escritor.close();

            BufferedReader lector = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String resposta;
            if ((resposta = lector.readLine()) != null) {
                IO.println("Fill → Pare: " + resposta);
            }
            
        } catch (IOException e) {
            IO.println(e.getMessage());
        }
    }
}