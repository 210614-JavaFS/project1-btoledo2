const URL = "http://localhost:8080/project1/manager/";

let viewByStatusButton = document.getElementById("viewStatus");
let updateStatusButton = document.getElementById("statusUpdate");
let logoutButton = document.getElementById("logout");

viewByStatusButton.onclick = viewByStatus;
updateStatusButton.onclick = updateStatus;
logoutButton.onclick = logoutManager;


async function loadSession() {
    let response = await fetch(URL + 'managerCheck');
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

async function updateStatus(){
    let newStatus = document.getElementById("selection").value;
    let ticketToUpdate = document.getElementById("TicketID").value;

    let response = await fetch(URL + 'newStatus/' + newStatus  + "/" + ticketToUpdate + '/' +userData.userId, {
        method: 'PUT'
    });

    if (response.status === 201) {
        console.log("Ticket was being update");
    }else{
        console.log("Something went wrong");
    }




}






async function viewByStatus(){
    let newStatusId = document.getElementById("status").value;
    let responseList = await fetch(URL + 'viewStatus/' + newStatusId)
    if(responseList.status === 200){
        let ticketData = await responseList.json();
        fillTable(ticketData);
        //console.log(ticketData);
    }else{
        console.log("Something went wrong");
    }
}






function fillTable(data){
    let tbody = document.getElementById("reimbursements");
    console.log(data);
    let num = 0;
    tbody.innerHTML="";
    for(let ticket of data){
        num = 0;
        let row = document.createElement("tr");
        for(let cell in ticket){
            let td = document.createElement("td");
            if(cell == 'reimbId' && ticket[cell]){
                td.innerText=ticket[cell];
                row.appendChild(td);
            }else if(cell =='amount' && ticket[cell]){
                td.innerText=ticket[cell];
                row.appendChild(td);
            }else if(cell =='description' && ticket[cell]){
                td.innerText=ticket[cell];
                row.appendChild(td);
            }else if(cell =='author' && ticket[cell]){
                td.innerText=ticket[cell].userName;
                row.appendChild(td);
            }else if(num === 6){
                if(cell =='resolver' && ticket[cell]){
                    td.innerText=ticket[cell].userName;
                    row.appendChild(td);
                }else{
                td.innerText="Waiting Manager";
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
        console.log(num);
    tbody.appendChild(row);
    }

}



async function logoutManager() {
    let response = await fetch(URL + "logout");
    location.href = './index.html';
    

    checkSessions();
}




