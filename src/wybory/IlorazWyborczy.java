package wybory;

import java.util.Comparator;

//Reprezentuje obiekt ilorazu wyborczego, używany w metodach D'Hondta i Sainte-Laguë.
public class IlorazWyborczy {
    private final double wartość;
    private final Partia partia;
    
    public static final Comparator<IlorazWyborczy> comparator = new Comparator<IlorazWyborczy>() {
        //Porównuje Ilorazy po wartości, zwraca liczbę dodatnią, jesli wartość ilorazu
        //o1 jest mniejsza od wartości ilorazu o2.
        @Override
        public int compare(IlorazWyborczy o1, IlorazWyborczy o2) {
            if (o1.wartość > o2.wartość)
                return -1;
            if (o1.wartość == o2.wartość)
                return 0;
            return 1;
        }
    };
    
    public IlorazWyborczy(double wartość, Partia partia) {
        this.wartość = wartość;
        this.partia = partia;
    }

    public Partia getPartia() {
        return partia;
    }
}

