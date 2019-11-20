import java.io.*;
import java.util.ArrayList;

public class Student implements Serializable, Comparable<Student> {
    private String firstName, lastName, affiliation;
    private static final long serialVersionUID = 1L;

    public Student(String firstName, String lastName, String affiliation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.affiliation = affiliation;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, EOFException {
        Liste<Student> studenter = new Liste<Student>();

        System.out.println("Åpner Students.obj for lesing...");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Students.obj"));

        Student tempStud = (Student) ois.readObject();

        try {
            while (tempStud != null) {
                studenter.add(tempStud);
                tempStud = (Student) ois.readObject();
            }
        } catch(EOFException e) {
            System.out.println("Innlesing fullført!");
        }

        for (int i = 0; i < studenter.size(); i++) {
            System.out.print("Student " + i + ": ");
            Student temp = studenter.remove(i);
            temp.display();
        }
    }

    private void display() {
        System.out.println(this.firstName + " " + this.lastName + " - " + this.affiliation);
    }

    @Override
    public int compareTo(Student student) {
        return this.firstName.compareToIgnoreCase(student.firstName);
    }
}