/*
Day 6: File I/O + JSON

1. File I/O
    - Là đọc (input - data đi vào chương trình) và ghi (output - data đi ra chương trình) dữ liệu với file trong Java.
    - FileWriter, FileReader:
        + FileWriter: ghi file (.txt) theo ký tự.
        + FileReader: đọc file (.txt) theo ký tự.

    - BufferedReader, BufferedWriter:
        + BufferedReader: đọc theo dòng (readLine()).
        + BufferedWriter: ghi theo buffer, hiệu năng tốt hơn.
        
    - Ghi file .txt
    - Đọc file .txt

2. JSON 
    - JSON (JavaScript Object Notation): định dạng dữ liệu dạng text, độc lập ngôn ngữ.
    - Dùng để lưu trữ hoặc truyền dữ liệu.
    - Thư viện: Gson hoặc Jackson
        + Jackson: mạnh, chuẩn Spring Boot, dùng project lớn.
        + Gson: dễ dùng, code ngắn, phù hợp người mới.
        + org.json: đơn giản, test nhanh, dữ liệu nhỏ.
            
    - Convert:
        + Object → JSON:
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(student);
        
        + JSON → Object:
            Student st = mapper.readValue(jsonString, Student.class);
        Lưu ý: mapper thường dùng lại (hoặc Spring inject), không tạo nhiều lần trong thực tế.

*/
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
/*
Bài tập: Quản lý sinh viên (File + JSON)
Yêu cầu:
    1. Tạo class Student (mssv, name, age).
    2. Tạo danh sách sinh viên bằng List.
    3. Nhập dữ liệu sinh viên từ bàn phím.
    4. Ghi toàn bộ danh sách sinh viên ra file JSON.
    5. Đọc dữ liệu từ file JSON.
    6. Chuyển dữ liệu đọc được → List<Student>.
    7. In lại danh sách sinh viên ra màn hình.

Mục tiêu:
    - Biết ghi/đọc file trong Java
    - Biết dùng thư viện JSON (Gson/Jackson)
*/
package com.example;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Main{
    //class Student (mssv, name, age)
     public static class Student{
        private String id;
        private String name;
        private int age;
        //Contructor
        public Student(){}
        public Student(String id, String name, int age){
            setID(id);
            setName(name);
            setAGE(age);
        }
        //Getter
        public String getID(){return id;}
        public String getName(){return name;}
        public int getAGE(){return age;}
        //Setter
        public void setID(String id){
            validateID(id);
            this.id = id;
        }
        public void setName(String name){
            validateName(name);
            this.name = name;
        }
        public void setAGE(int ageInt){
            validateAGE(ageInt);
            this.age = ageInt;
        }
        //Validate
        private void validateID(String idStr){
            if(idStr == null || idStr.isEmpty())
                throw new IllegalArgumentException("ID khong duoc de trong!!!");
            else if(idStr.equals("SV000"))
                throw new IllegalArgumentException("SV000 khong ton tai!!!");
            else if(!idStr.matches("^SV\\d{3}$"))
                throw new IllegalArgumentException("ID sai dinh dang. Dinh dang dung vd: SV001,...!!!");
        }
        private void validateName(String nameStr){
            if(nameStr == null || nameStr.isEmpty())
                throw new IllegalArgumentException("Ten khong duoc de trong!!!");
            else if(!nameStr.matches("^[A-Za-zÀ-ỹ '-.]+$"))
                throw new IllegalArgumentException("Ten bao gom chu cai, dau cach ( ), dau nhay don ('), dau gach ngang (-) va dau cham(.)!!!");
        }
        private void validateAGE(int ageInt){
            if(ageInt < 18)
                throw new IllegalArgumentException("Tuoi phai lon hon hoac bang 18!!!");
        }
        //toString()
        @Override
        public String toString(){
            return "ID: " + getID() + "\nName: " + getName() + "\nTuoi: " + getAGE();
        }
    }
    //FindByID
    public static Student findStudentByID(List<Student> studentList, String idStr){
        return studentList.stream().filter(s -> s.getID().equals(idStr)).findFirst().orElse(null);
    }
    //Tên file 
    private static final String fileName = "students.json";
    //Tạo objMapper
    private static final ObjectMapper objMapper = new ObjectMapper();
    //Save
    public static void saveToFile(List<Student> studentList){
        try{
            objMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), studentList);
        }catch(IOException e){
            System.err.println("Loi doc/ghi file JSON: " + e.getMessage());
        }
    }
    //Load
    public static List<Student> readFromFile(){
        try{
            File file = new File(fileName);
            if(file.exists())
                return objMapper.readValue(file, new TypeReference<List<Student>>() {});
        }catch(IOException e){
            System.err.println("Loi doc/ghi file JSON: " + e.getMessage());
        }
        return new java.util.ArrayList<>();
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
        System.out.println("3. Sua thong tin sinh vien");
        System.out.println("4. Xoa sinh vien");
        System.out.println("5. Tim kiem sinh vien");
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
            List<Student> studentList = readFromFile();
            String idStr, nameStr;
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
                                //Nhập
                                while(true){
                                    try {
                                        System.out.print("Nhap ID: ");
                                        idStr = sc.nextLine().toUpperCase().trim();
                                        if(findStudentByID(studentList, idStr) != null)
                                            throw new IllegalArgumentException("Da co trong danh sach!!!");
                                        else{
                                            st.setID(idStr);
                                            break;
                                        }
                                    }catch(IllegalArgumentException i){
                                        System.err.println(i.getMessage());
                                    }
                                }
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
                                        st.setAGE(ageInt);
                                        break;
                                    }catch(NumberFormatException e){
                                        System.err.println("Nhap sai kieu du lieu!");
                                    }catch(IllegalArgumentException i){
                                        System.err.println(i.getMessage());
                                    }
                                }
                                //Thông tin trước khi lưu
                                System.out.println("----Thong tin sinh vien truoc khi luu----");
                                System.out.println(st.toString());
                                //Thêm
                                studentList.add(st);
                                saveToFile(studentList);
                                System.out.println("Da luu.");
                                //Tiếp tục?
                                if(!yesOrNo(sc)) break;
                            }
                        }
                        case 2 -> {
                            System.out.println("2. Hien thi danh sach sinh vien");
                            if(studentList.isEmpty())
                                System.out.println("Khong co sinh vien nao trong danh sach.");
                            else{
                                int i = 1;
                                for(Student student : studentList){
                                    System.out.println("----Thong tin sinh vien thu " + i + "----\n" + student.toString());
                                    i++;
                                }
                                pause(sc);
                            } 
                        }
                        case 3 -> {
                            System.out.println("3. Sua thong tin sinh vien");
                            if(studentList.isEmpty())
                                System.out.println("Khong co sinh vien nao trong danh sach.");
                            else while(true){
                                Student st;
                                //Nhập id cần sửa
                                while(true){
                                    try {
                                        System.out.print("Nhap ID sinh vien can sua: ");
                                        idStr = sc.nextLine().toUpperCase().trim();
                                        st = findStudentByID(studentList, idStr);
                                        if(st == null)
                                            throw new IllegalArgumentException("Chua co trong danh sach!!!");
                                        else break;
                                    }catch(IllegalArgumentException i){
                                        System.err.println(i.getMessage());
                                    }
                                }
                                //Thông tin trước khi sửa
                                System.out.println("----Thong tin sinh vien truoc khi sua----");
                                System.out.println(st.toString());
                                //Nhập
                                System.out.println("----Nhap thong tin can sua----");
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
                                        st.setAGE(ageInt);
                                        break;
                                    }catch(NumberFormatException e){
                                        System.err.println("Nhap sai kieu du lieu!");
                                    }catch(IllegalArgumentException i){
                                        System.err.println(i.getMessage());
                                    }
                                }
                                //Thông tin trước khi lưu
                                System.out.println("----Thong tin sinh vien sau khi sua----");
                                System.out.println(st.toString());
                                //Sửa
                                saveToFile(studentList);
                                System.out.println("Da sua.");
                                //Tiếp tục?
                                if(!yesOrNo(sc)) break;
                            }
                        }
                        case 4 -> {
                            System.out.println("4. Xoa sinh vien");
                            if(studentList.isEmpty())
                                System.out.println("Khong co sinh vien nao trong danh sach.");
                            else {
                                Student st;
                                while(true){
                                    //Nhập id cần xóa
                                    while(true){
                                        try {
                                            System.out.print("Nhap ID sinh vien can xoa: ");
                                            idStr = sc.nextLine().toUpperCase().trim();
                                            st = findStudentByID(studentList, idStr);
                                            if(st == null)
                                                throw new IllegalArgumentException("Chua co trong danh sach!!!");
                                            else{
                                                //Thông tin trước khi xóa
                                                System.out.println("----Thong tin sinh vien can xoa----");
                                                System.out.println(st.toString());
                                                break;
                                            }
                                        }catch(IllegalArgumentException i){
                                            System.err.println(i.getMessage());
                                        }
                                    }
                                    //Xóa
                                    studentList.remove(st); 
                                    saveToFile(studentList);
                                    System.out.println("Da xoa.");
                                    //Tiếp tục?
                                    if(!yesOrNo(sc)) break;
                                }
                            }
                        }
                        case 5 -> {
                            System.out.println("5. Tim kiem sinh vien");
                            if(studentList.isEmpty())
                                System.out.println("Khong co sinh vien nao trong danh sach.");
                            else while(true){
                                //Nhập id cần tìm
                                try {
                                    System.out.print("Nhap ID sinh vien can tìm: ");
                                    idStr = sc.nextLine().toUpperCase().trim();
                                    Student st = findStudentByID(studentList, idStr);
                                    if(st == null)
                                        throw new IllegalArgumentException("Chua co trong danh sach!!!");
                                    else System.out.println(st.toString());
                                }catch(IllegalArgumentException i){
                                    System.out.println(i.getMessage());
                                }
                                //Tiếp tục?
                                if(!yesOrNo(sc)) break;
                            }
                        }
                        case 0 -> {
                            saveToFile(studentList);
                            return;
                        }
                        default -> throw  new IllegalArgumentException("Phai nhap so tu 0 den 5!!!");
                    }
                }catch(NumberFormatException i){
                    System.err.println("Phai nhap so!!!");
                }catch(IllegalArgumentException i){
                    System.err.println(i.getMessage());
                }
            }
        }
    }
}