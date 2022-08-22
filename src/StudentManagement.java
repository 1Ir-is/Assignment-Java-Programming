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
                        "Duplicated Id!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (CheckIdValid(id)) {
                JOptionPane.showMessageDialog(null,
                        "Id is invalid, please try again!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (CheckNameValid(name)) {
                JOptionPane.showMessageDialog(null,
                        "Name is invalid, please try again", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (CheckGradeValid(grade)) {
                JOptionPane.showMessageDialog(null,
                        "Grade must be P , M or D", "Error", JOptionPane.ERROR_MESSAGE);
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
    //END CHECK ID

    //CHECK ID IS VALID
    public boolean CheckIdValid(String id){
        try
        {
            Integer.parseInt(id);
            return true;
        }

        catch (NumberFormatException e){return false;}
    }
    //END CHECK ID IS VALID

    //CHECK NAME IS VALID
    public static boolean CheckNameValid(String name) {
        for (int i = 0; i < name.length(); ++i) {
            char ch = name.charAt(i);

            // If the character is not a letter and not a whitespace, return false.
            if (!Character.isLetter(ch) && !Character.isWhitespace(ch)) {
                return false;
            }
        }
        return true;
    }
    //END CHECK NAME IS VALID

    //CHECK GRADE IS VALID
    public boolean CheckGradeValid(String grade){
        if (grade.equalsIgnoreCase("P")
                || grade.equalsIgnoreCase("M")
                || grade.equalsIgnoreCase("D")){
            return true;
        }
        return false;
    }
    //END CHECK GRADE IS VALID
}
