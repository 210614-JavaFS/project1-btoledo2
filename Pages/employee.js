const URL = "http://localhost:8080/project1/employee/";

let createButton = document.getElementById("createReim");
let viewButton =  document.getElementById("viewReim");
let logoutButton = document.getElementById("logout");

createButton.onclick = addReim;
viewButton.onclick = viewTickets;
logoutButton.onclick = logoutEmployee;

async function loadSession() {
    let response = await fetch(URL + 'check');
    console.log(response.status);
    console.log('Hello');


    if (response.status == 404) {
        location.href = './index.html';
    }

    if (response.status == 201) {
        //catch response JSON data
        let userFromBackend = await response.json();
        userData = userFromBackend;

        let welcomeHead = document.getElementById("userInfo");
        welcomeHead.innerText = "Welcome " + userData.firstName; 
        console.log(userData);

        //console.log(userData.userId)
    }
}

function getNewTicket(){
    let newAmount = document.getElementById("amount").value;
    let newDescription = document.getElementById("shortDescription").value;
    let newType = document.getElementById("selection").value;
    userData;
    let reimbursement={
        amount:newAmount,
        description:newDescription,
        author:userData,
        typeString:newType
    }
    console.log(reimbursement);
    return reimbursement;
}

async function addReim(){
    let reimbursement = getNewTicket();
    console.log("In add Reim");

    let response = await fetch(URL + 'createForm',{
        method:'POST',
        body:JSON.stringify(reimbursement),
    })
    if(response.status === 201){
        console.log("Ticket Created");
    }else{
        console.log("Something went wrong could not add ticket.");
    }

}

async function viewTickets(){

    let responseList = await fetch(URL + 'viewForm/' + userData.userId)
    if(responseList.status === 200){
        let ticketData = await responseList.json();
        fillTable(ticketData);
        //console.log(ticketData);
    }else{
        console.log("Something went wrong");
    }
}

function fillTable(data){
    let tbody = document.getElementById("reimTable");
    console.log(data);
    let num = 0;
    tbody.innerHTML="";
    for(let ticket of data){
        num = 0;
        let row = document.createElement("tr");
        for(let cell in ticket){
            let td = document.createElement("td");
            if(cell =='amount' && ticket[cell]){
                td.innerText=ticket[cell];
                row.appendChild(td);
            }else if(cell =='description' && ticket[cell]){
                td.innerText=ticket[cell];
                row.appendChild(td);
            }else if(num === 6){
                if(cell =='resolver' && ticket[cell]){
                    td.innerText=ticket[cell].userName;
                    row.appendChild(td);
                }else{
                td.innerText="Waiting on Manager";
                row.appendChild(td);
                }
            }else if(cell =='status' && ticket[cell]){
                td.innerText=ticket[cell].status;
                row.appendChild(td);
            }else if(cell =='type' && ticket[cell]){
                td.innerText=ticket[cell].type;
                row.appendChild(td);
            }
            num = num +1;
        }
        tbody.appendChild(row);
    }

}

async function logoutEmployee() {
    let response = await fetch(URL + "logout");
    location.href = './index.html';
    
}









// need a get method to getCreate a ticket info
// need 

