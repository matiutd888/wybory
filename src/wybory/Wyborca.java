package wybory;

import java.util.List;
import java.util.Random;

public abstract class Wyborca {
    private final String imię, nazwisko;

    public Wyborca(String imię, String nazwisko) {
        this.imię = imię;
        this.nazwisko = nazwisko;
    }

    //Zwraca losowego kandydata z listy przekazanej jako argument.
    public static Kandydat losowyZListy(List<Kandydat> wybrani) {
        Random random = new Random();
        return wybrani.get(random.nextInt(wybrani.size()));
    }

    public abstract Kandydat wybierz(List<ListaKandydatów> listyPartyjne);
    //Jeśli wyborca ocenia kandydatów licząc ich średnią ważoną z ich cech,
    //funkcja zwraca wartość liczbową będącą zmianą w ocenie kandydata po zastosowaniu
    //na wyborcy działania 'działanie'. W przeciwnym przypadku funkcja zwraca wartość 0.

    public abstract int getZmianaWOcenieKandydata(Kandydat kandydat, Działanie działanie);

    public abstract void zastosujDziałanie(Działanie działanie);

    public String getImię() {
        return imię;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    @Override
    public String toString() {
        return "Wyborca{" +
                "imię='" + imię + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                '}';
    }
}
