package wybory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    private static final String Z_ROZMACHEM = "R";
    private static final String ZACHŁANNA = "Z";
    private static final String SKROMNA = "S";
    private static final String WŁASNA = "W";

    private static final int ŻELAZNY_PARTYJNY = 1;
    private static final int ŻELAZNY_KANDYDATA = 2;
    private static final int MINIMALIZUJĄCY_JEDNOCECHOWY = 3;
    private static final int MAKSYMALIZUJĄCY_JEDNOCECHOWY = 4;
    private static final int WSZECHSTRONNY = 5;
    private static final int MINIMALIZUJĄCY_JEDNOCECHOWY_PARTYJNY = 6;
    private static final int MAKSYMALIZUJĄCY_JEDNOCECHOWY_PARTYJNY = 7;
    private static final int WSZECHSTRONNY_PARTYJNY = 8;
    private static final int DZIELNIK = 10;
    private int n, p, d, c;
    private List<String> nazwyPartii;
    private List<Integer> budżetyPartii;
    private List<String> strategie;
    private List<Partia> partie;
    private List<List<ListaKandydatów>> listyWOkręgach;
    private List<List<Wyborca>> wyborcyWOkręgach;
    private List<Integer> liczbyWyborców;
    private List<Działanie> działania;
    private List<Okręg> okręgi;
    private List<Okręg> scaloneOkręgi;
    private List<Pair<Integer, Integer>> paryDoScalenia;

    public Państwo read(InputStream inputStream) throws NieprawidłoweDane {
        Scanner scanner = new Scanner(inputStream);
        wczytajPierwszeLiczby(scanner);
        wczytajParyDoScalenia(scanner);
        nazwyPartii = new ArrayList<>(p);
        wczytajListęNapisów(scanner, nazwyPartii, p);
        budżetyPartii = new ArrayList<>(p);
        wczytajListęLiczb(scanner, budżetyPartii, p);
        strategie = new ArrayList<>(p);
        wczytajListęNapisów(scanner, strategie, p);
        stwórzPartie();
        liczbyWyborców = new ArrayList<>(n);
        wczytajListęLiczb(scanner, liczbyWyborców, n);
        listyWOkręgach = new ArrayList<>(n);
        wczytajKandydatów(scanner);
        wyborcyWOkręgach = new ArrayList<>(n);
        wczytajWyborców(scanner);
        stwórzOkręgi();
        działania = new ArrayList<>(d);
        wczytajDziałania(scanner);
        scanner.close();
        return new Państwo(okręgi, partie, działania, paryDoScalenia);
    }

    private void wczytajWyborców(Scanner scanner) throws NieprawidłoweDane {
        for (int i = 0; i < n; i++) {
            int liczbaWyborcówWOkręgu = liczbyWyborców.get(i);
            List<Wyborca> wyborcyWOkręgu = new ArrayList<>(liczbaWyborcówWOkręgu);
            for (int j = 0; j < liczbaWyborcówWOkręgu; j++) {
                Wyborca w = wczytajWyborcę(scanner, i + 1);
                wyborcyWOkręgu.add(w);
            }
            wyborcyWOkręgach.add(wyborcyWOkręgu);
        }
    }

    private void wczytajDziałania(Scanner scanner) {
        for (int i = 0; i < d; i++) {
            List<Integer> listaDziałania = new ArrayList<>(c);
            wczytajListęLiczb(scanner, listaDziałania, c);
            działania.add(new Działanie(listaDziałania));
        }
    }

    private void wczytajKandydatów(Scanner scanner) throws NieprawidłoweDane {
        for (int i = 0; i < n; i++)
            listyWOkręgach.add(wczytajKandydatówWOkręgu(scanner, i + 1));
    }

    private List<ListaKandydatów> wczytajKandydatówWOkręgu(Scanner scanner, int numerOkręgu) throws NieprawidłoweDane {
        ArrayList<ListaKandydatów> listyPartii = new ArrayList<>(p);
        int liczbaMandatów = getLiczbaMandatów(liczbyWyborców.get(numerOkręgu - 1));
        for (int i = 0; i < p; i++) {
            List<Kandydat> listaKandydatów = new ArrayList<>(liczbaMandatów);
            for (int j = 0; j < liczbaMandatów; j++) {
                Kandydat kandydat = wczytajKandydata(scanner, partie.get(i), numerOkręgu, j + 1);
                listaKandydatów.add(kandydat);
            }
            listyPartii.add(new ListaKandydatów(partie.get(i), listaKandydatów));
        }
        return listyPartii;
    }


    private void stwórzOkręgi() {
        okręgi = new ArrayList<>(n);
        scaloneOkręgi = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            okręgi.add(new Okręg(i + 1, wyborcyWOkręgach.get(i), listyWOkręgach.get(i)));
        }
    }

    private Kandydat wczytajKandydata(Scanner scanner, Partia partia, int oczekiwanyNumerOkręgu, int oczekiwanyNumerNaLiście) throws NieprawidłoweDane {
        String imię = scanner.next();
        String nazwisko = scanner.next();
        int numerOkręgu = scanner.nextInt();
        if (numerOkręgu != oczekiwanyNumerOkręgu)
            throw new NieprawidłoweDane();
        String nazwaPartii = scanner.next();
        String oczekiwanaNazwaPartii = partia.getNazwa();
        if (!nazwaPartii.equals(oczekiwanaNazwaPartii))
            throw new NieprawidłoweDane();
        int numerNaLiście = scanner.nextInt();
        if (oczekiwanyNumerNaLiście != numerNaLiście)
            throw new NieprawidłoweDane();
        List<Integer> cechy = new ArrayList<>(c);
        wczytajListęLiczb(scanner, cechy, c);

        return new Kandydat(imię, nazwisko, partia, cechy);
    }

    private int getLiczbaMandatów(int liczbaWyborców) {
        return liczbaWyborców / DZIELNIK;
    }

    private void stwórzPartie() {
        partie = new ArrayList<>(p);
        for (int i = 0; i < p; i++)
            partie.add(skontruujPartię(i + 1));
    }

    private Partia skontruujPartię(int numerListy) {
        String nazwa = nazwyPartii.get(numerListy - 1);
        int budżet = budżetyPartii.get(numerListy - 1);
        Strategia strategia = strategiaFromString(strategie.get(numerListy - 1));
        return new Partia(nazwa, budżet, strategia, numerListy);
    }

    private void wczytajPierwszeLiczby(Scanner scanner) {
        n = scanner.nextInt();
        p = scanner.nextInt();
        d = scanner.nextInt();
        c = scanner.nextInt();
    }

    private void wczytajListęNapisów(Scanner scanner, List<String> lista, int length) {
        for (int i = 0; i < length; i++)
            lista.add(scanner.next());
    }

    private void wczytajListęLiczb(Scanner scanner, List<Integer> lista, int length) {
        for (int i = 0; i < length; i++)
            lista.add(scanner.nextInt());
    }

    private Strategia strategiaFromString(String s) {
        if (s.equals(Z_ROZMACHEM)) {
            return new StrategiaZRozmachem();
        } else if (s.equals(ZACHŁANNA)) {
            return new StrategiaZachłanna();
        } else if (s.equals(SKROMNA)) {
            return new StrategiaSkromna();
        } else if (s.equals(WŁASNA)) {
            return new StrategiaWłasna();
        } else return null;
    }

    private Partia wczytajPartięPoNazwie(Scanner scanner) {
        String nazwa = scanner.next();
        for (Partia p : partie) {
            if (p.getNazwa().equals(nazwa))
                return p;
        }
        return null;
    }

    private Kandydat wczytajKandydataPoNumerze(Scanner scanner, int numerOkręgu, Partia partia) {
        int numerNaLiście = scanner.nextInt();
        ListaKandydatów listaKandydatów = listyWOkręgach.get(numerOkręgu - 1).get(partia.getNumerListy() - 1);
        return listaKandydatów.getKandydatONumerze(numerNaLiście);
    }

    private Wyborca wczytajWyborcę(Scanner scanner, int oczekiwanyNumerOkręgu) throws NieprawidłoweDane {
        String imię = scanner.next();
        String nazwisko = scanner.next();
        int numerOkręgu = scanner.nextInt();
        if (numerOkręgu != oczekiwanyNumerOkręgu)
            throw new NieprawidłoweDane();

        int id = scanner.nextInt();
        if (id == ŻELAZNY_PARTYJNY) {
            Partia partia = wczytajPartięPoNazwie(scanner);
            return new ŻelaznyPartyjny(imię, nazwisko, partia);
        } else if (id == ŻELAZNY_KANDYDATA) {
            Partia partia = wczytajPartięPoNazwie(scanner);
            Kandydat kandydat = wczytajKandydataPoNumerze(scanner, numerOkręgu, partia);
            return new ŻelaznyKandydata(imię, nazwisko, kandydat);
        } else if (id == WSZECHSTRONNY || id == WSZECHSTRONNY_PARTYJNY) {
            List<Integer> wagi = new ArrayList<>(c);
            wczytajListęLiczb(scanner, wagi, c);
            if (id == WSZECHSTRONNY)
                return new Wszechstronny(imię, nazwisko, wagi);
            Partia partia = wczytajPartięPoNazwie(scanner);
            return new WszechstronnyPartyjny(imię, nazwisko, wagi, partia);
        } else {
            int numerCechy = scanner.nextInt();
            if (id == MINIMALIZUJĄCY_JEDNOCECHOWY)
                return new MinimalizującyJednocechowy(imię, nazwisko, numerCechy);
            if (id == MAKSYMALIZUJĄCY_JEDNOCECHOWY)
                return new MaksymalizującyJednocechowy(imię, nazwisko, numerCechy);
            Partia partia = wczytajPartięPoNazwie(scanner);
            if (id == MINIMALIZUJĄCY_JEDNOCECHOWY_PARTYJNY)
                return new MinimalizującyJednocechowyPartyjny(imię, nazwisko, numerCechy, partia);
            return new MaksymalizującyJednocechowyPartyjny(imię, nazwisko, numerCechy, partia);
        }
    }

    private Pair<Integer, Integer> paraFromString(String s) {
        String[] spliter = s.split("\\(|\\)|,");
        int first = Integer.parseInt(spliter[1]);
        int second = Integer.parseInt(spliter[2]);
        return new Pair<>(first, second);
    }

    private void wczytajParyDoScalenia(Scanner scanner) {
        int ile = scanner.nextInt();
        paryDoScalenia = new ArrayList<>(ile);
        for (int i = 0; i < ile; i++) {
            String opisPary = scanner.next();
            paryDoScalenia.add(paraFromString(opisPary));
        }
    }
}
