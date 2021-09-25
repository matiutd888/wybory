package wybory;

import java.util.List;

public class ŻelaznyKandydata extends Wyborca {
    private final Kandydat kandydat;

    public ŻelaznyKandydata(String imię, String nazwisko, Kandydat kandydat) {
        super(imię, nazwisko);
        this.kandydat = kandydat;
    }

    @Override
    public int getZmianaWOcenieKandydata(Kandydat kandydat, Działanie działanie) {
        return 0;
    }

    @Override
    public Kandydat wybierz(List<ListaKandydatów> listyPartyjne) {
        return kandydat;
    }

    @Override
    public void zastosujDziałanie(Działanie działanie) {
        ;
    }

    @Override
    public String toString() {
        return "ŻelaznyKandydata{" +
                super.toString() +
                "kandydat=" + kandydat.getImię() +
                '}';
    }
}
