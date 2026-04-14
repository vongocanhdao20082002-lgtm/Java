import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class Java2weeksday5 {
    //class Student
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
        public void setAGE(int age){
            validateAGE(age);
            this.age = age;
        }
        //validate
        private void validateID(String idStr){
            if(idStr == null || idStr.isEmpty())
                throw new IllegalArgumentException("Khong duoc de trong!!!");
            else if(idStr.equals("SV000"))
                throw new IllegalArgumentException("ID SV000 khong ton tai!!!");
            else if(!idStr.matches("^SV\\d{3}$"))
                throw new IllegalArgumentException("ID bat dau bang SV, ket thuc bang 3 chu so!!!");
        }
        private void validateName(String nameStr){
            if(nameStr == null || nameStr.isEmpty())
                throw new IllegalArgumentException("Khong duoc de trong!!!");
            else if(!nameStr.matches("^[A-Za-zÁ-ỹ ']+$"))
                throw new IllegalArgumentException("Ten chi co chu cai, dau cach ( ) va dau nhay don (')!!!");
        }
        private void validateAGE(int ageInt){
            if(ageInt < 18)
                throw new IllegalArgumentException("Tuoi phai lon hon hoac bang 18!!!");
        }
        //Hienthi
        public String showInfo(){
            return "ID: " + id + "\nName: " + name + "\nTuoi: " + age;
        }
    }
    //Tiep tuc?
    public static boolean yesORno(Scanner sc){
        while(true){
            System.out.print("Tiep tuc hay dung? Tra loi (y/n): ");
            String answer = sc.nextLine().toLowerCase().trim();
            switch(answer){
                case "y" -> {return true;}
                case "n" -> {return false;}
                default -> System.out.println("Chi nhap y hoac n!!!");
            }
        }
    } 
    //printFunction
    public static void printFunction(){
        System.out.println("----------Menu Chuc Nang----------");
        System.out.println("1. Them nhieu sinh vien vao danh sach");
        System.out.println("2. Hien thi toan bo danh sach");
        System.out.println("3. Tra cuu thong tin theo id");
        System.out.println("4. Loc sinh vien tuoi > 20 bang Stream");
        System.out.println("0. Thoat");
        System.out.print("Chon chuc nang so: ");
    }
    //main
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            ArrayList<Student> students = new ArrayList<>();
            Map<String, List<Object>> studentMap = new HashMap<>();
            while(true){
                try{
                    printFunction();
                    int choose = Integer.parseInt(sc.nextLine());
                    switch(choose){
                        case 1 -> {
                            while(true){
                                Student st = new Student();
                                String idStr, nameStr;
                                int ageInt;
                                System.out.println("1. Them nhieu sinh vien vao danh sach");
                                //ID
                                while(true){
                                    try {
                                        System.out.print("Nhap ID: ");
                                        idStr = sc.nextLine().toUpperCase().trim();
                                        st.setID(idStr);
                                        break;
                                    }catch(IllegalArgumentException i){
                                        System.err.println(i.getMessage());
                                    }
                                }
                                //Name
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
                                //AGE
                                while(true){
                                    try {
                                        System.out.print("Nhap tuoi: ");
                                        ageInt = Integer.parseInt(sc.nextLine().trim());
                                        st.setAGE(ageInt);
                                        break;
                                    }catch(NumberFormatException n){
                                        System.err.println("Phai nhap so!!!");
                                    }catch(IllegalArgumentException i){
                                        System.err.println(i.getMessage());
                                    }
                                }
                                //Lưu
                                students.add(st);
                                System.out.println("Da luu!");
                                //Thêm vào hashmap studentMap
                                studentMap.put(idStr, Arrays.asList(nameStr,ageInt));
                                //Tiếp tục?
                                if(!yesORno(sc))
                                    break;
                            }
                        }
                        case 2 -> {
                            System.out.println("2. Hien thi toan bo danh sach");
                            int i = 1;
                            if(students.isEmpty())
                                System.out.println("Khong co sinh vien nao trong danh sach!");
                            else for(Student st : students){
                                System.out.println("----Sinh vien " + i + "----");
                                System.out.println(st.showInfo());
                                i++;
                            } 
                        }
                        case 3 -> {
                            while(true){
                                try {
                                    System.out.println("3. Tra cuu thong tin theo id");
                                    System.out.print("Nhap id can tra cuu: ");
                                    String idStr = sc.nextLine().toUpperCase().trim();
                                    if(idStr == null || idStr.isEmpty())
                                        throw new IllegalArgumentException("Phai nhap id!!!");
                                    else if(studentMap.get(idStr) == null)
                                        System.out.println("Khong co ID " + idStr + " trong danh sach!");
                                    else{
                                        System.out.println("Thong tin ca nhan " + idStr + ": ");
                                        String text = "Ho va ten: " + studentMap.get(idStr).get(0);
                                        text += "\nTuoi: " + studentMap.get(idStr).get(1);
                                        System.out.println(text);
                                    }
                                    //Tiếp tục?
                                    if(!yesORno(sc))
                                        break;
                                }catch(IllegalArgumentException i){
                                    System.err.println(i.getMessage());
                                }
                            }
                        }
                        case 4 ->  {
                            System.out.println("4. Loc sinh vien tuoi > 20 bang Stream");
                            if(students.isEmpty())
                                System.out.println("Khong co sinh vien nao trong danh sach!");
                            else{
                                students.stream().filter(s -> (int) s.getAGE() > 20)
                                .forEach(s -> System.out.println("ID: " + s.getID() + "\nHo va ten: " + s.getName() + "\nTuoi: " + s.getAGE()));
                            }
                        }
                        case 0 -> {return;}
                        default -> throw new IllegalArgumentException("Chi nhap tu 0 den 4!!!");
                    }
                }catch(NumberFormatException n){
                    System.err.println("Phai nhap so!!!");
                }catch(IllegalArgumentException i){
                    System.err.println(i.getMessage());
                }
            }
        }
    }
}
