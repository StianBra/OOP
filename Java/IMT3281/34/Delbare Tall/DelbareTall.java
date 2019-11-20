public class DelbareTall {
    public static void main(String[] args) {
        // ALT.1: Delelighet samlet i tre linjer (tre for loops, en per tall):
        System.out.print("Delelig på 2: ");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                System.out.print(i+" ");
            }
        }

        System.out.print("\nDelelig på 3: ");

        for (int i = 1; i <= 10; i++) {
            if (i % 3 == 0) {
                System.out.print(i+" ");
            }
        }

        System.out.print("\nDelelig på 4: ");

        for (int i = 1; i <= 10; i++) {
            if (i % 4 == 0) {
                System.out.print(i+" ");
            }
        }

        System.out.print("\n\n");

        // ALT.2: Alle tall på hver sin linje (en for loop):
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                System.out.println("Tallet " +i +" er delelig på 2.");
            }

            if (i % 3 == 0) {
                System.out.println("Tallet " +i +" er delelig på 3.");
            }

            if (i % 4 == 0) {
                System.out.println("Tallet " +i +" er delelig på 4.");
            }
        }
    }
}
