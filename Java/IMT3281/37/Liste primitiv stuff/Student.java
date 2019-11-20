import java.io.Serializable;

public class Student implements Serializable, Comparable<Student> {
    private String firstName, lastName, affiliation;

    public Student(String firstName, String lastName, String affiliation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.affiliation = affiliation;
    }

    public static void main(String[] args) {
        Liste list = new Liste();
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(5);
        list.print();
    }

    @Override
    public int compareTo(Student student) {
        return this.lastName.compareToIgnoreCase(student.lastName);
    }
}

/*
Bruk opp igjen Student klassen fra forrige uke, men denne gangen skal (filen med studenter)[students.txt] leses inn ved hjelp av BufferedReader.
Legg til Serializable interfacet på Studentklassen og skriv ut igjen alle studenter til en fil med ObjectOutputStream.
 */