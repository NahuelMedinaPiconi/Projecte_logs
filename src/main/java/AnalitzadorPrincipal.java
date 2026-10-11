

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AnalitzadorPrincipal {
    public static void main(String[] args) {

        //Creació de la comanda del FillERROR i FillWARNING
        String cp = System.getProperty("java.class.path");
        ProcessBuilder pbWarning = new ProcessBuilder("java", "-cp", cp, "FiltreLog", "WARNING");
        ProcessBuilder pbError = new ProcessBuilder("java", "-cp", cp, "FiltreLog");

        //Enviam els errors del proces fill a l'arxiu errors_filtre.log
        pbError.redirectError(ProcessBuilder.Redirect.to(new File("errors_filtreError.log")));
        pbWarning.redirectError(ProcessBuilder.Redirect.to(new File("errors_filtreWarning.log")));

        String[] llistaTexts = IO.readln("").split("\\r?\\n");

        try  {
            //Inciam els procesos fills
            Process pErr = pbError.start();
            Process pWar = pbWarning.start();

            try (
                BufferedWriter inErr = new BufferedWriter(new OutputStreamWriter(pErr.getOutputStream()));
                BufferedWriter inWar = new BufferedWriter(new OutputStreamWriter(pWar.getOutputStream()));
            ) {
                //Escritura al fill
                for (String linia: llistaTexts) {
                    inErr.write(linia);
                    inWar.write(linia);

                    inErr.newLine();
                    inWar.newLine();
                }

                inErr.flush();
                inWar.flush();
            }

            BufferedReader outErr = new BufferedReader(new InputStreamReader(pErr.getInputStream()));
            BufferedReader outWar = new BufferedReader(new InputStreamReader(pWar.getInputStream()));

            //Lectura del fill
            String quantitatError;
            String quantitatWarning;
                
            //Esperam a que el fill termini per indicar com ha acabat el process fill.
            quantitatError = outErr.readLine();
            quantitatWarning = outWar.readLine();
            pErr.waitFor();
            pWar.waitFor();
            
            IO.println("RESULTAT: ERRORS=" + quantitatError + " | WARNINGS=" + quantitatWarning + " | EXIT_CODE=" + pErr.exitValue());
            
        } catch (IOException | InterruptedException e) {
            IO.println(e.getMessage());
        }
    }
}