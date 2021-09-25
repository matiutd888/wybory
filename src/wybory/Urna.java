package wybory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Urna {
    private final Map<Kandydat, Integer> LiczbyGłosów;
    private final Map<Wyborca, Kandydat> głosyWyborców;

    public Urna() {
        LiczbyGłosów = new HashMap<>();
        głosyWyborców = new HashMap<>();
    }

    public void oddajGłos(Wyborca wyborca, List<ListaKandydatów> listyPartii) {
        if (głosyWyborców.containsKey(wyborca))
            return;
        Kandydat kandydat = wyborca.wybierz(listyPartii);
        głosyWyborców.put(wyborca, kandydat);
        int add;
        if (!LiczbyGłosów.containsKey(kandydat))
            add = 1;
        else add = LiczbyGłosów.get(kandydat) + 1;
        LiczbyGłosów.put(kandydat, add);
    }

    public Kandydat getGłosWyborcy(Wyborca wyborca) {
        return głosyWyborców.get(wyborca);
    }

    public int getGłosyKandydata(Kandydat kandydat) {
        return LiczbyGłosów.getOrDefault(kandydat, 0);
    }

    public int getGłosyPartii(Partia partia) {
        int ile = 0;
        for (Map.Entry<Kandydat, Integer> entry : LiczbyGłosów.entrySet()) {
            if (entry.getKey().getPartia() == partia)
                ile += entry.getValue();
        }
        return ile;
    }

    public int getŁącznaLiczbaGłosów() {
        int ile = 0;
        for (Map.Entry<Kandydat, Integer> entry : LiczbyGłosów.entrySet()) {
            ile += entry.getValue();
        }
        return ile;
    }
}
