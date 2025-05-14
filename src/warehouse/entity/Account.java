package warehouse.entity;

import warehouse.bean.ERole;


public class Account {
    private String username;
    private String password;
    private ERole role;


    public Account() {
    }

    public Account(String username, String password, ERole role) {
        this.username = username;
        this.password = password;
        this.role = role;
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

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }
    
    
}
