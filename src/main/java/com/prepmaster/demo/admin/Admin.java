package com.prepmaster.demo.admin;

public class Admin {
    private Long id;
    private String email;
    private String organization;
    private String password;

    public Admin() {
    }

    public Admin(
            String email,
            String organization,
            String password) {
        this.email = email;
        this.organization = organization;
        this.password = password;
    }

    public Admin(Long id, String email, String organization, String password) {
        this.id = id;
        this.email = email;
        this.organization = organization;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", organization='" + organization + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
