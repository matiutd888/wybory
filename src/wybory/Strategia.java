package wybory;

import java.util.List;

public abstract class Strategia {
    //Zwraca wartość true, jeśli działanie zostało wybrane i zastosowane
    public boolean wybierzIZastosujDziałanie(Partia partia, List<Okręg> okręgi, List<Działanie> działania) {
        int budżet = partia.getBudżet();
        boolean wybrano = false;
        Okręg wybranyOkręg = null;
        Działanie wybraneDziałanie = null;
        int cena = 0;
        for (Okręg itOkręg : okręgi) {
            for (Działanie itDziałanie : działania) {
                int itCena = itDziałanie.getCenaZadziałaniaNaOkręgu(itOkręg);
                if (itCena <= budżet && (!wybrano || compare(partia, itDziałanie, itOkręg, wybraneDziałanie, wybranyOkręg))) {
                    wybrano = true;
                    wybranyOkręg = itOkręg;
                    wybraneDziałanie = itDziałanie;
                    cena = itCena;
                }
            }
        }
        if (!wybrano)
            return false;
        partia.wydajPieniądze(cena);
        wybranyOkręg.zastosujDziałanie(wybraneDziałanie);

        return true;
    }

    //Zwraca wartość 'true' jeśli Strategia powinna wybrać działanie d1 w okręgu o1
    //zamiast działania d2 w okręgu o2
    protected abstract boolean compare(Partia partia, Działanie d1, Okręg o1, Działanie d2, Okręg o2);
}
