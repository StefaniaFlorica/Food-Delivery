package model;

import java.io.Serializable;
import java.util.Objects;

public class Client implements Serializable {
    private int id;
    private String user;
    private String password;

    public Client(int id,String user, String password) {
        this.id=id;
        this.user = user;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return user.equals(client.user) && password.equals(client.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, password);
    }

    @Override
    public String toString() {
        return "Client: " +
                "id=" + id +
                ", user='" + user + '\'' +
                ", password='" + password ;
    }
}
