/*
Bài tập: Quản lý sinh viên (JDBC)
Yêu cầu:
    1. Kết nối database `student_management`
    2. Insert 3 sinh viên bằng Java
    3. Lấy danh sách sinh viên từ DB
    4. In ra console

Mục tiêu:
    - Kết nối Java với MySQL bằng JDBC
    - Thực hiện INSERT, SELECT
    - Hiểu CRUD cơ bản trong Java
*/
package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/student_management";
    private static final String user = "root";
    private static final String password = "Dao@20/08/2002";
    //Connect DB
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url, user, password);
        }catch (Exception e) {
            throw new RuntimeException("Lỗi kết nối Database!\n", e);
        }
    }
    //class Student 
    public static class Student{
        private String name;
        private int age;
        private String email;
        //Contructor
        public Student(){}
        public Student(String name, int age, String email){
            setName(name);
            setAge(age);
            setEmail(email);
        }
        //Getter
        public String getName(){return name;}
        public int getAge(){return age;}
        public String getEmail(){return email;}
        //Setter
        public void setName(String name){
            validateName(name);
            this.name = name;
        }
        public void setAge(int age){
            validateAge(age);
            this.age = age;
        }
        public void setEmail(String email){
            validateEmail(email);
            this.email = email;
        }
        //Validate
        private void validateName(String nameStr){
            if(nameStr == null || nameStr.isEmpty())
                throw new IllegalArgumentException("Ten khong duoc de trong!!!");
            else if(!nameStr.matches("^[A-Za-zÀ-ỹ '-.]+$"))
                throw new IllegalArgumentException("Ten bao gom chu cai, dau cach ( ), dau nhay don ('), dau gach ngang (-) va dau cham(.)!!!");
        }
        private void validateAge(int ageInt){
            if(ageInt < 18)
                throw new IllegalArgumentException("Tuoi phai lon hon hoac bang 18!!!");
        }
        private void validateEmail(String emailStr){
            if(emailStr == null || emailStr.isEmpty())
                throw new IllegalArgumentException("Email khong duoc de trong!!!");
            else if(!emailStr.matches("^[A-Za-z\\d._%+-]+@gmail.com$") && !emailStr.matches("^[A-Za-z\\d._%+-]+@email.com$"))
                throw new IllegalArgumentException("Email chi gom chu, so va cac ky tu dac biet (. _ % + -)!!!");
            else if(emailStr.length() > 100)
                throw new IllegalArgumentException("Email phai duoi 100 ky tu!!!");
        }
        //toString()
        public String toString(){
            return "Ho va ten: " + getName() + "\nTuoi: " + getAge() + "\nEmail: " + getEmail();
        }
    }
    //Hàm INSERT
    public static void insertStudent(String nameStr, int ageInt, String emailStr){
        String sql = "INSERT INTO student (student_name, age, email) VALUES (?, ?, ?);";
        try(Connection con = getConnection(); //Connect DB
            PreparedStatement prep = con.prepareStatement(sql) /*Chứa câu lệnh sql*/){    
                //Gán giá trị vào sql
                prep.setString(1, nameStr);
                prep.setInt(2, ageInt);
                prep.setString(3, emailStr);
                //Thực thi sql
                int rows = prep.executeUpdate();
                if(rows > 0)
                    System.out.println("Them sinh vien thanh cong.");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    //Hàm SELECT
    public static void selectStudent(){
        String sql = "SELECT * FROM student;";
        try(Connection con = getConnection();
        PreparedStatement prep = con.prepareStatement(sql); /*Chứa câu lệnh sql*/
        ResultSet rs = prep.executeQuery()){ 
            //Hiển thị
            int i = 0;
            while(rs.next()){
                i++;
                System.out.println("----Thong tin sinh vien thu " + i + "----");
                System.out.println("ID: " + rs.getInt("id") + "\nHo va ten: " + rs.getString("student_name") + "\nTuoi: " + rs.getInt("age") 
                + "\nEmail: " + rs.getString("email") + "\nNgay gio nhap: " + rs.getTimestamp("created_at"));
            }
            if(i == 0) System.out.println("Khong co sinh vien nao trong danh sach.");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    //Pause 
    public static void pause(Scanner sc){
        System.out.println("Nhan Enter de tiep tuc...");
        sc.nextLine();
    }
    //printMenu
    public static void printMenu(){
        System.out.println("--------Menu Chuc Nang--------");
        System.out.println("1. Them sinh vien");
        System.out.println("2. Hien thi danh sach sinh vien");
        System.out.println("0. Thoat");
        System.out.print("Chon chuc nang so: ");
    }
    //yesOrNo
    public static boolean yesOrNo(Scanner sc){
        while(true){
            System.out.print("Tiep tuc khong? Tra loi (y/n): ");
            String answer = sc.nextLine().trim().toLowerCase();
            switch(answer){
                case "y" -> {return true;}
                case "n" -> {return false;}
                default -> System.err.println("Tra loi y hoac n!!!");
            }
        }
    }
    //main
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            String nameStr, emailStr;
            int ageInt;
            while(true){
                printMenu();
                try{
                    int chooseMenu = Integer.parseInt(sc.nextLine().trim());
                    switch(chooseMenu){
                        case 1 -> {
                            while(true){
                                System.out.println("1. Them sinh vien");
                                Student st = new Student();
                                while(true){
                                    try {
                                        System.out.print("Nhap ho va ten: ");
                                        nameStr = sc.nextLine().trim();
                                        st.setName(nameStr);
                                        break;
                                    }catch(IllegalArgumentException i){
                                        System.err.println(i.getMessage());
                                    }
                                }
                                while(true){
                                    try {
                                        System.out.print("Nhap tuoi: ");
                                        ageInt = Integer.parseInt(sc.nextLine().trim());
                                        st.setAge(ageInt);
                                        break;
                                    }catch(NumberFormatException e){
                                        System.err.println("Nhap sai kieu du lieu!");
                                    }catch(IllegalArgumentException i){
                                        System.err.println(i.getMessage());
                                    }
                                }
                                while(true){
                                    try {
                                        System.out.print("Nhap email: ");
                                        emailStr = sc.nextLine().trim();
                                        st.setEmail(emailStr);
                                        break;
                                    }catch(IllegalArgumentException i){
                                        System.err.println(i.getMessage());
                                    }
                                }
                                //Thông tin trước khi lưu
                                System.out.println("----Thong tin sinh vien truoc khi luu----");
                                System.out.println(st.toString());
                                //Thêm
                                insertStudent(nameStr, ageInt, emailStr);
                                //Tiếp tục?
                                if(!yesOrNo(sc)) break;
                            }
                        }
                        case 2 -> {
                            System.out.println("2. Hien thi danh sach sinh vien");
                            //Hiển thị
                            selectStudent();
                            //Pause
                            pause(sc);
                        }
                        case 0 -> {return;}
                        default -> throw  new IllegalArgumentException("Phai nhap so tu 0 den 2!!!");
                    }
                }catch(NumberFormatException i){
                    System.err.println("Phai nhap so!!!");
                }catch(IllegalArgumentException i){
                    System.err.println(i.getMessage());
                }
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}