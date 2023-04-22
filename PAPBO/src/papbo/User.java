package papbo;

public class User {
    private String Username, Password, Address, Email;
    private int Age;

    public User(String Username, String Password, String Address, String Email, int Age) {
        this.Username = Username;
        this.Password = Password;
        this.Address = Address;
        this.Email = Email;
        this.Age = Age;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getAddress() {
        return Address;
    }

    public String getEmail() {
        return Email;
    }
    
    public int getAge(){
        return Age;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }
    
}
