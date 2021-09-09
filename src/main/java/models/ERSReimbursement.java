package models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

/**
 * A representation of an entry in the ers_reimbursement database table.
 * These are submitted reimbursement requests.
 */
public class ERSReimbursement {

    private Integer reimb_id;
    private Float reimb_amount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd @ HH:mm:ss")
    private Timestamp reimb_submitted;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd @ HH:mm:ss")
    private Timestamp reimb_resolved;

    private String reimb_description;
    //private ?????? reimb_receipt;
    private Integer reimb_author_id;
    private Integer reimb_resolver_id;
    private Integer reimb_status_id;
    private String reimb_status;
    private Integer reimb_type_id;
    private String reimb_type;

    public ERSReimbursement() {
    }

    public ERSReimbursement(Integer reimb_id, Float reimb_amount, Timestamp reimb_submitted,
                            Timestamp reimb_resolved, String reimb_description, Integer reimb_author_id,
                            Integer reimb_resolver_id, Integer reimb_status_id, Integer reimb_type_id) {
        this.reimb_id = reimb_id;
        this.reimb_amount = reimb_amount;
        this.reimb_submitted = reimb_submitted;
        this.reimb_resolved = reimb_resolved;
        this.reimb_description = reimb_description;
        this.reimb_author_id = reimb_author_id;
        this.reimb_resolver_id = reimb_resolver_id;

        this.reimb_status_id = reimb_status_id;
        switch(reimb_status_id){
            case 1 :
                this.reimb_status = "PENDING";
                break;
            case 2 :
                this.reimb_status = "APPROVED";
                break;
            case 3 :
                this.reimb_status = "DENIED";
                break;
        }

        this.reimb_type_id = reimb_type_id;
        switch(reimb_type_id){
            case 1 :
                this.reimb_type = "LODGING";
                break;
            case 2 :
                this.reimb_type = "TRAVEL";
                break;
            case 3 :
                this.reimb_type = "FOOD";
                break;
            case 4 :
                this.reimb_type = "OTHER";
                break;
        }
    }

    public Integer getReimb_id() {
        return reimb_id;
    }

    public void setReimb_id(Integer reimb_id) {
        this.reimb_id = reimb_id;
    }

    public Float getReimb_amount() {
        return reimb_amount;
    }

    public void setReimb_amount(Float reimb_amount) {
        this.reimb_amount = reimb_amount;
    }

    public Timestamp getReimb_submitted() {
        return reimb_submitted;
    }

    public void setReimb_submitted(Timestamp reimb_submitted) {
        this.reimb_submitted = reimb_submitted;
    }

    public Timestamp getReimb_resolved() {
        return reimb_resolved;
    }

    public void setReimb_resolved(Timestamp reimb_resolved) {
        this.reimb_resolved = reimb_resolved;
    }

    public String getReimb_description() {
        return reimb_description;
    }

    public void setReimb_description(String reimb_description) {
        this.reimb_description = reimb_description;
    }

    public Integer getReimb_author_id() {
        return reimb_author_id;
    }

    public void setReimb_author_id(Integer reimb_author_id) {
        this.reimb_author_id = reimb_author_id;
    }

    public Integer getReimb_resolver_id() {
        return reimb_resolver_id;
    }

    public void setReimb_resolver_id(Integer reimb_resolver_id) {
        this.reimb_resolver_id = reimb_resolver_id;
    }

    public Integer getReimb_status_id() {
        return reimb_status_id;
    }

    public void setReimb_status_id(Integer reimb_status_id) {
        this.reimb_status_id = reimb_status_id;
    }

    public Integer getReimb_type_id() {
        return reimb_type_id;
    }

    public void setReimb_type_id(Integer reimb_type_id) {
        this.reimb_type_id = reimb_type_id;
    }

    public String getReimb_status() {
        return reimb_status;
    }

    public void setReimb_status(String reimb_status) {
        this.reimb_status = reimb_status;
    }

    public String getReimb_type() {
        return reimb_type;
    }

    public void setReimb_type(String reimb_type) {
        this.reimb_type = reimb_type;
    }

    @Override
    public String toString() {
        return "ERSReimbursement{" +
                "reimb_id=" + reimb_id +
                ", reimb_amount=" + reimb_amount +
                ", reimb_submitted=" + reimb_submitted +
                ", reimb_resolved=" + reimb_resolved +
                ", reimb_description='" + reimb_description + '\'' +
                ", reimb_author_id=" + reimb_author_id +
                ", reimb_resolver_id=" + reimb_resolver_id +
                ", reimb_status_id=" + reimb_status_id +
                ", reimb_status='" + reimb_status + '\'' +
                ", reimb_type_id=" + reimb_type_id +
                ", reimb_type='" + reimb_type + '\'' +
                '}';
    }
}
