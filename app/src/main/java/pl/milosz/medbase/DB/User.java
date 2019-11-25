package pl.milosz.medbase.DB;

/**
 * Obiekt reprezentujący użytwkonika systemu
 *
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class User {
    private String username;
    private String password;
    private int id;

    User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
