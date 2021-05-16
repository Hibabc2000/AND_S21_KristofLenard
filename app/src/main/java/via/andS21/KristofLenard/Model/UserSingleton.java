package via.andS21.KristofLenard.Model;

public class UserSingleton {
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
}
