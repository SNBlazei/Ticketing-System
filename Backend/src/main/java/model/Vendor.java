package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String configSettings;

    public Vendor(int id, String name, String email, String configSettings) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.configSettings = configSettings;

    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }public String configSettings() {
        return configSettings;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setConfigSettings(String configSettings) {
        this.configSettings = configSettings;
    }

    public Vendor() {

    }

}
