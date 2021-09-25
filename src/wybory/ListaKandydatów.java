package wybory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Reprezentuję listę kandydatów jednej partii w danym okręgu.
public class ListaKandydatów {
    private final Partia partia;
    private final List<Kandydat> kandydaci;

    public ListaKandydatów(Partia partia, List<Kandydat> kandydaci) {
        this.partia = partia;
        this.kandydaci = List.copyOf(kandydaci);
    }

    public int getLiczbaKandydatów() {
        return kandydaci.size();
    }

    public Kandydat getKandydatONumerze(int numerNaLiście) {
        return kandydaci.get(numerNaLiście - 1);
    }

    //Tworzy listę powstałą poprzed "doklejenie" listy "doScalenia" na końcu listy.
    public ListaKandydatów scalListę(ListaKandydatów doScalenia) {
        List<Kandydat> scaleniKandydaci = new ArrayList<>(kandydaci);
        scaleniKandydaci.addAll(doScalenia.kandydaci);
        return new ListaKandydatów(partia, scaleniKandydaci);
    }

    public List<Kandydat> getKandydaciAsList() {
        return new LinkedList<>(kandydaci);
    }

    @Override
    public String toString() {
        return "ListaKandydatów{" +
                "partia=" + partia +
                ", kandydaci=" + kandydaci +
                '}';
    }

    public Partia getPartia() {
        return partia;
    }
}
