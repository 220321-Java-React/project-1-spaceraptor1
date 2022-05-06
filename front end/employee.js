const url = "http://localhost:3005";
var user = sessionStorage.getItem('user');
u = JSON.parse(user);
document.getElementById("test").innerText="Welcome " + u.firstname + "!!";
getTableData();
document.getElementById("refresh").addEventListener("click",getTableData);

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

            //append the tr (which we called "row") to the table body (tbody)
            //a new row will be appended FOR every employee that got returned in the fetch()
            document.getElementById("myReqBody").appendChild(row);
        }
    }else{
        alert("session inactive")
    }
}