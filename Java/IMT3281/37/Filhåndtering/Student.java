import java.io.*;
import java.util.LinkedList;

public class Student implements Serializable, Comparable<Student> {
    private String firstName, lastName, affiliation;
    private static final long serialVersionUID = 1L;

    public Student(String firstName, String lastName, String affiliation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.affiliation = affiliation;
    }

    public static void main(String[] args) throws IOException {
        LinkedList<Student> studenter = new LinkedList<Student>();
        FileReader file = new FileReader("Resources/Students.txt");
        BufferedReader in = new BufferedReader(file);

        String text = in.readLine();                   // Hopper over "tittel"-tekst øverst
        text = in.readLine();

        while (text != null) {                                          // Looper gjennom hele fila
            //String[] lines = text.split("[^;]+");                     // Separerer data med regex (teksten mellom hver ;)
            String[] lines = text.split(";", 100);
            studenter.add(new Student(lines[0], lines[1], lines[2]));  // Legger til et nytt Student-objekt i lista
            text = in.readLine();                                       // Leser neste linje
        }

        System.out.println("Åpner Students.obj for skriving...");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Students.obj"));
        System.out.println("Fil åpnet, skriver til fil!");

        while (studenter.size() > 0) {
            Student student = studenter.remove();
            oos.writeObject(student);
        }
        System.out.println("Skriving til fil fullført!");
    }

    @Override
    public int compareTo(Student student) {
        return this.lastName.compareToIgnoreCase(student.lastName);
    }
}