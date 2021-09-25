package wybory;

import java.util.ArrayList;
import java.util.List;

public class Działanie {
    private final List<Integer> wektorZmianyWag;
    private int sumaWartościBezwzględnychZmianyWag;

    public Działanie(List<Integer> wektorZmianyWag) {
        this.wektorZmianyWag = List.copyOf(wektorZmianyWag);
        sumaWartościBezwzględnychZmianyWag = 0;
        for (int i : this.wektorZmianyWag)
            sumaWartościBezwzględnychZmianyWag += Math.abs(i);
    }

    public int getCenaZadziałaniaNaOkręgu(Okręg o) {
        return sumaWartościBezwzględnychZmianyWag * o.getLiczbaWyborców();
    }

    public List<Integer> getWektorZmianyWag() {
        return new ArrayList<>(wektorZmianyWag);
    }
}
