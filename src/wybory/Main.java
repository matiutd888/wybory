package wybory;

import java.io.*;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
         
        if (args.length == 0) {
			System.err.println("Brak argumentu z nazwą pliku!");
			System.exit(1);
		}
        File file = new File(args[0]);
        
        try (InputStream inputStream = new FileInputStream(file)) {
            Państwo państwo = parser.read(inputStream);
            List<Okręg> okręgi = państwo.getScaloneOkręgi();
            Map<Okręg, Urna> głosy = państwo.przeprowadźGłosowania();
            państwo.policzGłosyIWyświetlWyniki(new DHondt(), głosy);
            państwo.policzGłosyIWyświetlWyniki(new SaintLague(), głosy);
            państwo.policzGłosyIWyświetlWyniki(new HareNiemeyer(), głosy);
        } catch (FileNotFoundException e) {
            System.err.println("Nieprawidłowa nazwa pliku!");
        } catch (IOException e) {
            System.err.println("Błąd wejścia/wyjścia!");
        } catch (NieprawidłoweDane nieprawidłoweDane) {
            System.err.println("Nieprawidłowe dane!");
        }
    }
}
