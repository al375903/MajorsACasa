package es.uji.ei1027.majorsACasa.dao;

import java.util.Collection;
import es.uji.ei1027.majorsACasa.model.UserDetails;


public interface UserDao {
	UserDetails loadUserByUsername(String username, String password, String tipo);
    Collection<UserDetails> listAllUsers();
}