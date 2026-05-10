//Tạo nơi lưu trữ students
const students = [];
//Lấy bảng chứa dữ liệu
const create_table = document.getElementById("table-students");
//Mở web
window.onload = function(){
    //Tạo header
    let header_row = create_table.insertRow();
    header_row.insertCell().innerHTML = `<b>STT</b>`;
    header_row.insertCell().innerHTML = `<b>NAME</b>`;
    header_row.insertCell().innerHTML = `<b>AGE</b>`;
    header_row.insertCell().innerHTML = `<b>EMAIL</b>`;
}
//Check
function check_student_name(){ 
    //Gán biến
    //let regex_student_name = /^[A-Za-zẮ-ỹ '-\.]+$/;
    let regex_student_name = /^[\p{L}\s'.-]+$/u;
    let student_name = document.getElementById("student-name");
    let student_name_value = student_name.value.trim();
    let error_student_name = document.getElementById("error-student-name");
    //Kiểm tra
    if(!student_name_value){
        student_name.classList.add("error-student-name");
        error_student_name.innerText = "Required";
        return false;
    }else if(regex_student_name.test(student_name_value)){
        student_name.classList.remove("error-student-name");
        error_student_name.innerText = "";
        return true;
    }else{
        student_name.classList.add("error-student-name");
        error_student_name.innerText = "Name student includes letters and symbols (spaces ' - .)";
        return false;
    }
}
function check_student_age(){
    //Gán biến
    let student_age = document.getElementById("student-age");
    let student_age_value = student_age.value.trim();
    let error_student_age = document.getElementById("error-student-age");
    //Kiểm tra
    if(student_age_value === ""){
        student_age.classList.add("error-student-age");
        error_student_age.innerText = "Required";
        return false;
    }
    student_age_value = Number(student_age_value);
    //isNaN = is Not A Number (Không phải số)
    if(isNaN(student_age_value)){
        student_age.classList.add("error-student-age");
        error_student_age.innerText = "Age must be a number";
        return false;
    }
    if(student_age_value < 2){
        student_age.classList.add("error-student-age");
        error_student_age.innerText = "Age is invalid";
        return false;
    }else if(student_age_value < 17 || student_age_value > 60){
        student_age.classList.remove("error-student-age");
        error_student_age.innerText = `Warning you are ${student_age_value} years old`;
        return true;
    }else{
        student_age.classList.remove("error-student-age");
        error_student_age.innerText = "";
        return true;
    }
}
function check_student_email(){
    //Gán biến
    let regex_student_email = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    let student_email = document.getElementById("student-email");
    let student_email_value = student_email.value.trim();
    let error_student_email = document.getElementById("error-student-email");
    //Kiểm tra
    if(!student_email_value){
        student_email.classList.add("error-student-email");
        error_student_email.innerText = "Required";
        return false;
    }else if(!regex_student_email.test(student_email_value)){
        student_email.classList.add("error-student-email");
        error_student_email.innerText = "Email's student is wrong";
        return false;
    }else{
        student_email.classList.remove("error-student-email");
        error_student_email.innerText = "";
        return true;
    }    
}
//Reset error-student-name/age/email
document.querySelector("form").addEventListener("reset", function(){
    document.getElementById("error-student-name").innerText = "";
    document.getElementById("error-student-age").innerText = "";
    document.getElementById("error-student-email").innerText = "";
});
//Thêm dữ liệu vào bảng
document.getElementById("button-form-student").addEventListener('click', function insert_table(){
    //Gán dữ liệu
    let student_name_value = document.getElementById("student-name").value.trim();
    let student_age_value = Number(document.getElementById("student-age").value.trim());
    let student_email_value = document.getElementById("student-email").value.trim();
    //Check empty
    if(!check_student_name() || !check_student_age() || !check_student_email()){
        return;
    }
    //Lưu dữ liệu theo obj
    students.push({
        student_name: student_name_value,
        student_age: student_age_value,
        student_email: student_email_value,
    });
    //Thêm dữ liệu vào bảng
    let row = create_table.insertRow();
    row.insertCell().innerText = students.length;
    row.insertCell().innerText = student_name_value;
    row.insertCell().innerText = student_age_value;
    row.insertCell().innerText = student_email_value;
    //Reset
    document.getElementById("form-student").reset();
})