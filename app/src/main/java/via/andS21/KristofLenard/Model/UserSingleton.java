package via.andS21.KristofLenard.Model;

public class UserSingleton {

    /**
     * This class is to ensure that the user being handled is unique,
     * to prevent potential security and software issues.
     */

    private static UserSingleton userSingleton = null;
    private static User user;

    private UserSingleton(User user)
    {
        UserSingleton.user = user;
    }

    public static UserSingleton getInstance(User user)
    {
        if (userSingleton == null) {
            userSingleton = new UserSingleton(user);
        }
        return userSingleton;
    }

    public static User getUser() {
        return user;
    }

    public static void setVoter(Voter voter) {
        user.setVoter(voter);
    }
}
