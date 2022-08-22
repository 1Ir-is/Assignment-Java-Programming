import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.font.FontRenderContext;
import java.io.*;
import java.util.ArrayList;

public class StudentManagement extends JFrame {
    public JTextField nameField;
    public JTextField idField;
    public JTextField gradeField;
    public JRadioButton maleRadioButton;
    public JRadioButton femaleRadioButton;
    public JRadioButton otherRadioButton;
    public JTable tableStudent;

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
            Student student = new Student(id, name, gender, grade);
            studentArrayList.add(student);
        }
        else {
            if (id.isEmpty()){
                JOptionPane.showMessageDialog(null,
                        "Id field can be blank", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Name field can be blank", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    //END VALIDATE
    //END ADD FUNCTION

    // DELETE FUNCTION
    public void deleteStudent(ActionEvent event){
        String id = idField.getText().trim();
        for (int i = 0; i<studentArrayList.size(); i++){
            if (studentArrayList.get(i).getId().equals(id)){
                studentArrayList.remove(i);
            }
        }
    }
    //END DELETE FUNCTION

    //UPDATE FUNCTION
    public void updateStudent(ActionEvent e){
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
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
            for (Student student : studentArrayList){
                if (student.getId().equals(id)){
                    student.setName(name);
                    student.setGender(gender);
                    student.setGrade(grade);
                }
            }
        }
        else{
            if (id.isEmpty()){
                JOptionPane.showMessageDialog(null,
                        "Id field can be blank", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Name field can be blank", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    //END UPDATE FUNCTION

    //SEARCH FUNCTION
    public void searchStudent(ActionEvent e){
        String id = idField.getText().trim();
        clearTableContents();
        if (!id.isEmpty()){
            for (Student student : studentArrayList) {
                if (student.getId().equals(id)) {
                    Student findedStudent = new Student(student.getId(), student.getName(), student.getGender(), student.getGrade());
                    clearTableContents();
                    returnFindedStudentsToTable(findedStudent);
                }
            }
        } else{
            returnStudentsToTable(readFile());
            JOptionPane.showMessageDialog(null, "Filling Search Field Required", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    //END SEARCH FUNCTION

    // return user by Object
    public void returnFindedStudentsToTable(Student student){
        DefaultTableModel defaultTableModel = (DefaultTableModel) tableStudent.getModel();
        String[] findedUser = student.toString().split(",");
        defaultTableModel.addRow(findedUser);
    }

    // clear all the contents of table
    public void clearTableContents(){
        DefaultTableModel defaultTableModel = (DefaultTableModel) tableStudent.getModel();
        defaultTableModel.setRowCount(0);
    }

    // read file
    public Object[] readFile(){
        Object[] objects;
        try {
            FileReader fr = new FileReader(databaseFile);
            BufferedReader bufferedReader = new BufferedReader(fr);
            // each lines to array
            objects = bufferedReader.lines().toArray();
            bufferedReader.close();
            return objects;
        } catch (IOException e) {

        }
        return null;
    }

    // write from list to file
    public static void writeToFile(ArrayList<Student> _users){
        try{
            FileWriter fw = new FileWriter(databaseFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Student user: _users
            ) {
                bw.write(user.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();

        }catch (Exception exception){

        }
    }

    // return user by Object array
    public void returnStudentsToTable(Object[] objects){
        DefaultTableModel defaultTableModel = (DefaultTableModel) tableStudent.getModel();
        int i = 0;
        while(i < objects.length) {
            String row = objects[i].toString().trim();
            String[] rows = row.split(",");
            defaultTableModel.addRow(rows);
            i++;
        }
    }
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
    //END CHECK STUDENT INFO
}
