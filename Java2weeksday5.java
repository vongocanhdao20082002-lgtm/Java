/*
Day 5: Collections + Stream cơ bản 

1. List(ArrayList)
    - Là collection có thứ tự.
    - Được phép trùng (value).
    - Lấy value theo index(thứ tự). 
    - Collection là interface gốc (cha) của hầu hết các cấu trúc dữ liệu trong Java.
    - Các loại List phổ biến:
        + ArrayList: .get() nhanh.
        + LinkedList: giống ArrayList nhưng cấu trúc linked list (thêm/xóa nhanh hơn, truy cập chậm hơn).

    - Công thức:
        + Khởi tạo: List<String> list = new ArrayList<>();
        + Thêm phần tử: list.add("A");
        + Lấy phần tử: list.get(0); // "A"
        + Sua phần tử: list.set(0, "Z"); // sửa index 0
        + Xóa: 
                list.remove(0); // xóa theo index
                list.remove("A"); // xóa theo giá trị
        + Kích thước: list.size();
        + Kiểm tra tồn tại: 
            list.contains("A"); // true / false

2. Map(HashMap)
    - Là một tập hợp các cặp Key - Value.
    - Không được trùng Key.
    - Lấy data theo Key.
    - Các loại Map phổ biến:
        + HashMap: không có thứ tự
        + LinkedHashMap: sắp xếp theo thứ tự thêm vào
        + TreeMap: sắp xếp theo Key

    - Công thức:
        + Khởi tạo: Map<Key, Value> map = new HashMap<>();
        + Thêm data: map.put("A", 1);
        + Lấy data: map.get("A"); // 1
        + Xóa theo Key: map.remove("A"); 
        + Xóa tất cả: map.clear();
        + Kiểm tra tồn tại: 
            map.containsKey("A"); //true
            map.containsValue(5); //false

3. Stream(filter, forEach)
    - Là pipeline xử lý dữ liệu (không chỉ List).
    - Công thức theo thứ tự:
        + Biến list thành dòng xử lý: list.stream()
        + Lọc theo điều kiện: .filter(s -> s.getAge() > 20) //chỉ giữ lại những phần tử thoả điều kiện
        + Duyệt và xử lý list: .forEach(s -> System.out.println(s.getName())); //in name của phần tử ra ngoài
        + Transform dữ liệu: .map()
        + Đếm: .count()
*/
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
/*
Bài tập: Quản lý sản phẩm
Yêu cầu:
    1. Tạo danh sách sản phẩm (id, name, price, category).
    2. Lưu danh sách bằng List.
    3. Tạo thêm Map để:
        - Key: category
        - Value: danh sách sản phẩm thuộc category đó
    4. Dùng Stream để:
        - Lọc sản phẩm có price > 100
        - Lọc sản phẩm thuộc 1 category bất kỳ
        - In ra tên tất cả sản phẩm (chỉ name)
        - Đếm số sản phẩm có price > 200
Mục tiêu
    - Dùng được List + Map cùng lúc
    - Biết filter dữ liệu bằng Stream
    - Làm quen map() và count()
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class Java2weeksday5{
    //class product
    public static class product{
        //Khai báo id, name, price, category
        private String id, name, category;
        private double price;
        //Contructor
        public product(){}
        public product(String id, String name, double price, String category){
            setID(id);
            setName(name);
            setPrice(price);
            setCategory(category);
        }
        //Getter
        public String getID(){return id;}
        public String getName(){return name;}
        public double getPrice(){return price;}
        public String getCategory(){return category;}
        //Setter
        public void setID(String id){
            validateID(id);
            this.id = id;
        }
        public void setName(String name){
            validateName(name);
            this.name = name;
        }
        public void setPrice(double price){
            validatePrice(price);
            this.price = price;
        }
        public void setCategory(String category){
            validateCategory(category);
            this.category = category;
        }
        //Validate public
        //id: PD0001,...
        public void validateID(String idStr){
            if(idStr == null || idStr.isEmpty())
                throw new IllegalArgumentException("Khong duoc de trong!!!");
            else if(idStr.equals("PD0000"))
                throw new IllegalArgumentException("Khong ton tai ID PD0000!!!");
            else if(!idStr.matches("^PD\\d{4}$"))
                throw new IllegalArgumentException("Sai format! ID bat dau bang PD va ket thuc bang 4 chu so!!!");
        }
        //name gồm chữ cái, dấu cách và '
        public void validateName(String nameStr){
            if(nameStr == null || nameStr.isEmpty())
                throw new IllegalArgumentException("Khong duoc de trong!!!");
            else if(!nameStr.matches("^[A-Za-zÀ-ỹ ']+$"))
                throw new IllegalArgumentException("Sai format! Ten chi bao gom chu cai, dau cach( ) va dau nhay don(')!!!");
        }
        //price > 0
        public void validatePrice(double priceDou){
            if(priceDou <= 0)
                throw new IllegalArgumentException("Gia phai lon hon 0!!!");
        }
        //category gồm chữ cái và dấu cách
        public void validateCategory(String categoryStr){
            if(categoryStr == null || categoryStr.isEmpty())
                throw new IllegalArgumentException("Khong duoc de trong!!!");
            else if(!categoryStr.matches("^[A-Za-zÀ-ỹ ]+$"))
                throw new IllegalArgumentException("Sai format! Loai san pham chi bao gom chu cai va dau cach( )!!!");
        }
        //ShowInformationProduct
        public String ShowInformationProduct(){
            String priceStr = String.format("%.2f", price);
            return "ID san pham: " + id +"\nTen san pham: " + name + "\nGia san pham: " + priceStr + "\nLoai san pham: " + category;
        }
    }
    //Check
    public static boolean checkID(List<product> productList, String idStr){
        for(product pd : productList)
            if(pd.getID().equals(idStr))
                return true;
        return false;
    }
    //menuFunction
    public static void menuFunction(){
        System.out.println("--------Menu Function--------");
        System.out.println("1. Them san pham moi");
        System.out.println("2. Xoa san pham");
        System.out.println("3. Sua san pham");
        System.out.println("4. Hien thi thong tin san pham");
        System.out.println("5. Bo loc san pham");
        System.out.println("0. Thoat");
        System.out.print("Chon chuc nang so: ");
    } 
    //menuFunctionFilterProduct
    public static void menuFunctionFilterProduct(){
        System.out.println("----Menu Function: Bo Loc San Pham----");
        System.out.println("1. Loc san pham co gia > 100");
        System.out.println("2. Loc san pham thuoc 1 loai san pham bat ky");
        System.out.println("3. In ra ten tat ca san pham");
        System.out.println("4. Dem so san pham co gia > 200");
        System.out.println("0. Thoat");
        System.out.print("Chon chuc nang so: ");
    } 
    //main
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            List<product> productList = new ArrayList<>();
            //Key: category - Value: danh sách sản phẩm thuộc category đó
            Map<String, List<product>> categoryMap = new HashMap<>();
            //Khai báo
            String idStr, nameStr, categoryStr; 
            double priceDou;
            while(true){
                try{
                    menuFunction();
                    int chooseMenuFunction = Integer.parseInt(sc.nextLine());
                    switch(chooseMenuFunction){
                        case 1 -> {
                            System.out.println("1. Them san pham moi");
                            product products = new product();
                            //Nhập
                            while(true){
                                try {
                                    System.out.print("ID san pham: ");
                                    idStr = sc.nextLine().trim().toUpperCase();
                                    products.validateID(idStr);
                                    if(checkID(productList, idStr) == true)
                                        throw new IllegalArgumentException("Da co ID " + idStr + " trong danh sach!!!");
                                    else break;
                                }catch(IllegalArgumentException i){
                                    System.err.println(i.getMessage());
                                }
                            }
                            while(true){
                                try {
                                    System.out.print("Ten san pham: ");
                                    nameStr = sc.nextLine().trim();
                                    products.validateName(nameStr);
                                    break;
                                }catch(IllegalArgumentException i){
                                    System.err.println(i.getMessage());
                                }
                            }
                            while(true){
                                try {
                                    System.out.print("Gia san pham: ");
                                    priceDou = Double.parseDouble(sc.nextLine().trim());
                                    products.validatePrice(priceDou);
                                    break;
                                }catch(NumberFormatException n){
                                    System.err.println("Phai nhap so!!!");
                                }catch(IllegalArgumentException i){
                                    System.err.println(i.getMessage());
                                }
                            }
                            while(true){
                                try {
                                    System.out.print("Loai san pham: ");
                                    categoryStr = sc.nextLine().trim();
                                    products.validateCategory(categoryStr);
                                    break;
                                }catch(IllegalArgumentException i){
                                    System.err.println(i.getMessage());
                                }
                            }
                            //Xem thông tin trước khi lưu
                            System.out.println("--------Thong tin san pham--------");
                            System.out.printf("ID san pham: " + idStr +"\nTen san pham: " + nameStr + "\nGia san pham: %.2f \nLoai san pham: " + categoryStr + "\n",priceDou);
                            //Lưu
                            products = new product(idStr, nameStr, priceDou, categoryStr);
                            productList.add(products);
                            if(!categoryMap.containsKey(categoryStr)){
                                List<product> list = new ArrayList<>();
                                list.add(products);
                                categoryMap.put(categoryStr, list);
                            }else categoryMap.get(categoryStr).add(products);
                            System.out.println("Da luu.");
                        }
                        case 2 -> {
                            System.out.println("2. Xoa san pham");
                            if(productList.isEmpty())
                                System.out.println("Khong co san pham nao trong danh sach.");
                            else{
                                product products = new product();
                                //Nhập
                                while(true){
                                    try {
                                        System.out.print("ID san pham: ");
                                        idStr = sc.nextLine().trim().toUpperCase();
                                        products.validateID(idStr);
                                        if(checkID(productList, idStr) == false)
                                            throw new IllegalArgumentException("Chua co ID " + idStr + " trong danh sach!!!");
                                        else break;
                                    }catch(IllegalArgumentException i){
                                        System.err.println(i.getMessage());
                                    }
                                }
                                //Xóa
                                for(int i = 0; i < productList.size(); i++)
                                    if(productList.get(i).getID().equals(idStr)){
                                        //Lấy category, list product của category cần xóa
                                        categoryStr = productList.get(i).getCategory();
                                        List<product> categoryList = categoryMap.get(categoryStr);
                                        //Xóa product trong list category
                                        categoryList.remove(productList.get(i));
                                        //Nếu size hết thì xóa luôn Key category đó
                                        if(categoryList.size() == 0)
                                            categoryMap.remove(categoryStr);
                                        productList.remove(i);
                                        System.out.println("Da xoa.");
                                        break;
                                    }
                            }
                        }
                        case 3 -> {
                            System.out.println("3. Sua san pham");
                            if(productList.isEmpty())
                                System.out.println("Khong co san pham nao trong danh sach.");
                            else{
                                product products = new product();
                                //Nhập
                                while(true){
                                    try {
                                        System.out.print("ID san pham: ");
                                        idStr = sc.nextLine().trim().toUpperCase();
                                        products.validateID(idStr);
                                        if(checkID(productList, idStr) == false)
                                            throw new IllegalArgumentException("Chua co ID " + idStr + " trong danh sach!!!");
                                        else break;
                                    }catch(IllegalArgumentException i){
                                        System.err.println(i.getMessage());
                                    }
                                }
                                //Xem thông tin trước khi sửa
                                System.out.println("----Thong tin ban dau cua san pham----");
                                for(product pd : productList)
                                    if(pd.getID().equals(idStr)){
                                        System.out.println(pd.ShowInformationProduct());
                                        break;
                                    }
                                //Thông tin cần sửa
                                System.out.println("----Thong tin san pham can sua----");
                                while(true){
                                    try {
                                        System.out.print("Ten san pham: ");
                                        nameStr = sc.nextLine().trim();
                                        products.validateName(nameStr);
                                        break;
                                    }catch(IllegalArgumentException i){
                                        System.err.println(i.getMessage());
                                    }
                                }
                                while(true){
                                    try {
                                        System.out.print("Gia san pham: ");
                                        priceDou = Double.parseDouble(sc.nextLine().trim());
                                        products.validatePrice(priceDou);
                                        break;
                                    }catch(NumberFormatException n){
                                        System.err.println("Phai nhap so!!!");
                                    }catch(IllegalArgumentException i){
                                        System.err.println(i.getMessage());
                                    }
                                }
                                while(true){
                                    try {
                                        System.out.print("Loai san pham: ");
                                        categoryStr = sc.nextLine().trim();
                                        products.validateCategory(categoryStr);
                                        break;
                                    }catch(IllegalArgumentException i){
                                        System.err.println(i.getMessage());
                                    }
                                }
                                //Xem thông tin sau khi sửa
                                System.out.println("----Thong tin san pham sau khi sua----");
                                System.out.printf("ID san pham: " + idStr +"\nTen san pham: " + nameStr + "\nGia san pham: %.2f \nLoai san pham: " + categoryStr + "\n",priceDou);
                                //Sửa
                                for(int i = 0; i < productList.size(); i++){
                                    if(productList.get(i).getID().equals(idStr)){
                                        // Lấy product cũ
                                        product oldProduct = productList.get(i);
                                        // Xóa khỏi category cũ
                                        String oldCategory = oldProduct.getCategory();
                                        List<product> oldList = categoryMap.get(oldCategory);
                                        oldList.remove(oldProduct);
                                        if(oldList.size() == 0)
                                            categoryMap.remove(oldCategory);
                                        // Tạo product mới
                                        product newProduct = new product(idStr, nameStr, priceDou, categoryStr);
                                        // Cập nhật vào productList
                                        productList.set(i, newProduct);
                                        // Thêm vào category mới
                                        if(!categoryMap.containsKey(categoryStr)){
                                            List<product> newList = new ArrayList<>();
                                            newList.add(newProduct);
                                            categoryMap.put(categoryStr, newList);
                                        } else {
                                            categoryMap.get(categoryStr).add(newProduct);
                                        }
                                        System.out.println("Da sua.");
                                        break;
                                    }
                                } 
                            }
                        }
                        case 4 -> {
                            System.out.println("4. Hien thi thong tin san pham");
                            int i = 1;
                            if(productList.isEmpty())
                                System.out.println("Khong co san pham nao trong danh sach.");
                            else for(product pd : productList){
                                System.out.println("----Thong tin san pham thu " + i + "----");
                                System.out.println(pd.ShowInformationProduct());
                                i++;
                            }
                        }
                        case 5 -> {
                            boolean functionFilterProduct = true;
                            while(functionFilterProduct){
                                try{
                                    menuFunctionFilterProduct();
                                    int chooseMenuFunctionFilterProduct = Integer.parseInt(sc.nextLine());
                                    switch(chooseMenuFunctionFilterProduct){
                                        case 1 -> {
                                            System.out.println("1. Loc san pham co gia > 100");
                                            if(productList.isEmpty())
                                                System.out.println("Khong co san pham nao trong danh sach.");
                                            else{
                                                List<product> price100List = productList.stream().filter(p -> p.getPrice() > 100).toList();
                                                if(price100List.isEmpty())
                                                    System.out.println("Khong co san pham nao co gia > 100.");
                                                else price100List.forEach(p -> System.out.println(p.ShowInformationProduct() + "\n"));
                                            } 
                                        }
                                        case 2 -> {
                                            System.out.println("2. Loc san pham thuoc 1 loai san pham bat ky");
                                            if(productList.isEmpty())
                                                System.out.println("Khong co san pham nao trong danh sach.");
                                            else{
                                                product products = new product();
                                                //Nhập
                                                while(true){
                                                    try {
                                                        System.out.print("Loai san pham: ");
                                                        categoryStr = sc.nextLine().trim();
                                                        products.validateCategory(categoryStr);
                                                        break;
                                                    }catch(IllegalArgumentException i){
                                                        System.err.println(i.getMessage());
                                                    }
                                                }
                                                //Lọc
                                                if(!categoryMap.containsKey(categoryStr))
                                                    System.out.println("Khong co loai san pham do.");
                                                else{
                                                    List<product> list = categoryMap.get(categoryStr);
                                                    if(list.isEmpty())
                                                        System.out.println("Loai nay hien tai khong co san pham.");
                                                    else
                                                        list.forEach(p -> System.out.println(p.ShowInformationProduct() + "\n"));
                                                }
                                            }
                                        }
                                        case 3 -> {
                                            System.out.println("3. In ra ten tat ca san pham");
                                            if(productList.isEmpty())
                                                System.out.println("Khong co san pham nao trong danh sach.");
                                            else productList.stream().map(product::getName).forEach(System.out::println);
                                        }
                                        case 4 -> {
                                            System.out.println("4. Dem so san pham co gia > 200");
                                            if(productList.isEmpty())
                                                System.out.println("Khong co san pham nao trong danh sach.");
                                            else System.out.println("Co " + productList.stream().filter(p -> p.getPrice() > 200).count() + " san pham co gia > 200.");
                                        }
                                        case 0 -> {functionFilterProduct = false;}
                                        default -> throw new IllegalArgumentException("Chi nhap so tu 0 den 4!!!");
                                    }
                                }
                                catch(NumberFormatException n){
                                    System.err.println("Phai nhap so!!!");
                                }catch(IllegalArgumentException i){
                                    System.err.println(i.getMessage());
                                }
                            }
                        }
                        case 0 -> {return;}
                        default -> throw new IllegalArgumentException("Chi nhap so tu 0 den 5!!!");
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