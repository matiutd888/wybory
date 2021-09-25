package wybory;

import java.util.ArrayList;
import java.util.List;

public class MaksymalizującyJednocechowyPartyjny extends MaksymalizującyJednocechowy {
    private Partia partia;

    public MaksymalizującyJednocechowyPartyjny(String imię, String nazwisko, int numerCechy, Partia partia) {
        super(imię, nazwisko, numerCechy);
        this.partia = partia;
    }

    @Override
    public Kandydat wybierz(List<ListaKandydatów> listyPartyjne) {
        ListaKandydatów listaKandydatówWybranejPartii = listyPartyjne.get(partia.getNumerListy() - 1);
        ArrayList<ListaKandydatów> pojedynczaLista = new ArrayList<>();
        pojedynczaLista.add(listaKandydatówWybranejPartii);
        return super.wybierz(pojedynczaLista);
    }

    @Override
    public String toString() {
        return "MaksymalizującyJednocechowyPartyjny{" +
                super.toString() + '\'' +
                partia.toString() + '\'' +
                '}';
    }
}
