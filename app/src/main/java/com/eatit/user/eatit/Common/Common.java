package com.eatit.user.eatit.Common;

import com.eatit.user.eatit.Model.Users;

/**
 * Created by User on 21-Nov-17.
 */

public class Common {
    public static Users currentUser;

    public static Users getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Users currentUser) {
        Common.currentUser = currentUser;
    }
}
