package wybory;

import java.util.ArrayList;
import java.util.List;

public class WszechstronnyPartyjny extends Wszechstronny {
    private final Partia partia;

    public WszechstronnyPartyjny(String imię, String nazwisko, List<Integer> wagi, Partia partia) {
        super(imię, nazwisko, wagi);
        this.partia = partia;
    }

    @Override
    public Kandydat wybierz(List<ListaKandydatów> listyPartyjne) {
        ListaKandydatów listaKandydatówWybranejPartii = listyPartyjne.get(partia.getNumerListy() - 1);
        ArrayList<ListaKandydatów> pojedynczaLista = new ArrayList<>(1);
        pojedynczaLista.add(listaKandydatówWybranejPartii);
        return super.wybierz(pojedynczaLista);
    }

    @Override
    public String toString() {
        return "WszechstronnyPartyjny{" +
                super.toString() +
                "partia=" + partia.getNazwa() +
                '}';
    }
}
