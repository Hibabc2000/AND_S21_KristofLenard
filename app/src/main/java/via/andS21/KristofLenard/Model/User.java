package via.andS21.KristofLenard.Model;

public class User
{
    /**
     * @version 1.0.0
     * This class is used for logging in.
     */
    private String username, password;
    private Voter voter;

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

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }
}
