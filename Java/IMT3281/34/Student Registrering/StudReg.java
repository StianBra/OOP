import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class StudReg {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> studenter = new LinkedList();

        System.out.print("Navn på student: ");
        String navn = scanner.nextLine();

        while (!navn.contentEquals("ingen flere studenter")) { // Looper til 'ingen flere studenter' er angitt
            System.out.print("\nKlasse for student: ");
            String klasse = scanner.nextLine();

            studenter.add(new Student(navn, klasse));

            System.out.print("\nNavn på student: ");
            navn = scanner.nextLine();
        }
    }
}
