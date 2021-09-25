package wybory;

import java.util.List;

public class Kandydat {
    private final String imię, nazwisko;
    private final Partia partia;
    private final List<Integer> cechy;

    public Kandydat(String imię, String nazwisko, Partia partia, List<Integer> cechy) {
        this.imię = imię;
        this.nazwisko = nazwisko;
        this.partia = partia;
        this.cechy = List.copyOf(cechy);
    }

    public String getImię() {
        return imię;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public int getSumaWażonaCech(List<Integer> wagi) {
        int suma = 0;
        for (int i = 0; i < cechy.size(); i++) {
            suma += wagi.get(i) * cechy.get(i);
        }
        return suma;
    }

    public int getWartośćCechy(int numerCechy) {
        return cechy.get(numerCechy - 1);
    }

    public Partia getPartia() {
        return partia;
    }

    @Override
    public String toString() {
        return "Kandydat{" +
                "imię='" + imię + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", partia = '" + partia.getNazwa() + '\'' +
                ", cechy=" + cechy +
                '}';
    }
}
