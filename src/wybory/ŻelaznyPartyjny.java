package wybory;

import java.util.List;

public class ŻelaznyPartyjny extends Wyborca {
    private final Partia partia;

    public ŻelaznyPartyjny(String imię, String nazwisko, Partia partia) {
        super(imię, nazwisko);
        this.partia = partia;
    }

    @Override
    public int getZmianaWOcenieKandydata(Kandydat kandydat, Działanie działanie) {
        return 0;
    }

    @Override
    public Kandydat wybierz(List<ListaKandydatów> listyPartyjne) {
        ListaKandydatów listaKandydatówPartii = listyPartyjne.get(partia.getNumerListy() - 1);
        return losowyZListy(listaKandydatówPartii.getKandydaciAsList());
    }

    @Override
    public void zastosujDziałanie(Działanie działanie) {
        ;
    }

    @Override
    public String toString() {
        return "ŻelaznyPartyjny{" +
                super.toString() +
                "partia=" + partia.getNazwa() +
                '}';
    }
}
