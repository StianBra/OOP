import java.util.Scanner;

public class DrivstoffForbruk {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char valg;
        int antReg = 0;
        float avgLitMil = 0;

        do {
            System.out.print("Antall km kjørt: ");
            int km = scanner.nextInt();

            System.out.print("Antall liter drivstoff brukt: ");
            int liter = scanner.nextInt();

            float litMil = (float)liter / (km * 10);
            avgLitMil = ((avgLitMil * antReg) + litMil) / ++antReg;

            System.out.println("Liter/mil = "+litMil);
            System.out.println("Avg. liter/mil på "+antReg+" registreringer = "+avgLitMil);

            System.out.print("Registrere flere forbruk? q for å avslutte.");
            valg = scanner.next().charAt(0);

        } while(valg != 'q');
    }
}
