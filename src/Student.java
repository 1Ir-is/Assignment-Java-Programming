public class Student {
    public String Id, Name, Gender, Grade;

    public Student() {}

    public Student(String Id, String Name, String Gender, String Grade){
        this.Id = Id;
        this.Name = Name;
        this.Gender = Gender;
        this.Grade = Grade;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    @Override
    public String toString(){
        return Id + "," + Name + "," + Gender + "," + Grade;
    }
}
