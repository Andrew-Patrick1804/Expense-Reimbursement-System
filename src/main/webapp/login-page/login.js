let loginForm = document.getElementById("login-form");

loginForm.onsubmit = async function(e){
    e.preventDefault();

    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    //console.log(username);
    //console.log(password);

    let response = await fetch(`${domain}/Project1/api/login`, {
        method: "POST",
        body: JSON.stringify({
            username : username,
            password : password
        })
    });

    let responseData = await response.json();
    console.log(responseData);

    if(responseData.success){

        //console.log("redirecting...")

        if(responseData.data.user_role == "EMPLOYEE"){
            window.location = `${domain}/Project1/employee-dashboard?userId=${responseData.data.user_id}`;
        }
        else if(responseData.data.user_role == "FI_MANAGER"){
            window.location = `${domain}/Project1/manager-dashboard?userId=${responseData.data.user_id}`;
        }

    }
};