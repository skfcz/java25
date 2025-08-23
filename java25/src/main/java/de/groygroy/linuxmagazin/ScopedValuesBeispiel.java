package de.groygroy.linuxmagazin;

/**
 * Dieses Beispiel zeigt die Verwendung von ScopedValues in Java 25.
 * Definiert in JEP 506
 *
 */
public class ScopedValuesBeispiel {

    public final static ScopedValue<String> Kontext = ScopedValue.newInstance();
    public final static ScopedValue<Integer> KontextII = ScopedValue.newInstance();

    public static void main(String ... args) {
        
        // Starten eines Runnables, welches den ScopedValue als Context bekommt
        for (int i = 0; i < 5; i++) {
            var resultat = ScopedValue.
                    // Zuweisung des ScopedValue im Kontext
                    where( Kontext, "Aufruf Nr. " + i).
                    // Ausführen eines Runnables mit Rückgabewert
                    call( () -> {
                        var meinKontext = Kontext.get();
                        System.out.println("Kontext: " + meinKontext);
                        return meinKontext.hashCode();

                    });

            ScopedValue.where( Kontext, "Aufruf II Nr. " + i).
                    // Ausführen eines Runnables ohne Rückgabewert
                    run( () -> {
                        var meinKontext = Kontext.get();
                        // ...
                    });

            ScopedValue.where(Kontext, "String " + i).where(KontextII, i*i)
                    .run(() -> {
                        var meinKontext = Kontext.get();
                        var meinKontextII = KontextII.get();
                        System.out.println("Mehrerer Kontexte: " + meinKontext + ", " + meinKontextII);
                    });
        }
        try {
            Kontext.get();
        } catch (Exception e) {
            System.out.println("Kein Kontext gesetzt: " + e);
        }

    }
}
