const url = "http://localhost:3005";

document.getElementById("loginButton").addEventListener("click", login);

async function login(){
    let un = document.getElementById("username").value;
    let pw = document.getElementById("password").value;
    let user = {
        username:un,
        password:pw
    }

    console.log(user);

    let response = await fetch(url+"/login", {

        method: "POST", 
        body: JSON.stringify(user), 
        credentials: "include"
         
    });
    //let result = await response.json();
    //console.log("sadsada");
    //console.log(response.status);

    if(response.status === 202){

        //converting from json to JS
        let data = await response.json();
       //wipe our login row and welcome the user
       console.log("success");
       document.getElementById("welcomeHead").innerText="Welcome " + data.firstname + "!!";
   
       //THIS IS PROBABLY WHERE YOUR REDIRECT WOULD BE IF USING MULTIPLE HTML PAGES
       //don't be intimidated, it's an easy google :)
       sessionStorage.setItem('user',JSON.stringify(data));
       if(data.roleID === 1){
            window.location.replace("employee.html");
       }else if(data.roleID === 2){
            window.location.replace("manager.html");
       }
   } else {
    console.log("failed");
       document.getElementById("welcomeHead").innerText="Login failed! Try Again";
       document.getElementById("welcomeHead").style.color = "red";
   }

}