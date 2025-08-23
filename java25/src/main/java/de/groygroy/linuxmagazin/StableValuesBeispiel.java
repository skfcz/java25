package de.groygroy.linuxmagazin;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.function.Supplier;

public class StableValuesBeispiel {


    static final Date LADEZEIT = new Date();
    static final StableValue<Date> BEIBEDARF = StableValue.of();
    static final Supplier<Date> MITSUPPLIER = StableValue.supplier(() -> new Date());
    static final List<Date> LISTE= StableValue.list(10, i -> {
        System.out.println("1. Anfrage für Index " + i);
        return new GregorianCalendar(2025, Calendar.AUGUST,i+1, i,i,i).getTime();
    });


    public static void main() throws  Exception{

        System.out.println("           geladen: " + LADEZEIT);
        Thread.sleep(1000);
        System.out.println("             jetzt: " + new Date());
        Thread.sleep(1000);
        System.out.println("        bei Bedarf: " + BEIBEDARF.orElse(new Date()));
        Thread.sleep(1000);
        System.out.println("nochmal bei Bedarf: " + BEIBEDARF.orElse(new Date()));
        Thread.sleep(1000);
        System.out.println("      mit Supplier: " + MITSUPPLIER.get());

        List.of(1,1,5).forEach( i -> {
            System.out.println("  Liste[" + i + "]: " + LISTE.get(i));
        });




    }


}
