/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.User;

/**
 *
 * @author hkoub
 */
public interface IUserDao {
    boolean addUser(User user); 
    User findUserByLogin(String login); 
    boolean authenticate(String login, String password); 
    boolean userExists(String login);
    boolean updatePassword(String login, String newPassword);
}
