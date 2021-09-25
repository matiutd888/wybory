package wybory;

import java.util.ArrayList;
import java.util.List;

public class Okręg {
    private static final int DZIELNIK = 10;
    private final int numerOkręgu, liczbaWyborców, liczbaMandatów;
    private final List<Wyborca> wyborcy;
    private final List<ListaKandydatów> listyPartii;

    public Okręg(int numerOkręgu, List<Wyborca> wyborcy, List<ListaKandydatów> listyPartii) {
        this.numerOkręgu = numerOkręgu;
        this.wyborcy = List.copyOf(wyborcy);
        this.listyPartii = List.copyOf(listyPartii);
        this.liczbaWyborców = wyborcy.size();
        this.liczbaMandatów = liczbaWyborców / DZIELNIK;
    }

    public int getNumerOkręgu() {
        return numerOkręgu;
    }

    public ListaKandydatów getListaNumer(int numer) {
        return listyPartii.get(numer - 1);
    }

    public int getLiczbaMandatów() {
        return liczbaMandatów;
    }

    public int getLiczbaWyborców() {
        return liczbaWyborców;
    }

    public int getLiczbaListPartii() {
        return listyPartii.size();
    }

    public List<Wyborca> getWyborcy() {
        return new ArrayList<>(wyborcy);
    }

    public Okręg scalOkręgi(Okręg o2) {
        int partie = getLiczbaListPartii();
        List<ListaKandydatów> scaloneListyPartii = new ArrayList<>(partie);
        for (int i = 0; i < partie; i++) {
            scaloneListyPartii.add(getListaNumer(i + 1).scalListę(o2.getListaNumer(i + 1)));
        }
        List<Wyborca> połączeniWyborcy = new ArrayList<>(wyborcy);
        połączeniWyborcy.addAll(o2.wyborcy);
        return new Okręg(numerOkręgu, połączeniWyborcy, scaloneListyPartii);
    }

    public Urna zbierzGłosy() {
        Urna urna = new Urna();
        for (Wyborca w : wyborcy) {
            urna.oddajGłos(w, new ArrayList<>(listyPartii));
        }
        return urna;
    }

    public void zastosujDziałanie(Działanie działanie) {
        for (Wyborca w : wyborcy)
            w.zastosujDziałanie(działanie);
    }

    public void wyświetlUrnę(Urna urna, Wypisywator wypisywator) {
        wypisywator.wyświetlUrnę(urna, new ArrayList<>(wyborcy), new ArrayList<>(listyPartii));
    }
}
