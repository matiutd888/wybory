package wybory;

import java.util.LinkedList;
import java.util.List;

//Reprezentuje wyborcę analizującego wszystkich kandydatów,
//i porównującego ich za pomocą metody 'compareKandydatów'.
public abstract class WyborcaIterujący extends Wyborca {
    public WyborcaIterujący(String imię, String nazwisko) {
        super(imię, nazwisko);
    }

    //Metoda porównująca kandydatów, zwraca wartość zależnie od tego, na którego kandydata
    //wyborca oddałby głos mając do wyboru tylko kandydatów k1, k2.
    //Zwraca:
    // 1) Wartość dodatnią, jeśli wyborca oddałby głos na kandydata k1
    // 2) Wartość ujemną, jeśli wyborca oddałby głos na kandydata k2
    // 3) Wartość 0, jeśli nie mógłby zdecydować i musiałby losować
    // (obaj kandydaci są w jego ocenie równi)
    protected abstract int compareKandydatów(Kandydat k1, Kandydat k2);

    private List<Kandydat> znajdźNajlepszych(List<ListaKandydatów> listyPartii) {
        boolean czyWybrano = false;
        List<Kandydat> wybrani = null;
        for (ListaKandydatów lista : listyPartii) {
            int n = lista.getLiczbaKandydatów();
            for (int i = 0; i < n; i++) {
                Kandydat kandydat = lista.getKandydatONumerze(i + 1);
                if (!czyWybrano) {
                    wybrani = new LinkedList<>();
                    wybrani.add(kandydat);
                    czyWybrano = true;
                } else {
                    Kandydat reprezentant = wybrani.get(0);
                    int porównanie = compareKandydatów(kandydat, reprezentant);
                    if (porównanie > 0) {
                        wybrani = new LinkedList<>();
                        wybrani.add(kandydat);
                    } else if (porównanie == 0) {
                        wybrani.add(kandydat);
                    }
                }
            }
        }
        return wybrani;
    }

    @Override
    public Kandydat wybierz(List<ListaKandydatów> listyPartyjne) {
        return losowyZListy(znajdźNajlepszych(listyPartyjne));
    }

    @Override
    public String toString() {
        return "WyborcaCechowy{" +
                super.toString() +
                '}';
    }
}
