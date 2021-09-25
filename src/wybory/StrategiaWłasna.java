package wybory;


//Strategia wybiera działanie maksymalizując największą sumę ważoną wśród kandydatów na liście w okręgu
//biorąc jako wagi wektor zmiany wag dany w działaniu.
public class StrategiaWłasna extends Strategia {
    private int getNajwiększaZmianaUKandydata(Partia partia, Działanie działanie, Okręg okręg) {
        int maks = Integer.MIN_VALUE;
        ListaKandydatów listaKandydatów = okręg.getListaNumer(partia.getNumerListy());
        for (int i = 0; i < listaKandydatów.getLiczbaKandydatów(); i++) {
            Kandydat kandydat = listaKandydatów.getKandydatONumerze(i + 1);
            maks = Math.max(maks, kandydat.getSumaWażonaCech(działanie.getWektorZmianyWag()));
        }
        return maks;
    }

    @Override
    protected boolean compare(Partia partia, Działanie d1, Okręg o1, Działanie d2, Okręg o2) {
        return getNajwiększaZmianaUKandydata(partia, d1, o1) > getNajwiększaZmianaUKandydata(partia, d2, o2);
    }

    @Override
    public String toString() {
        return "W";
    }
}
