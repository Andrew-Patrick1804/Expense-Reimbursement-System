package models;

import java.util.Objects;

/**
 * A representation of an entry in the ers_users database table.
 * These are the users of the application, including employees and finance managers.
 */
public class ERSUser {

    private Integer user_id;
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String email;
    private Integer user_role_id;   //1 means employee, 2 means finance manager
    private String user_role;       //determined by user_role_id

    public ERSUser(){

    }

    public ERSUser(Integer user_id, String username, String password,
                   String first_name, String last_name,
                   String email, Integer user_role_id) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.user_role_id = user_role_id;

        //determines user role based on their user_role_id
        if(user_role_id == 1){
            this.user_role = "EMPLOYEE";
        }
        else{
            this.user_role = "FI_MANAGER";
        }
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUser_role_id() {
        return user_role_id;
    }

    public void setUser_role_id(Integer user_role_id) {
        this.user_role_id = user_role_id;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    @Override
    public String toString() {
        return "ERSUser{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", user_role_id=" + user_role_id +
                ", user_role='" + user_role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ERSUser)) return false;
        ERSUser ersUser = (ERSUser) o;
        return Objects.equals(getUser_id(),
                ersUser.getUser_id()) && Objects.equals(getUsername(),
                ersUser.getUsername()) && Objects.equals(getPassword(),
                ersUser.getPassword()) && Objects.equals(getFirst_name(),
                ersUser.getFirst_name()) && Objects.equals(getLast_name(),
                ersUser.getLast_name()) && Objects.equals(getEmail(),
                ersUser.getEmail()) && Objects.equals(getUser_role_id(),
                ersUser.getUser_role_id()) && Objects.equals(getUser_role(),
                ersUser.getUser_role());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser_id(), getUsername(), getPassword(),
                getFirst_name(), getLast_name(), getEmail(),
                getUser_role_id(), getUser_role());
    }
}
