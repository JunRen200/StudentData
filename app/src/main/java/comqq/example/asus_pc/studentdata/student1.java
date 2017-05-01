package comqq.example.asus_pc.studentdata;

import java.util.List;

/**
 * Created by asus-pc on 2017/5/1.
 */

public class student1 {
    private List<StudentBean> Student;
    public List<StudentBean> getStudentBean(){
        return Student;
    }
    public static class StudentBean {
        private String number;
        private String name;
        private String password;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
