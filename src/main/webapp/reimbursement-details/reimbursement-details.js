console.log("reimbursement-details.js hit!!");

const urlParams = new URLSearchParams(window.location.search);
const userId = urlParams.get("userId");
const reimbId = urlParams.get("reimbId");

console.log(userId, reimbId);

window.onload = async function(){
    fillDetails();
}

async function fillDetails(){


    //get the reimbursement data
    const reimbResponse = await fetch(`${domain}/Project1/api/reimbursement?reimbursementId=${reimbId}`,{
        method : "GET"
    })
    const reimbData = await reimbResponse.json();
    console.log(reimbData);
    const reimbDetails = reimbData.data;
    console.log(reimbDetails)



    //get the reimbursement submitter's name
    const submitterNameResponse = await fetch(`${domain}/Project1/api/username?userId=${reimbDetails.reimb_author_id}`, {
        method : "GET"
    });
    const submitterName = await submitterNameResponse.json();
    console.log(submitterName);
    console.log(submitterName.data);



    //set the reimbursement id
    let reimbIdElem = document.getElementById("reimb-id");
    reimbIdElem.innerText += " " + reimbDetails.reimb_id;

    //set the reimbursement amount
    let reimbAmountElem = document.getElementById("reimb-amount");
    reimbAmountElem.innerText +=  " " + reimbDetails.reimb_amount;

    //set the reimbursement description
    let reimbDescrElem = document.getElementById("reimb-description");
    reimbDescrElem.innerText += " " + reimbDetails.reimb_description;

    //set the reimbursement type
    let reimbTypeElem = document.getElementById("reimb-type");
    reimbTypeElem.innerText += " " + reimbDetails.reimb_type;

    //set the reimbursement submitter name
    let reimbSubmitterElem = document.getElementById("reimb-submitter");
    reimbSubmitterElem.innerText += " " + submitterName.data;

    //set the reiumbursement submission time
    let reimbSubmitTimeElem = document.getElementById("reimb-submitted");
    reimbSubmitTimeElem.innerText += " " + reimbDetails.reimb_submitted;

    //set the reimbursement status
    let reimbStatusElem = document.getElementById("reimb-status");
    reimbStatusElem.innerText += " " + reimbDetails.reimb_status;

    /* 
        Check if reimbursement resolved time and resolver need to be filled.
        If not, then just leave them blank.
    */

    if (reimbDetails.reimb_status_id != 1){
        console.log("gotta fill in the rest of the details!");
        
        //get the resolver's name
        const resolverNameResponse = await fetch(`${domain}/Project1/api/username?userId=${reimbDetails.reimb_resolver_id}`, {
            method : "GET"
        });
        const resolverName = await resolverNameResponse.json();
        console.log(resolverName);
        console.log(resolverName.data);

        //set the reimbursement resolver
        let reimbResolverElem = document.getElementById("reimb-resolver");
        reimbResolverElem.innerText += " " + resolverName.data;

        //set the reimbursement resolve time
        let reimbResolveTimeElem = document.getElementById("reimb-resolved");
        reimbResolveTimeElem.innerText += " " + reimbDetails.reimb_resolved

    }
    else{
        console.log("don't gotta fill in the rest of the details!");
    }


}