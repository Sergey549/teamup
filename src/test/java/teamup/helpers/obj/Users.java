package teamup.helpers.obj;

public class Users {
    private final String password;
    private final String email;

    public Users(String loginEmail, String loginPassword) {
        this.email = loginEmail;
        this.password = loginPassword;
    }

    public String getLoginEmail() {
        return email;
    }

    public String getLoginPassword() {
        return password;
    }

}
