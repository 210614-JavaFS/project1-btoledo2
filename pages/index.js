const URL = "http://localhost:8080/project1/";
let loginbutton = document.getElementById("submitbtn");

loginbutton.onclick = loginUser;


function getLoginUser() {

    let inputName = document.getElementById("loginName").value;
    let passWord = document.getElementById("password").value;;

    let user = {
        userName:inputName,
        password:passWord
    }

    return user;
}

async function loginUser() {
    let users = getLoginUser();
    console.log(users);
    let response =  await fetch(URL + 'userLogin',{
        method:'POST',
        body:JSON.stringify(users),
       // credentials: 'include'
    })

    if (response.status === 201) {
        console.log("User Login is successful");
        document.location.href = './employee.html';

    } else if (response.status === 200) {
        console.log("User Login is successful");
        document.location.href = './manager.html';

    } else {
        console.log('User Login failed');
        alert("Login failed!");
    }
}