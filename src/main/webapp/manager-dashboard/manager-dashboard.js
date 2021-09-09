console.log("manager-dashboard.js hit!");

//getting the current user's Id from the URL
const urlParams = new URLSearchParams(window.location.search);
console.log(urlParams);

const userId = urlParams.get("userId");
console.log(userId);

let filterStatus = "ALL";


window.onload = async function(){
    makeReimbRequestRows();
}

async function makeReimbRequestRows(){

    //get all the employee's reimbursement requests
    const reimbResponse = await fetch(`${domain}/Project1/api/manager?userId=${userId}`, {
        method : "GET"
    })
    const reimbData = await reimbResponse.json();
    console.log(reimbData);

    if (filterStatus == "ALL"){
        makeAllReimbRequests(reimbData);
    }
    else{
        makeFilteredReimbRequests(reimbData);
    }

    
}

async function makeAllReimbRequests(reimbData){

    //getting the base table element
    let reimbTableElem = document.getElementById("reimb-table");


    reimbTableElem.innerHTML = "";
    reimbTableElem.innerHTML += `<tr>
    <th>Request ID</th>
    <th>$ Amount</th>
    <th>Date Submitted</th>
    <th>Status</th>
    <th>Type</th>
    <th>Details/Resolve</th>
</tr>`;

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
        detailsBtn.innerText = "Details/Resolve";
        detailsElem.appendChild(detailsBtn);
        tableRow.appendChild(detailsElem);
        
        reimbTableElem.appendChild(tableRow);

        //when the details button is clicked, navigate to a new page
        detailsBtn.onclick = function(){
            window.location = `${domain}/Project1/reimbursement-resolve?userId=${userId}&reimbId=${reimbRequest.reimb_id}`;
        }

    });
}

async function makeFilteredReimbRequests(reimbData){

    //getting the base table element
    let reimbTableElem = document.getElementById("reimb-table");

    reimbTableElem.innerHTML = "";
    reimbTableElem.innerHTML += `<tr>
    <th>Request ID</th>
    <th>$ Amount</th>
    <th>Date Submitted</th>
    <th>Status</th>
    <th>Type</th>
    <th>Details/Resolve</th>
</tr>`;
    

    //iterate through each employee's reimbursement request
    reimbData.data.forEach(reimbRequest => {

        if(filterStatus == reimbRequest.reimb_status){

        
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
            detailsBtn.innerText = "Details/Resolve";
            detailsElem.appendChild(detailsBtn);
            tableRow.appendChild(detailsElem);
            
            reimbTableElem.appendChild(tableRow);

            //when the details button is clicked, navigate to a new page
            detailsBtn.onclick = function(){
                window.location = `${domain}/Project1/reimbursement-resolve?userId=${userId}&reimbId=${reimbRequest.reimb_id}`;
            }
        }

    });
}

let filterForm = document.getElementById("filter-reimb");
filterForm.onsubmit = async function(e){
    e.preventDefault();

    filterStatus = document.getElementById("status-selector").value;
    makeReimbRequestRows();
}