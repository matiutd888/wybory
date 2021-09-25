package wybory;

import java.util.List;
import java.util.Map;

//Klasa udostępniająca metody wypisująca odpowiednie obiekty
//metodą zgodnę ze specyfikacją zadania
public class Wypisywator {
    public void wyświetlUrnę(Urna urna, List<Wyborca> wyborcy, List<ListaKandydatów> listyPartii) {
        for (Wyborca wyborca : wyborcy) {
            Kandydat kandydat = urna.getGłosWyborcy(wyborca);
            System.out.println(wyborca.getImię() + " "
                    + wyborca.getNazwisko() + " "
                    + kandydat.getImię() + " "
                    + kandydat.getNazwisko() + " "
            );
        }

        for (int i = 0; i < listyPartii.size(); i++) {

            ListaKandydatów lista = listyPartii.get(i);
            int liczbaKandydatów = lista.getLiczbaKandydatów();
            Partia partia = lista.getPartia();
            for (int j = 0; j < liczbaKandydatów; j++) {
                Kandydat kandydat = lista.getKandydatONumerze(j + 1);
                int liczbaGłosów = urna.getGłosyKandydata(kandydat);
                System.out.println(kandydat.getImię() + " "
                        + kandydat.getNazwisko() + " "
                        + partia.getNazwa() + " "
                        + Integer.toString(j + 1) + " "
                        + liczbaGłosów);
            }
        }
    }

    public void wyświetlMandaty(List<Partia> partie, Map<Partia, Integer> mandaty) {
        for (Partia partia : partie) {
            int liczbaMandatów = mandaty.get(partia);
            System.out.println(partia.getNazwa() + " " + liczbaMandatów);
        }
    }
}
