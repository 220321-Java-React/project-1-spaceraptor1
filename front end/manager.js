const url = "http://localhost:3005";
var user = sessionStorage.getItem('user');
var selected = 0;
u = JSON.parse(user);
document.getElementById("test").innerText="Welcome " + u.firstname + "!!";
getTableData();
getPendingData();
document.getElementById("create").addEventListener("click",createRequest)
document.getElementById("refresh").addEventListener("click",getTableData);
document.getElementById("refresh2").addEventListener("click",getPendingData);
document.getElementById("resolve").addEventListener("click",updateStatus)

async function getTableData(){
    let response = await fetch(url + "/myReqs", {
        method: "POST", 
        body: JSON.stringify(u.id),
        credentials: "include"
    });

    console.log(response);

    if(response.status === 200){
        let data = await response.json();
        document.getElementById("myReqBody").innerHTML = "";
        console.log(data);

        for(let r of data){
            console.log(r.status);
            //create a table row
            let row = document.createElement("tr");

            //create a data cell for each employee field
            let cell = document.createElement("td");
            cell.innerHTML = r.id;
            row.appendChild(cell);

            let cell2 = document.createElement("td");
            cell2.innerHTML = r.type;
            row.appendChild(cell2);

            let cell3 = document.createElement("td");
            cell3.innerHTML = r.amount;
            row.appendChild(cell3);

            let cell4 = document.createElement("td");
            cell4.innerHTML = r.status;
            row.appendChild(cell4);

            let cell5 = document.createElement("td");
            cell5.innerHTML = r.resolver;
            row.appendChild(cell5);

            let cell6 = document.createElement("td");
            cell6.innerHTML = r.email;
            row.appendChild(cell6);

            //row.addEventListener("click",selectRow(r.id));
            //append the tr (which we called "row") to the table body (tbody)
            //a new row will be appended FOR every employee that got returned in the fetch()
            document.getElementById("myReqBody").appendChild(row);
        }
    }else{
        alert("session inactive")
    }
}

async function createRequest(){
    let typ = document.getElementById("typeSelector").value;
    let amnt = document.getElementById("amountInput").value;
    
    if(amnt >0){
    let DTO = {
        type:typ,
        amount:amnt,
        author:u.id
    }

    console.log(typ);
    console.log(amnt);
    console.log(DTO.author);

    let response = await fetch(url + "/create", {
        method: "PUT", 
        body: JSON.stringify(DTO),
        credentials: "include"
    });

    console.log(response);
    if(response.status === 201){
        document.getElementById("amountInput").value = "Default value";
        getTableData();
    }else if(response.status === 400){alert("invalid input")
    }else if(response.status === 401){alert("session inactive")}
    }else{alert("invalid input")}
}

async function getPendingData(){
    let response = await fetch(url + "/pending", {
        method: "POST", 
        body: JSON.stringify(u.id),
        credentials: "include"
    });

    console.log(response);

    if(response.status === 200){
        let data = await response.json();
        document.getElementById("pendingBody").innerHTML = "";
        console.log(data);

        for(let r of data){
            console.log(r.status);
            //create a table row
            let row = document.createElement("tr");
            row.setAttribute("id", r.id);
            //create a data cell for each employee field
            let cell = document.createElement("td");
            cell.innerHTML = r.id;
            row.appendChild(cell);

            let cell2 = document.createElement("td");
            cell2.innerHTML = r.type;
            row.appendChild(cell2);

            let cell3 = document.createElement("td");
            cell3.innerHTML = r.amount;
            row.appendChild(cell3);

            let cell4 = document.createElement("td");
            cell4.innerHTML = r.resolver;
            row.appendChild(cell4);

            let cell5 = document.createElement("td");
            cell5.innerHTML = r.email;
            row.appendChild(cell5);

            
            document.getElementById("pendingBody").appendChild(row);
        }
        makeTableSelectable();
    }else{
        alert("session inactive")
    }
}
function selectRow(id){
    return function execute (event){
        console.log(id);
        console.log(event);
        selected = id;
        document.getElementById("sel").innerText = selected;
    }
}
async function updateStatus(){
    let stat = {
        id:selected,
        resolver:u.id,
        status:document.getElementById("statusSelector").value
    }
    if(stat.id != 0){

        let response = await fetch(url + "/resolve", {
            method: "PUT", 
            body: JSON.stringify(stat),
            credentials: "include"
        });
        if(response.status === 200){
            selected = 0;
            document.getElementById("sel").innerText = "Nothing Selected";
            getPendingData();
        }else if(response.status === 401){alert("sesion inactive")
        }else{alert("Bad input")}
    }else{
        alert("Please select a request from the table")
    }
}

function makeTableSelectable(){

    document.querySelectorAll('#pending tr')
    .forEach(e => {
        e.addEventListener("click", selectRow(e.id));
        
        console.log(e.id);
    });
}