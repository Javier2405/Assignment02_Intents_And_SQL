package com.example.intentsandsqlite;

public class User {

    private String name;
    private String lastname;
    private String nickname;
    private String password;
    private String dateofbirth;
    private String role;
    private int semester;

    public User(String[] info, int semester){
        this.name = info[0];
        this.lastname = info[1];
        this.nickname = info[2];
        this.password = info[3];
        this.dateofbirth = info[4];
        this.role = info[5];
        this.semester = semester;

    }


    public String getNickname() {
        return nickname;
    }

    public int getSemester() {
        return semester;
    }

    public String getLastname() {
        return lastname;
    }

    public String getName(){
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public String getRole() {
        return role;
    }
}
