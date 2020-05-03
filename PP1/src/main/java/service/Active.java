package service;

import model.User;

public class Active {

    private User activeUser;

    public User getActive(){
        return activeUser;
    }

    public void setActive(User user){
        activeUser = user;
    }

    private static Active INSTANCE;

    public static Active getInstance(){
        if(INSTANCE == null){
            synchronized (Active.class){
                if(INSTANCE == null) INSTANCE = new Active();
            }
        }
        return INSTANCE;
    }
}
