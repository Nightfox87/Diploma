package ru.iteco.fmhandroid.ui.data;

public class AuthDataHelper {

    public static class AuthInfo {
        private String login;
        private String password;

        public AuthInfo(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }

    public static AuthInfo getCorrectAuthInfo() {
        return new AuthInfo("login2", "password2");
    }

    public static AuthInfo getIncorrectLoginInfo() {
        return new AuthInfo("login22", "password2");
    }

    public static AuthInfo getInfoWithEmptyLogin() {
        return new AuthInfo("", "password2");
    }

    public static AuthInfo getInfoWithSpacesBeforeLogin() {
        return new AuthInfo("  login2", "password2");
    }

    public static AuthInfo getInfoWithSpacesBeforePassword() {
        return new AuthInfo("login2", "  password2");
    }

    public static AuthInfo getInfoWithSpaceAfterLogin() {
        return new AuthInfo("login2 ", "password2");
    }

    public static AuthInfo getInfoWithSpacesAfterPassword() {
        return new AuthInfo("login2", "password2  ");
    }
}
