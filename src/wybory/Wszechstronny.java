package wybory;

import java.util.ArrayList;
import java.util.List;

/* Wszechstronny liczy średnią ważoną cech dla każdego z kandydatów, przypisując
        każdej z jego cech całkowite wagi (które zawsze powinny być z przedziału -100 do
        100) ze swojego wektora wag, a następnie dokonuje wyboru maksymalizując średnią
        ważoną*/
public class Wszechstronny extends WyborcaIterujący {
    private static final int MAKSYMALNA_WAGA = 100;
    private static final int MINIMALNA_WAGA = -100;
    private List<Integer> wagi;

    public Wszechstronny(String imię, String nazwisko, List<Integer> wagi) {
        super(imię, nazwisko);
        this.wagi = List.copyOf(wagi);
    }

    @Override
    public int getZmianaWOcenieKandydata(Kandydat kandydat, Działanie działanie) {
        int sumaBezDziałania = kandydat.getSumaWażonaCech(wagi);
        int sumaZDziałaniem = kandydat.getSumaWażonaCech(zmieńWagi(działanie.getWektorZmianyWag(), wagi));
        return sumaZDziałaniem - sumaBezDziałania;
    }

    @Override
    protected int compareKandydatów(Kandydat k1, Kandydat k2) {
        int s1 = k1.getSumaWażonaCech(new ArrayList<>(wagi));
        int s2 = k2.getSumaWażonaCech(new ArrayList<>(wagi));
        if (s1 == s2)
            return 0;
        if (s1 > s2)
            return 1;
        return -1;
    }

    @Override
    public void zastosujDziałanie(Działanie działanie) {
        List<Integer> wektor = działanie.getWektorZmianyWag();
        wagi = zmieńWagi(wektor, wagi);
    }

    @Override
    public String toString() {
        return "Wszechstronny{" +
                super.toString() +
                "wagi=" + wagi +
                '}';
    }

    private List<Integer> zmieńWagi(List<Integer> zmianaWag, List<Integer> wagi) {
        List<Integer> wagiPoZmianie = new ArrayList<>(wagi);
        for (int i = 0; i < wagi.size(); i++) {
            int poZmianie = zmianaWag.get(i) + wagi.get(i);
            if (poZmianie > MAKSYMALNA_WAGA)
                poZmianie = MAKSYMALNA_WAGA;
            else if (poZmianie < MINIMALNA_WAGA)
                poZmianie = MINIMALNA_WAGA;
            wagiPoZmianie.set(i, poZmianie);
        }
        return wagiPoZmianie;
    }

}
