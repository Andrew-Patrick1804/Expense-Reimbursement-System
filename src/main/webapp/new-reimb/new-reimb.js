
//get the current user's id from the url
const urlParams = new URLSearchParams(window.location.search);
const userId = urlParams.get("userId");
console.log(userId);

let newReimbForm = document.getElementById("new-reimb-form")

newReimbForm.onsubmit = async function(e){
    e.preventDefault();

    let reimbAmount = document.getElementById("amount").value;
    console.log(reimbAmount);

    let reimbDescr = document.getElementById("description").value;
    console.log(reimbDescr);

    let reimbType = document.getElementById("type-selector").value;
    console.log(reimbType);

    let response = await fetch(`${domain}/Project1/api/reimbursement?userId=${userId}`, {
        method : "POST",
        body : JSON.stringify({
            reimb_amount : reimbAmount,
            reimb_description : reimbDescr,
            reimb_author_id : userId,
            reimb_type_id : reimbType
        })
    });

    let responseData = await response.json();
    console.log(responseData);

    if(responseData.success){
        window.location = `${domain}/Project1/employee-dashboard?userId=${userId}`;
    }
}