
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
            if(idStr.isEmpty() || idStr == null) 
                throw new IllegalArgumentException("ID khong duoc de trong!!!");
            else if(idStr.equals("ID000"))
                throw new IllegalArgumentException("ID000 khong ton tai");
            else if(!idStr.matches("^ID\\d{3}$"))
                throw new IllegalArgumentException("ID sai format! Format dung: ID001,...");
        }
        public static void validateName(String nameStr){
            if(nameStr.isEmpty() || nameStr == null) 
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
            if(idStr.isEmpty() || idStr == null) 
                throw new IllegalArgumentException("ID khong duoc de trong!!!");
            else if(idStr.equals("GV000"))
                throw new IllegalArgumentException("GV000 khong ton tai");
            else if(!idStr.matches("^GV//d{3}$"))
                throw new IllegalArgumentException("ID sai format! Format dung: GV001,...");
        }
        public static void validateName(String nameStr){
            if(nameStr.isEmpty() || nameStr == null) 
                throw new IllegalArgumentException("Ten khong duoc de trong!!!");
            else if(!nameStr.matches("^[a-zA-ZÀ-ỹ\\x20']+$"))
                throw new IllegalArgumentException("Ten chi bao gom chu cai, dau cach ( ) va dau nhay don (')!!!");
        }
        public static void validateSalary(double salaryDou){
            if(salaryDou <= 0) 
                throw new IllegalArgumentException("Tien luong phai lon hon 0!!!");
        }
        public static void validateSubject(String subjectStr){
            if(subjectStr.isEmpty() || subjectStr == null) 
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
        public void add(T t, String id);
        public void remove(String id);
        public void findByID(String id);
        public void displayAll(); 
    }
    //Class PersonManager implements Imanage<Person>
    public static class PersonManager implements Imanage<Person>{
        ArrayList<Person> personList = new ArrayList<>();
        //@Override
        @Override
        public void add(Person p, String idStr){
            if(!checkID(p, idStr))
                personList.add(p);
            else throw new IllegalArgumentException("Da co ID nay!!!");
        }
        @Override
        public void remove(String id){
            personList.remove(id);
        }
        @Override
        public void findByID(String id){}
        @Override
        public void displayAll(){}
        //checkID
        private boolean checkID(Person p, String idStr){
            return p!=null && p.id.equals(idStr);
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
                                    System.out.println("----Chuc Nang: Them Student----");
                                    while(true){
                                        System.out.print("ID: ");
                                        try {
                                            String idStr = sc.nextLine();
                                            Student.validateID(idStr);
                                            break;
                                        } catch(IllegalArgumentException i){
                                            System.err.println(i.getMessage());
                                        }
                                    }
                                    System.out.print("Ho va ten: ");
                                    String nameStr = sc.nextLine();
                                    System.out.print("Tuoi: ");
                                    int ageInt = Integer.parseInt(sc.nextLine());
                                    System.out.print("GPA: ");
                                    double gpaDou = Double.parseDouble(sc.nextLine());
                                    //Student st = new Student(idStr, nameStr, ageInt, gpaDou);
                                    //personManagers.add(st, idStr);
                                    if(!yesORno(sc))
                                        break;
                                } catch (IllegalArgumentException i) {
                                    System.err.println(i.getMessage());
                                }
                            }
                        }
                        case 2 -> {
                            while(true){
                                System.out.println("----Chuc Nang: Them Student----");
                                System.out.print("ID: ");
                                System.out.print("Ho va ten: ");
                                System.out.print("Tien luong: ");
                                System.out.print("Mon hoc: ");
                                if(!yesORno(sc))
                                    break;
                            }
                        }
                        case 3 -> {
                            while(true){
                                System.out.println("----Chuc Nang: Them Student----");
                                if(!yesORno(sc))
                                    break;
                            }
                        }
                        case 4 -> {
                            while(true){
                                System.out.println("----Chuc Nang: Them Student----");
                                if(!yesORno(sc))
                                    break;
                            }
                        }
                        case 5 -> {
                            while(true){
                                System.out.println("----Chuc Nang: Them Student----");
                                if(!yesORno(sc))
                                    break;
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