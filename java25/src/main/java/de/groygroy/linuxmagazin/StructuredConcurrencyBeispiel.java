package de.groygroy.linuxmagazin;

import java.util.Date;
import java.util.concurrent.StructuredTaskScope;

/**
 * Dieses Beispiel zeigt die Verwendung von strukturierter Nebenläufigkeit in Java 25.
 * Definiert in JEP 505
 */
public class StructuredConcurrencyBeispiel {

    public final static ScopedValue<Double> Kontext = ScopedValue.newInstance();

    public static void main(String[] args) {
        // setzt den Kontext, der in den Tasks verwendet wird
        var ergebniss = ScopedValue.where(Kontext, Math.random()).call(() -> {

            // Erstellen eines neuen TaskScope
            try (var scope = StructuredTaskScope.open()) {

                // Starten der Aufgaben im Scope
                StructuredTaskScope.Subtask<String> aufgabe1 = scope.fork(() -> aufgabe1());
                StructuredTaskScope.Subtask<Double> aufgabe2 = scope.fork(() -> aufgabe2());

                // Warten auf die Fertigstellung der Aufgaben
                scope.join();

                var resultat = aufgabe1.get() + "_" + aufgabe2.get();

                return resultat;
            } catch (java.util.concurrent.StructuredTaskScope.FailedException exp)  {
                return "Fehler in einem StructuredTask" + exp.getCause();
            }  catch (Exception exp) {
                return "Sonstiger Fehler " + exp;
            }
        });
        System.out.println("Ergebnis: " + ergebniss);

    }

    private static String aufgabe1() {

        try {
            Thread.sleep(1000); // Simuliert eine Verzögerung
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return Kontext.get().toString();
    }
    private static Double aufgabe2() {

        var gleit = Kontext.get();
        if ( gleit > 0.5 ) {
            throw new IllegalStateException("Fehler für " + gleit +"> 0.5");
        }
        return Math.sqrt(gleit);
    }
}
