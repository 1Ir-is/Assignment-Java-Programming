import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

public class StudentManagement extends JFrame {
    public JTextField nameField;
    public JTextField idField;
    public JTextField gradeField;
    public JRadioButton maleRadioButton;
    public JRadioButton femaleRadioButton;
    public JRadioButton otherRadioButton;

    ArrayList<Student> studentArrayList = new ArrayList<>();
    static String databaseFile = "StudentManagement.txt";

    public static void main(String[] args) throws IOException {
        StudentManagement studentManagement = new StudentManagement();
        studentManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        studentManagement.setVisible(true);
    }

    // ADD FUNCTION.
    public void addStudent(ActionEvent e){
        String name = nameField.getText().trim();
        String id = idField.getText().trim();
        String gender;
        if (maleRadioButton.isSelected()){
            gender = maleRadioButton.getText();
        } else if (femaleRadioButton.isSelected()) {
            gender = femaleRadioButton.getText();
        } else if (otherRadioButton.isSelected()) {
            gender = otherRadioButton.getText();
        }
        else {
            gender = "";
        }
        String grade = gradeField.getText().toUpperCase();

        //VALIDATE
        if (id.isEmpty() && name.isEmpty()){
            if (CheckId(id)){
                JOptionPane.showMessageDialog(null,
                        "Duplicated ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    //END

    //CHECK STUDENT INFO
    //CHECK ID
    public boolean CheckId(String id){
        for (Student user : studentArrayList) {
            if (user.getId().equals(id)) {
                return true;
            }
        }return false;
    }
}
