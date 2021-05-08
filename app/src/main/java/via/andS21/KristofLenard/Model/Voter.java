package via.andS21.KristofLenard.Model;

public class Voter
{
    /**
     * @version 1.0.0
     * This class is responsible for storing voter info.
     * AuthMethod and authCode refer to the voter authentication scheme (eg. passport, with passport number as code)
     * For user authentication, see User class.
     */
    private String fullName;
    private String dateOfBirth; //format: "dd/mm/yyyy"
    private String country;
    private String authMethod;
    private String authCode;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAuthMethod() {
        return authMethod;
    }

    public void setAuthMethod(String authMethod) {
        this.authMethod = authMethod;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
}
