//Check
function check_student_fullname(){
    //Khai báo biến
    const student_fullname_regex = /^[\p{L} '-.]+$/u; //Nhập các ký tự Unicode, space '-.
    let student_fullname = document.getElementById("student-fullname");
    let student_fullname_value = student_fullname.value.trim();
    let error_student_fullname = document.getElementById("error-student-fullname");
    //Kiểm tra
    if(!student_fullname_value){
        student_fullname.classList.add("error-student-fullname");
        error_student_fullname.innerText = "Required";
        return false;
    }
    if(!student_fullname_regex.test(student_fullname_value)){
        student_fullname.classList.add("error-student-fullname");
        error_student_fullname.innerText = "Student full name is invalid";
        return false;
    }else{
        student_fullname.classList.remove("error-student-fullname");
        error_student_fullname.innerText = "";
        return true;
    }
}
function check_student_age(){
    //Khai báo biến
    let student_age = document.getElementById("student-age");
    let student_age_value = student_age.value.trim();
    let error_student_age = document.getElementById("error-student-age");
    let warning_student_age = document.getElementById("warning-student-age");
    //Kiểm tra
    if(!student_age_value){
        student_age.classList.add("error-student-age");
        student_age.classList.remove("warning-student-age");
        error_student_age.innerText = "Required";
        warning_student_age.innerText = "";
        return false;
    }
    if(isNaN(student_age_value)){
        student_age.classList.add("error-student-age");
        student_age.classList.remove("warning-student-age");
        error_student_age.innerText = "Student age is number";
        warning_student_age.innerText = "";
        return false;
    }
    student_age_value = Number(student_age.value);
    if(student_age_value < 1){
        student_age.classList.add("error-student-age");
        student_age.classList.remove("warning-student-age");
        error_student_age.innerText = "Student age is invalid";
        warning_student_age.innerText = "";
        return false;
    }
    if(student_age_value === 1){
        student_age.classList.remove("error-student-age");
        student_age.classList.add("warning-student-age");
        error_student_age.innerText = "";
        warning_student_age.innerText = "Warning you are 1 year old";
        return true;
    }
    if(student_age_value < 18 || student_age_value > 60){
        student_age.classList.remove("error-student-age");
        student_age.classList.add("warning-student-age");
        error_student_age.innerText = "";
        warning_student_age.innerText = `Warning you are ${student_age_value} years old`;
        return true;
    }else{
        student_age.classList.remove("error-student-age");
        student_age.classList.remove("warning-student-age");
        error_student_age.innerText = "";
        warning_student_age.innerText = "";
        return true;
    }
}
function check_student_email(){
    //Khai báo biến
    const student_email_regex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
    let student_email = document.getElementById("student-email");
    let student_email_value = student_email.value.trim();
    let error_student_email = document.getElementById("error-student-email");
    //Kiểm tra
    if(!student_email_value){
        student_email.classList.add("error-student-email");
        error_student_email.innerText = "Required";
        return false;
    }
    if(!student_email_regex.test(student_email_value)){
        student_email.classList.add("error-student-email");
        error_student_email.innerText = "Student email is invalid";
        return false;
    }else{
        student_email.classList.remove("error-student-email");
        error_student_email.innerText = "";
        return true;
    }
}
function check_student_password(){
    //Khai báo biến
    //const student_password_regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    const student_password_regex = /^[\w\W]{6,}$/;
    let student_password = document.getElementById("student-password");
    let student_password_value = student_password.value.trim();
    let error_student_password = document.getElementById("error-student-password");
    //Kiểm tra
    if(!student_password_value){
        student_password.classList.add("error-student-password");
        error_student_password.innerText = "Required";
        return false;
    }
    if(!student_password_regex.test(student_password_value)){
        student_password.classList.add("error-student-password");
        error_student_password.innerText = "Password must be at least 6 characters long";
        // error_student_password.innerText = "Password must contain:" +
        // "\n- At least 1 lowercase letter\n- At least 1 uppercase letter" + 
        // "\n- At least 1 number\n- At least 1 special character\n- Minimum 8 characters";
        return false;
    }else{
        student_password.classList.remove("error-student-password");
        error_student_password.innerText = "";
        return true;
    }
}
//Khai báo form id form-student
const form_student = document.querySelector("#form-student");
//Khai báo button id button-submit
const button_submit = document.querySelector("#button-submit");
//Click button submit
button_submit.addEventListener("click", function(){
    //Kiểm tra 
    if(check_student_fullname() === true && check_student_age() === true 
    && check_student_email() === true && check_student_password() === true){
        //Gán biến
        let student_fullname_value = document.getElementById("student-fullname").value.trim();
        let student_age_value = document.getElementById("student-age").value.trim();
        let student_email_value = document.getElementById("student-email").value.trim();
        //Click
        alert("Thêm sinh viên thành công!" + `\nHọ tên: ${student_fullname_value}` + 
            `\nTuổi: ${student_age_value}` + `\nEmail: ${student_email_value}`);
        form_student.reset();
        document.getElementById("student-age").value = 18;
    }
});