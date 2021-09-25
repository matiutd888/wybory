package wybory;

import java.util.List;

public class StrategiaZachłanna extends Strategia {
    private int getZmianaSumySumWażonych(Partia partia, Działanie działanie, Okręg okręg) {
        ListaKandydatów lista = okręg.getListaNumer(partia.getNumerListy());
        int liczbaKandydatów = lista.getLiczbaKandydatów();
        int sumaSumWażonych = 0;
        List<Integer> wektorZmianyWag = działanie.getWektorZmianyWag();
        List<Wyborca> wyborcy = okręg.getWyborcy();
        for (Wyborca wyborca : wyborcy) {
            for (int i = 0; i < liczbaKandydatów; i++)
                sumaSumWażonych += wyborca.getZmianaWOcenieKandydata(lista.getKandydatONumerze(i + 1), działanie);
        }
        return sumaSumWażonych;
    }

    @Override
    public String toString() {
        return "Z";
    }

    @Override
    protected boolean compare(Partia partia, Działanie d1, Okręg o1, Działanie d2, Okręg o2) {
        return getZmianaSumySumWażonych(partia, d1, o1) > getZmianaSumySumWażonych(partia, d2, o2);
    }
}
