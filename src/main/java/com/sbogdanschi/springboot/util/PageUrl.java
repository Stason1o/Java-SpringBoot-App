package com.sbogdanschi.springboot.util;

public class PageUrl {

    public static final String INDEX = "/";

    public static final String REDIRECT = "redirect:";

    public static final String REDIRECT_TO = "redirect:/";

    public static final String ERROR_PAGE = "/error";

    public static final String ACCESS_DENIED = "/access-denied";

    public static final Admin Admin = new Admin();

    public static final User User = new User();

    public static final Role Role = new Role();

    private PageUrl() {
        //default constructor
    }

    //    public static final String

    public static final class Admin {

        public static final String ADMIN_PAGE = "/admin";

        public static final String BASE_ADMIN_PAGE = "/admin/";

        public static final String SEARCH = "/search";

        public static final String USER_MANAGEMENT = "/user-management";

        public static final String ADMIN_SUB_DIRECTORY = "/admin/**";

        public static final String USERS_LIST = "/users/list";

        private Admin() {
            //private constructor
        }
    }

    public static final class User {

        public static final String LOGIN = "/login";

        public static final String REGISTRATION = "/registration";

        public static final String LOGIN_ERROR_PAGE = "/login?error=true";

        public static final String LOGOUT = "/logout";

        private User() {
            //default constructor
        }
    }

    public static final class Role {

        public static final String ADMIN = "ADMIN";

        public static final String USER = "USER";

        public static final String ROLE_ADMIN = "ROLE_ADMIN";

        public static final String ROLE_USER = "ROLE_USER";

        private Role() {
            //default constructor
        }

    }

    public static final class Common {

        private Common() {
            //default constructor
        }

        public static final String APPLICATION_JSON = "application/json";
    }
}
