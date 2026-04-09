import java.util.ArrayList;
import java.util.Scanner;

public class Java2weeksday2 {

    public static class Student {
        private String mssv;
        private String name;
        private int age;
        //Constructor
        public Student(String mssv, String name, int age) {
            setMssv(mssv);
            setName(name);
            setAge(age);
        }
        //Constructor mặc định
        public Student() {}
        //Getter
        public String getMssv(){return mssv;}
        public String getName(){return name;}
        public int getAge(){return age;}
        //Setter
        public void setMssv(String mssv){
            checkMssv(mssv);
            this.mssv = mssv;
        }
        public void setName(String name){
            checkName(name);
            this.name = name;
        }
        public void setAge(int age){
            checkAge(age);
            this.age = age;
        }
        @Override
        public String toString() {
            return String.format("Mssv: %s, Ho va ten: %s, Tuoi: %d.", mssv, name, age);
        }
        //check input
        private void checkMssv(String text){
            if (text.isEmpty())
                throw new IllegalArgumentException("Khong duoc de trong!!!");
            else if (!text.startsWith("SV"))
                throw new IllegalArgumentException("Mssv phai bat dau bang SV...");
            else if (text.length() != 5)
                throw new IllegalArgumentException("Mssv chi co 5 ky tu!!!");
            else if (text.equals("SV000"))
                throw new IllegalArgumentException("Mssv SV000 khong ton tai!!!");
            else{
                for (int i = 2; i < text.length(); i++) {
                if (!Character.isDigit(text.charAt(i)))
                    throw new IllegalArgumentException("3 ky tu cuoi phai la so!!!");
                }
            }
        }
        private void checkName(String text) {
            if (text.isEmpty())
                throw new IllegalArgumentException("Ten khong duoc de trong!!!");
            for (char c : text.toCharArray()) {
                if (Character.isDigit(c))
                    throw new IllegalArgumentException("Ten khong co so!!!");
            }
        }
        private void checkAge(int age) {
            if (age <= 18)
                throw new IllegalArgumentException("Tuoi phai lon hon 18!!!");
        }
        //Input student
        public void inputStudent(Scanner sc){
            System.out.println("-----Nhap thong tin sinh vien----");
            //Mssv
            while(true){
                try {
                    System.out.print("Mssv: ");
                    String mssvStr = sc.nextLine().toUpperCase().trim();
                    setMssv(mssvStr);
                    break;
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
            //Name
            while(true){
                try {
                    System.out.print("Ho va ten: ");
                    String nameStr = sc.nextLine().trim();
                    setName(nameStr);
                    break;
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
            // Age
            while (true){
                try {
                    System.out.print("Tuoi: ");
                    String ageStr = sc.nextLine().trim();
                    int age = Integer.parseInt(ageStr);
                    setAge(age);
                    break;
                } catch (NumberFormatException e) {
                    System.err.println("Tuoi phai la so nguyen!!!");
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
    //Hỏi tiếp tục hay dừng
    public static boolean yesOrNo(Scanner sc){
        while(true){
            System.out.print("Ban co muon tiep tuc khong? (Y/N): ");
            String yORn = sc.nextLine().toUpperCase().trim();
            switch(yORn){
                case "Y"-> {return true;}
                case "N"-> {return false;}
                default -> System.err.println("Vui long nhap Y hoac N!!!");
            }
        }
    }
    //Main
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            ArrayList<Student> list = new ArrayList<>();
            //Nhập thông tin sinh viên
            while(true){
                Student st = new Student();
                st.inputStudent(sc);
                list.add(st);
                System.out.println("Da luu thanh cong!!!");
                //Tiếp tục hay dừng
                if (!yesOrNo(sc))
                    break;
            }
            // Hiển thị thông tin sinh viên
            int i = 1;
            for(Student st : list){
                System.out.printf("----- Thong tin sinh vien %d ----%n", i++);
                System.out.println(st);
            }
        }
    }
}