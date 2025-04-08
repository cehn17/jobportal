package com.cehn17.jobportal.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users_type")
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserTypeId;

    private String UserTypeName;

    @OneToMany(targetEntity = User.class, mappedBy = "userTypeId", cascade = CascadeType.ALL)
    private List<User> users;

    public UserType() {
    }

    public UserType(int userTypeId, String userTypeName, List<User> users) {
        UserTypeId = userTypeId;
        UserTypeName = userTypeName;
        this.users = users;
    }

    public int getUserTypeId() {
        return UserTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        UserTypeId = userTypeId;
    }

    public String getUserTypeName() {
        return UserTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        UserTypeName = userTypeName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "UserTypeId=" + UserTypeId +
                ", UserTypeName='" + UserTypeName + '\'' +
                ", users=" + users +
                '}';
    }
}
