console.log("employee-dashboard.js hit!");

//getting the current user's Id from the URL
const urlParams = new URLSearchParams(window.location.search);
console.log(urlParams);

const userId = urlParams.get("userId");
console.log(userId);


window.onload = async function(){
    makeReimbRequestRows();
}

async function makeReimbRequestRows(){

    //get all the employee's reimbursement requests
    const reimbResponse = await fetch(`${domain}/Project1/api/employee?userId=${userId}`, {
        method : "GET"
    })
    const reimbData = await reimbResponse.json();
    console.log(reimbData);

    //getting the base table element
    let reimbTableElem = document.getElementById("reimb-table");


    //iterate through each employee's reimbursement request
    reimbData.data.forEach(reimbRequest => {

        let tableRow = document.createElement("tr");

        let dataIdElem = document.createElement("td");
        dataIdElem.innerText = reimbRequest.reimb_id;
        tableRow.appendChild(dataIdElem);

        let dataAmountElem = document.createElement("td");
        dataAmountElem.innerText = reimbRequest.reimb_amount;
        tableRow.appendChild(dataAmountElem);

        let dataSubmittedElem = document.createElement("td");
        dataSubmittedElem.innerText = reimbRequest.reimb_submitted;
        tableRow.appendChild(dataSubmittedElem);

        let dataStatusElem = document.createElement("td");
        dataStatusElem.innerText = reimbRequest.reimb_status;
        tableRow.appendChild(dataStatusElem);

        let dataTypeElem = document.createElement("td");
        dataTypeElem.innerText = reimbRequest.reimb_type;
        tableRow.appendChild(dataTypeElem);

        let detailsElem = document.createElement("td");
        let detailsBtn = document.createElement("button");
        detailsBtn.className = "btn btn-primary";
        detailsBtn.id = "details-btn";
        detailsBtn.innerText = "Details";
        detailsElem.appendChild(detailsBtn);
        tableRow.appendChild(detailsElem);
        
        reimbTableElem.appendChild(tableRow);

        //when the details button is clicked, navigate to a new page
        detailsBtn.onclick = function(){
            window.location = `${domain}/Project1/reimbursement-details?userId=${userId}&reimbId=${reimbRequest.reimb_id}`;
        }

    });
}

let newReimbBtn = document.getElementById("new-reimb-btn");
newReimbBtn.onclick = function(){
    window.location = `${domain}/Project1/new-reimb?userId=${userId}`;
};
