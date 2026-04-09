import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Java2weeksday3 {
    //Class Person
    public static class Person{
        //Thông tin chi tiết
        private String name;
        private int age;
        //Contructor 
        public Person(String name, int age){
            setName(name);
            setAge(age);
        }
        //Contructor mặc định
        public Person(){}
        //Getter
        public String getName(){
            return name;
        }
        public int getAge(){
            return age;
        }
        //Setter
        public void setName(String name){
            checkName(name);
            this.name = name;
        }
        public void setAge(int age){
            checkAge(age);
            this.age = age;
        }
        //Overide toString
        @Override
        public String toString(){
            return "Ten: " + name + "\nTuoi: " + age;
        }
        //Check
        public void checkName(String name){
            if(name.isEmpty())
                throw new IllegalArgumentException("Khong duoc de trong!!!");
            else
                for(Character a : name.toCharArray())
                    if(Character.isDigit(a))
                        throw new IllegalArgumentException("Trong ten khong co so!!!");
        }
        public void checkAge(int age){
            if(age <= 0)
                throw new IllegalArgumentException("Tuoi phai lon hon 0!!!");
        }
        //Input Person
        public void inputPerson(Scanner sc){
            System.out.println("--------Nhap thong tin--------");
            //Input name
            while(true){
                try{
                    System.out.print("Nhap ten: ");
                    String nameStr = sc.nextLine().trim();
                    setName(nameStr);
                    break;
                } catch(IllegalArgumentException e){
                    System.err.println(e.getMessage());
                }
            }
            //Input age
            while(true){
                try{
                    System.out.print("Nhap tuoi: ");
                    int ageInt = Integer.parseInt(sc.nextLine().trim());
                    setAge(ageInt);
                    break;
                } catch(NumberFormatException e){
                    System.err.println("Tuoi la so nguyen!!!");
                }catch(IllegalArgumentException e){
                    System.err.println(e.getMessage());
                }
            }
        }
    }
    //Class Student kế thừa Person
    public static class Student extends Person{
        private String mssv;
        private double gpa;
        //Constructor
        public Student(String name, int age, String mssv, double gpa){
            super(name,age);
            setMssv(mssv);
            setGPA(gpa);
        }
        //Contructor mặc định
        public Student(){super();}
        //Getter
        public String getMssv(){
            return mssv;
        }
        public double getGPA(){
            return gpa;
        }
        //Setter
        public void setMssv(String mssv){
            checkMssv(mssv);
            this.mssv = mssv;
        }
        public void setGPA(double gpa){
            checkGPA(gpa);
            this.gpa = gpa;
        }
        //Overide toString
        @Override
        public String toString(){
            return super.toString() + "\nMssv: " + mssv + "\nGPA: " + gpa + "\n";
        }
        //Check
        public static void checkMssv(String mssv){
            if(mssv.isEmpty())
                throw new IllegalArgumentException("Khong duoc de trong!!!");
            else if(!mssv.startsWith("SV"))
                throw new IllegalArgumentException("Mssv phai bat dau bang SV!!!");
            else if(mssv.length() != 5)
                throw new IllegalArgumentException("Mssv co 5 ky tu!!!");
            else if(mssv.equals("SV000"))
                throw new IllegalArgumentException("SV000 khong hop le!!!");
            else{
                char[] mssvChar = mssv.toCharArray();
                for(int i=2; i < mssv.length(); i++)
                    if(!Character.isDigit(mssvChar[i]))
                        throw new IllegalArgumentException("Mssv phai có 3 ky tu cuoi la so!!!");
            }   
        }
        public static void checkGPA(double gpa){
            if(gpa < 0 || gpa > 4)
                throw new IllegalArgumentException("GPA tu 0 den 4!!!");
        }
        //Input Student
        public void inputStudent(Scanner sc){
            super.inputPerson(sc);
            //Input mssv
            while(true){
                try{
                    System.out.print("Nhap mssv: ");
                    String mssvStr = sc.nextLine().trim().toUpperCase();
                    setMssv(mssvStr);
                    break;
                } catch(IllegalArgumentException e){
                    System.err.println(e.getMessage());
                }
            }
            //Input gpa
            while(true){
                try{
                    System.out.print("Nhap GPA: ");
                    double gpaDou = Double.parseDouble(sc.nextLine().trim());
                    setGPA(gpaDou);
                    break;
                } catch(NumberFormatException e){
                    System.err.println("GPA phai la so!!!");
                }catch(IllegalArgumentException e){
                    System.err.println(e.getMessage());
                }
            }
        }
    }
    //Class Teacher kế thừa Person
    public static class Teacher extends Person{
        private double salary;
        private String subject;
        //Constructor
        public Teacher(String name, int age, double salary, String subject){
            super(name, age);
            setSalary(salary);
            setSubject(subject);
        }
        //Contructor mặc định
        public Teacher(){super();}
        //Getter
        public double getSalary(){
            return salary;
        }
        public String getSubject(){
            return subject;
        }
        //Setter
        public void setSalary(double salary){
            checkSalary(salary);
            this.salary = salary;
        }
        public void setSubject(String subject){
            checkSubject(subject);
            this.subject = subject;
        }
        //Overide toString
        @Override
        public String toString(){
            return super.toString() + "\nSalary: " + salary + "\nSubject: " + subject + "\n";
        }
        //Check
        private static void checkSalary(double salary){
            if(salary <= 0)
                throw new IllegalArgumentException("Tien luong phai lon hon 0!!!");
        }
        public void checkSubject(String subject){
            super.checkName(subject);
        }
        //Input Teacher
        public void inputTeacher(Scanner sc){
            super.inputPerson(sc);
            //Input salary
            while(true){
                try{
                    System.out.print("Nhap tien luong: ");
                    double salaryDou = Double.parseDouble(sc.nextLine().trim());
                    setSalary(salaryDou);
                    break;
                } catch(NumberFormatException e){
                    System.err.println("Tien luong phai la so!!!");
                } catch(IllegalArgumentException e){
                    System.err.println(e.getMessage());
                }
            }
            //Input subject
            while(true){
                try{
                    System.out.print("Nhap mon hoc: ");
                    String subjectStr = sc.nextLine().trim();
                    setSubject(subjectStr);
                    break;
                }catch(IllegalArgumentException e){
                    System.err.println(e.getMessage());
                }
            }
        }
    }
    //Output Person
    public static void outputPerson(ArrayList<Person> p){
        if(p.isEmpty())
            System.out.println("Hien chua co thong tin nao.");
        else{
            int i = 1;
            //for(kieudulieu tenbien : tendulieuthamchieu)
            for(Person persons : p){
                System.out.printf("--------Thong tin ca nhan %d--------%n",i);
                System.out.println(persons.toString());
                i++;
            }
            
        }
    }
    //Output Student
    public static void outputStudent(ArrayList<Person> p){
        List<Student> student = p.stream().filter(s -> s instanceof Student).map(s -> (Student) s).toList();
        if(student.isEmpty())
            System.out.println("Hien chua co thong tin nao.");
        else{
            int i = 1;
            //for(kieudulieu tenbien : tendulieuthamchieu)
            for(Student st : student){
                System.out.printf("--------Thong tin sinh vien %d--------%n",i);
                System.out.println(st.toString());
                i++;
            }
            
        }
    }
    //Output Teacher
    public static void outputTeacher(ArrayList<Person> p){
        List<Teacher> teacher = p.stream().filter(t -> t instanceof Teacher).map(t -> (Teacher)t).toList();
        if(teacher.isEmpty())
            System.out.println("Hien chua co thong tin nao.");
        else{
            int i = 1;
            //for(kieudulieu tenbien : tendulieuthamchieu)
            for(Teacher te : teacher){
                System.out.printf("--------Thong tin giao vien %d--------%n",i);
                System.out.println(te.toString());
                i++;
            }
            
        }
    }
    //Print Menu
    public static void printMenu(){
        System.out.println("--------Menu--------");
        System.out.println("1. Nhap sinh vien.");
        System.out.println("2. Nhap giao vien.");
        System.out.println("3. Hien danh sach sinh vien.");
        System.out.println("4. Hien danh sach giao vien.");
        System.out.println("5. Hien danh sach tat ca.");
        System.out.println("0. Thoat.");
        System.out.print("Chon chuc nang so: ");
    }
    //Tiếp tục hay dừng
    public static Boolean yesORno(Scanner sc){
        while(true){
            System.out.print("Tiep tuc khong? Tra loi (Y/N): ");
            String answer = sc.nextLine().toUpperCase().trim();
            switch(answer){
                case "Y" -> {return true;}
                case "N" -> {return false;}
                default -> throw new IllegalArgumentException("Tra loi Y hoac N!!!");
            }
        }
    }
    //Main
    public static void main(String[] args){
        try(Scanner sc = new Scanner(System.in)){
            ArrayList<Person> person = new ArrayList<>();
            while(true){
                try{
                    printMenu();
                    int choose = Integer.parseInt(sc.nextLine());
                    switch(choose){
                        case 1 -> {
                            while(true){
                                Student st = new Student();
                                st.inputStudent(sc);
                                person.add(st);
                                if(!yesORno(sc))
                                    break;
                            }
                        }
                        case 2 -> {
                            while(true){
                                Teacher te = new Teacher();
                                te.inputTeacher(sc);
                                person.add(te);
                                if(!yesORno(sc))
                                    break;
                            }
                        }
                        case 3 -> {
                            outputStudent(person);
                        }
                        case 4 -> {
                            outputTeacher(person);
                        }
                        case 5 -> {
                            outputPerson(person);
                        }
                        case 0 -> {return;}
                        default -> throw new IllegalArgumentException("Chon chuc nang tu 0 den 5!!!");
                    }
                } catch(NumberFormatException e){
                    System.err.println("Nhap so!!!");
                } catch(IllegalArgumentException e){
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}