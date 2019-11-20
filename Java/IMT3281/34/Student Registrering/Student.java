import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;


public class Student {
    Scanner scanner = new Scanner(System.in);
    String navn, klasse;
    List<Emne> emner = new LinkedList();

    public Student(String n, String k) {
        navn = n;
        klasse = k;

        System.out.print("Navn på emne: ");
        String emneNavn = scanner.nextLine();

        while (!emneNavn.contentEquals("ingen flere emner")) {
            System.out.print("Emnekode: ");
            String emneKode = scanner.nextLine();

            emner.add(new Emne(emneNavn, emneKode));

            System.out.print("\nNavn på emne ('ingen flere emner for å avslutte'): ");
            emneNavn = scanner.nextLine();
        }
    }
}

