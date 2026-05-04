/*Day 10: MVC Pattern (rất quan trọng)
1. MVC (Model–View–Controller): là một mô hình thiết kế giúp tách code thành các phần riêng biệt để dễ quản
lý, bảo trì và mở rộng.
    - Model (dữ liệu):
        + Là dữ liệu/table trong Database.
        + Chỉ chứa thuộc tính (field), getter/ setter.
        + Không xử lý logic.
        + Ví dụ:
            class Student {
                int id;
                String name;
                int age;
            }

    - DAO (Data-Access-Object): làm việc với database.
        + Truy cập Database.
        + Viết câu lệnh sql (CREATE, INSERT, UPDATE, DELETE, SELECT).
        + Lấy dữ liệu từ database trả về Model.
        + Không xử lý logic, không validate dữ liệu.
        + Ví dụ:    
            class StudentDAO {
                void insert(Student s) {SQL}
                List<Student> getAll() {SQL}
            }

    - Service (xử lý logic):
        + Xử lý nghiệp vụ (business logic), validate dữ liệu.
        + Gọi DAO để làm việc với Database.
        + Ví dụ:
            class StudentService {
                StudentDAO dao = new StudentDAO();
                void addStudent(Student s) {
                    if (s.getAge() < 18) {
                        System.out.println("Không đủ tuổi");
                        return;
                    }
                    dao.insert(s);
                }
            }

    - Controller (Main – điều phối):
        + Nơi user tương tác, nhập input, hiển thị output.
        + Gọi Service.
        + Không làm DB, không xử lý logic nghiệp vụ.
        + Ví dụ:
            public class Main {
                public static void main(String[] args) {
                    StudentService service = new StudentService();
                    service.addStudent(new Student(1, "An", 20));
                }
            }

    - View: console (System.out / Scanner)
    
    - Sơ đồ hoạt động:
        Controller (Main)
                ↓
        Service (Business Logic)
                ↓
        DAO (Data Access)
                ↓
            Database
                ↑
                DAO
                ↑
            Service
                ↑
            Controller 

2. Cấu trúc project MVC
    - Model (Student class): Chứa dữ liệu, ánh xạ với bảng trong Database.

    - DAO (StudentDAO – CRUD DB): Thực hiện các thao tác với Database.

    - Service (StudentService – xử lý logic, validate): Xử lý nghiệp vụ, kiểm tra dữ liệu, gọi DAO.

    - Controller (Main – menu console): Điều phối chương trình, gọi Service, nhận input và hiển thị output.

    - View: Console (System.out / Scanner).

*/
//-----------------------------------------------------------------------------------------------------------------------------------------------
/* 
Bài tập: Quản lý sinh viên (MVC)

Yêu cầu:
    1. Tạo class Student (id, name, age, email).
    2. Tạo StudentDAO:
        - insertStudent()
        - getAllStudents()
        - updateStudent()
        - deleteStudent()
    3. Tạo StudentService:
        - Gọi DAO
        - Validate dữ liệu (age > 0, email hợp lệ)
    4. Tạo Main:
        Menu:
            - Thêm sinh viên
            - Hiển thị danh sách
            - Cập nhật
            - Xóa
Mục tiêu:
    - Hiểu mô hình MVC
    - Biết cách tách code theo tầng (Model / DAO / Service / Controller)
    - Áp dụng MVC vào project thực tế
*/
package com.example.controller;
import java.util.Scanner;

import com.example.model.Student;
import com.example.service.StudentService;
import com.example.view.StudentView;
public class Main {
    //Gọi file
    private static final StudentView stView = new StudentView();
    private static final StudentService stService = new StudentService();
    //Id chưa tồn tại (Add)
    private static String inputIdNotExists(Scanner sc){
        while(true){
            try{
                String id = stView.inputId(sc);
                stService.validateId(id);
                stService.checkIdNotExists(id);
                return id;
            }catch(IllegalArgumentException e){
                System.err.println(e.getMessage());
            }
        }
    }
    //Id đã tồn tại (Update/Delete)
    private static String inputIdExists(Scanner sc){
        while(true){
            try{
                String id = stView.inputId(sc);
                stService.validateId(id);
                stService.checkIdExists(id);
                return id;
            }catch(IllegalArgumentException e){
                System.err.println(e.getMessage());
            }
        }
    }
    //Input name, age, email
    private static Student inputStudentInfo(Scanner sc, String currentId){
        String name, email;
        int age;
        //Name
        while(true){
            try{
                name = stView.inputName(sc);
                stService.validateName(name);
                break;
            }catch(Exception e){
                System.err.println(e.getMessage());
            }
        }
        //Age
        while(true){
            try{
                age = stView.inputAge(sc);
                stService.validateAge(age);
                break;
            }catch(Exception e){
                System.err.println(e.getMessage());
            }
        }
        //Email(có xử lý UNIQUE cho Update)
        while(true){
            try{
                email = stView.inputEmail(sc);
                stService.validateEmail(email);
                if(currentId == null){
                    //Add
                    stService.checkEmailNotExists(email);
                }else{
                    //Update
                    stService.checkEmailForUpdate(email, currentId);
                }
                break;
            }catch(Exception e){
                System.err.println(e.getMessage());
            }
        }
        //Gán giá trị
        Student s = new Student();
        s.setName(name);
        s.setAge(age);
        s.setEmail(email);
        return s;
    }
    public static void main(String[] args) {
        //try catch
        try(Scanner sc = new Scanner(System.in)){
            while(true){
                try {
                    stView.printMenu();
                    int choose = Integer.parseInt(sc.nextLine().trim());
                    switch(choose){
                        case 1 -> {
                            while(true){
                                System.out.println("1. Them sinh vien");
                                //Nhập
                                String id = inputIdNotExists(sc);
                                Student s = inputStudentInfo(sc, null);
                                //Thêm
                                stView.Notification(stService.addStudent(id, s.getName(), s.getAge(), s.getEmail()), "Them");                              
                                //Tiếp tục?
                                if(!stView.yesOrNo("Tiep tuc", sc)) break;
                            }
                        }
                        case 2 -> {
                            while(true){
                                System.out.println("2. Hien thi danh sach");
                                stView.showInfoAll(stService.getAllStudents());
                                //Tiếp tục?
                                if(!stView.yesOrNo("Tiep tuc", sc)) break;
                            }
                        }
                        case 3 -> {
                            while(true){
                                System.out.println("3. Cap nhat sinh vien");
                                //Nhập
                                String id = inputIdExists(sc);
                                //Hiển thị thông tin cũ
                                Student oldStudent = stService.findStudentId(id);
                                stView.showInfo(oldStudent);
                                //Nhập thông tin mới
                                System.out.println("----Nhap thong tin moi cua ID " + id + "----");
                                Student s = inputStudentInfo(sc, id);
                                //Sửa
                                stView.Notification(stService.setStudent(id, s.getName(), s.getAge(), s.getEmail()), "Cap nhat");                              
                                //Tiếp tục?
                                if(!stView.yesOrNo("Tiep tuc", sc)) break;
                            }
                        }
                        case 4 -> {
                            while(true){
                                System.out.println("4. Xoa sinh vien");
                                //Nhập
                                String id = inputIdExists(sc);
                                //Hiển thị thông tin cũ
                                Student oldStudent = stService.findStudentId(id);
                                stView.showInfo(oldStudent);
                                //Chắc chắn xóa?
                                if(stView.yesOrNo("Chắn chắn xóa", sc)) 
                                    //Xóa
                                    stView.Notification(stService.eraseStudent(id), "Xoa");                        
                                //Tiếp tục?
                                if(!stView.yesOrNo("Tiep tuc", sc)) break;
                            }
                        }
                        case 0 -> {return;}
                        default -> throw new IllegalArgumentException("Hay nhap so tu 0 den 4!!!");
                    }
                }catch(NumberFormatException n){
                    System.err.println("Phai nhap so!!!");
                }catch(IllegalArgumentException e){
                    System.err.println(e.getMessage());
                }
            }
        }
    } 
}  