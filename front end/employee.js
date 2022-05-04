
var user = sessionStorage.getItem('user');
u = JSON.parse(user);
document.getElementById("test").innerText="Welcome " + u.firstname + "!!";