
import java.util.ArrayList;
import java.util.Scanner;

public class Java2weeksday4{
    //abstract class Person
    public static abstract class Person{
        String id;
        String name;
        //method
        public abstract String displayInfo();
    }
    //Class Student extends Person
    public static class Student extends Person{
        int age;
        double gpa;
        //Contructor
        public Student(){}
        public Student(String id, String name, int age, double gpa){
            setID(id);
            setName(name);
            setAGE(age);
            setGPA(gpa);
        }
        //Getter
        public String getID(){
            return id;
        }
        public String getName(){
            return name;
        }
        public int getAGE(){
            return age;
        }
        public double getGPA(){
            return gpa;
        }
        //Setter
        public void setID(String id){
            validateID(id);
            this.id = id;
        }
        public void setName(String name){
            validateName(name);
            this.name = name;
        }
        public void setAGE(int age){
            validateAGE(age);
            this.age = age;
        }
        public void setGPA(double gpa){
            validateGPA(gpa);
            this.gpa = gpa;
        }
        //Validate
        public static void validateID(String idStr){
            if(idStr == null || idStr.isEmpty()) 
                throw new IllegalArgumentException("ID khong duoc de trong!!!");
            else if(idStr.equals("ID000"))
                throw new IllegalArgumentException("ID000 khong ton tai");
            else if(!idStr.matches("^ID\\d{3}$"))
                throw new IllegalArgumentException("ID sai format! Format dung: ID001,...");
        }
        public static void validateName(String nameStr){
            if(nameStr == null || nameStr.isEmpty()) 
                throw new IllegalArgumentException("Ten khong duoc de trong!!!");
            else if(!nameStr.matches("^[a-zA-ZÀ-ỹ\\x20']+$"))
                throw new IllegalArgumentException("Ten chi bao gom chu cai, dau cach ( ) va dau nhay don (')!!!");
        }
        public static void validateAGE(int ageInt){
            if(ageInt <= 18) 
                throw new IllegalArgumentException("Tuoi phai lon hon 18!!!");
        }
        public static void validateGPA(double gpaDou){
            if(gpaDou < 0 || gpaDou > 4) 
                throw new IllegalArgumentException("GPA chi tu 0 den 4!!!");
        }
        //Override
        @Override
        public String displayInfo(){
            return "ID: " + id + "\nName: " + name + "\nAge: " + age + "\nGPA: " + gpa;
        }
    }
    //Class Teacher extends Person
    public static class Teacher extends Person{
        double salary;
        String subject;
        //Contructor
        public Teacher(){}
        public Teacher(String id, String name, double salary, String subject){
            setID(id);
            setName(name);
            setSalary(salary);
            setSubject(subject);
        }
        //Getter
        public String getID(){
            return id;
        }
        public String getName(){
            return name;
        }
        public double getSalary(){
            return salary;
        }
        public String getSubject(){
            return subject;
        }
        //Setter
        public void setID(String id){
            validateID(id);
            this.id = id;
        }
        public void setName(String name){
            validateName(name);
            this.name = name;
        }
        public void setSalary(double salary){
            validateSalary(salary);
            this.salary = salary;
        }
        public void setSubject(String subject){
            validateSubject(subject);
            this.subject = subject;
        }
        //Validate
        public static void validateID(String idStr){
            if(idStr == null || idStr.isEmpty()) 
                throw new IllegalArgumentException("ID khong duoc de trong!!!");
            else if(idStr.equals("GV000"))
                throw new IllegalArgumentException("GV000 khong ton tai");
            else if(!idStr.matches("^GV\\d{3}$"))
                throw new IllegalArgumentException("ID sai format! Format dung: GV001,...");
        }
        public static void validateName(String nameStr){
            if(nameStr == null || nameStr.isEmpty()) 
                throw new IllegalArgumentException("Ten khong duoc de trong!!!");
            else if(!nameStr.matches("^[a-zA-ZÀ-ỹ\\x20']+$"))
                throw new IllegalArgumentException("Ten chi bao gom chu cai, dau cach ( ) va dau nhay don (')!!!");
        }
        public static void validateSalary(double salaryDou){
            if(salaryDou <= 0) 
                throw new IllegalArgumentException("Tien luong phai lon hon 0!!!");
        }
        public static void validateSubject(String subjectStr){
            if(subjectStr == null || subjectStr.isEmpty()) 
                throw new IllegalArgumentException("Mon hoc khong duoc de trong!!!");
            else if(!subjectStr.matches("^[a-zA-ZÀ-ỹ\\x20]+$"))
                throw new IllegalArgumentException("Mon hoc chi bao gom chu cai, dau cach ( )!!!");
        }
        //Override
        @Override
        public String displayInfo(){
            return "ID: " + id + "\nHo va ten: " + name + "\nTien luong: " + salary + "\nMon hoc: " + subject;
        }
    }
    //interface Imanage<T>
    public static interface Imanage<T>{
        public void add(T t);
        public void remove(String id);
        public void findByID(String id);
        public void displayAll(); 
    }
    //Class PersonManager implements Imanage<Person>
    public static class PersonManager implements Imanage<Person>{
        ArrayList<Person> personList = new ArrayList<>();
        //@Override
        @Override
        public void add(Person p){
            if(!checkID(p.id))
                personList.add(p);
            else throw new IllegalArgumentException("Da co ID nay!!!");
        }
        @Override
        public void remove(String idStr){
            if(!checkID(idStr))
                throw new IllegalArgumentException("Khong co id nay trong danh sach!!!");
            else for(int i = 0; i < personList.size(); i++){
                if(idStr.equals(personList.get(i).id)){
                    personList.remove(i);
                    return;
                } 
            }
        }
        @Override
        public void findByID(String idStr){
            if(!checkID(idStr))
                throw new IllegalArgumentException("Khong co id nay trong danh sach!!!");
            else for(int i = 0; i < personList.size(); i++){
                if(idStr.equals(personList.get(i).id)){
                    System.out.println(personList.get(i).displayInfo());
                    return;
                } 
            }
        }
        @Override
        public void displayAll(){
            int i=1;
            if(personList == null || personList.isEmpty())
                throw new IllegalArgumentException("Khong co thong tin nao trong danh sach!!!");
            else for(Person p : personList){
                System.out.println("--------Thong tin thu " + i + "--------");
                System.out.println(p.displayInfo());
                i++;
            }
        }
        //checkID
        private boolean checkID(String idStr){
            for(Person p : personList){
                if(p.id.equals(idStr))
                    return true;
            }
            return false;
        }
    }
    //Print Menu
    public static void printMenu(){
        System.out.println("--------Chuc nang--------");
        System.out.println("1. Them Student");
        System.out.println("2. Them Teacher");
        System.out.println("3. Xoa theo ID");
        System.out.println("4. Tim theo ID");
        System.out.println("5. Hien thi tat ca");
        System.out.println("0. Thoat");
        System.out.print("Chon chuc nang so: ");
    }
    //Tiếp tục hay dừng
    public static boolean yesORno(Scanner sc){
        while(true){
            System.out.print("Co tiep tuc khong? Tra loi y/n: ");
            String answer =  sc.nextLine().toLowerCase().trim();
            switch(answer){
                case "y" -> {return true;}
                case "n" -> {return false;}
                default -> throw new IllegalArgumentException("Chi tra loi y hoặc n!!!");
            }
        }
    }
    //Main
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            PersonManager personManagers = new PersonManager();
            while(true){
                try {
                    printMenu();
                    int choose = Integer.parseInt(sc.nextLine());
                    switch(choose){
                        case 1 -> {
                            while(true){
                                try {
                                    String idStr, nameStr;
                                    int ageInt;
                                    double gpaDou;
                                    System.out.println("----Chuc Nang: Them Student----");
                                    //Nhập ID
                                    while(true){
                                        System.out.print("ID: ");
                                        try {
                                            idStr = sc.nextLine().toUpperCase().trim();
                                            Student.validateID(idStr);
                                            break;
                                        } catch(IllegalArgumentException i){
                                            System.err.println(i.getMessage());
                                        }
                                    }
                                    //Nhập họ và tên
                                    while(true){
                                        System.out.print("Ho va ten: ");
                                        try {
                                            nameStr = sc.nextLine().toUpperCase().trim();
                                            Student.validateName(nameStr);
                                            break;
                                        } catch(IllegalArgumentException i){
                                            System.err.println(i.getMessage());
                                        }
                                    }
                                    //Nhập tuổi
                                    while(true){
                                        System.out.print("Tuoi: ");
                                        try {
                                            ageInt = Integer.parseInt(sc.nextLine().trim());
                                            Student.validateAGE(ageInt);
                                            break;
                                        }  catch(NumberFormatException n){
                                            System.err.println("Tuoi la so nguyen!!!");
                                        } catch(IllegalArgumentException i){
                                            System.err.println(i.getMessage());
                                        }
                                    }
                                    //Nhập GPA
                                    while(true){
                                        System.out.print("GPA: ");
                                        try {
                                            gpaDou = Double.parseDouble(sc.nextLine().trim());
                                            Student.validateGPA(gpaDou);
                                            break;
                                        }  catch(NumberFormatException n){
                                            System.err.println("GPA phai la so!!!");
                                        } catch(IllegalArgumentException i){
                                            System.err.println(i.getMessage());
                                        }
                                    }
                                    Student st = new Student(idStr, nameStr, ageInt, gpaDou);
                                    personManagers.add(st);
                                    if(!yesORno(sc))
                                        break;
                                } catch (IllegalArgumentException i){
                                    System.err.println(i.getMessage());
                                }
                            }
                        }
                        case 2 -> {                            
                            while(true){
                                try {
                                    String idStr, nameStr, subjectStr;
                                    double salaryDou;
                                    System.out.println("----Chuc Nang: Them Teacher----");
                                    //Nhập ID
                                    while(true){
                                        System.out.print("ID: ");
                                        try {
                                            idStr = sc.nextLine().toUpperCase().trim();
                                            Teacher.validateID(idStr);
                                            break;
                                        } catch(IllegalArgumentException i){
                                            System.err.println(i.getMessage());
                                        }
                                    }
                                    //Nhập họ và tên
                                    while(true){
                                        System.out.print("Ho va ten: ");
                                        try {
                                            nameStr = sc.nextLine().toUpperCase().trim();
                                            Teacher.validateName(nameStr);
                                            break;
                                        } catch(IllegalArgumentException i){
                                            System.err.println(i.getMessage());
                                        }
                                    }
                                    //Nhập salary
                                    while(true){
                                        System.out.print("Tien luong: ");
                                        try {
                                            salaryDou = Double.parseDouble(sc.nextLine().trim());
                                            Teacher.validateSalary(salaryDou);
                                            break;
                                        }  catch(NumberFormatException n){
                                            System.err.println("Tien luong phai la so!!!");
                                        } catch(IllegalArgumentException i){
                                            System.err.println(i.getMessage());
                                        }
                                    }
                                    //Nhập subject
                                    while(true){
                                        System.out.print("Mon hoc: ");
                                        try {
                                            subjectStr = sc.nextLine().trim();
                                            Teacher.validateSubject(subjectStr);
                                            break;
                                        }  catch(IllegalArgumentException i){
                                            System.err.println(i.getMessage());
                                        }
                                    }
                                    Teacher te = new Teacher(idStr, nameStr, salaryDou, subjectStr);
                                    personManagers.add(te);
                                    if(!yesORno(sc))
                                        break;
                                } catch (IllegalArgumentException i){
                                    System.err.println(i.getMessage());
                                }
                            }
                        }
                        case 3 -> {
                            while(true){
                                try {
                                    System.out.println("----Chuc Nang: Xoa theo ID----");
                                    System.out.print("Nhap ID: ");
                                    String idStr = sc.nextLine().trim().toUpperCase();
                                    personManagers.remove(idStr);
                                    if(!yesORno(sc))
                                        break;
                                } catch(IllegalArgumentException i){
                                    System.out.println(i.getMessage());
                                }
                            }
                        }
                        case 4 -> {
                            while(true){
                                try {
                                    System.out.println("----Chuc Nang: Tim theo ID----");
                                    System.out.print("Nhap ID: ");
                                    String idStr = sc.nextLine().trim().toUpperCase();
                                    personManagers.findByID(idStr);
                                    if(!yesORno(sc))
                                        break;
                                } catch(IllegalArgumentException i){
                                    System.out.println(i.getMessage());
                                }
                            }
                        }
                        case 5 -> {
                            try {
                                System.out.println("----Chuc Nang: Hien thi tat ca----");
                                personManagers.displayAll();
                            } catch(IllegalArgumentException i){
                                System.out.println(i.getMessage());
                            }
                        }
                        case 0 -> {return;}
                        default -> throw new NumberFormatException("Nhap so tu 0 den 5!!!");
                    }
                } catch(NumberFormatException n){
                    System.err.println(n.getMessage());
                }catch(IllegalArgumentException i){
                    System.err.println(i.getMessage());
                }
            }
        }
    }
}