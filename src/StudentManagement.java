import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class StudentManagement extends JFrame {

    ArrayList<Student> studentArrayList = new ArrayList<>();
    static String databaseFile = "StudentManagement.txt";



    public static void main(String[] args) throws IOException {
        StudentManagement studentManagement = new StudentManagement();
        studentManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        studentManagement.setVisible(true);
    }
}
