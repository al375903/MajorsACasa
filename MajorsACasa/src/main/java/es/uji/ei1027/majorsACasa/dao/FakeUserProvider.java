package es.uji.ei1027.majorsACasa.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.stereotype.Repository;
import es.uji.ei1027.majorsACasa.model.UserDetails;

@Repository
public class FakeUserProvider implements UserDao {
  final Map<String, UserDetails> knownUsers = new HashMap<String, UserDetails>();

  public FakeUserProvider() {
	    BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor(); 
	    
	    UserDetails userAlice = new UserDetails(); 
	    userAlice.setUsername("alice"); 
	    userAlice.setPassword(passwordEncryptor.encryptPassword("alice")); 
	    userAlice.setTipo("voluntario");
	    knownUsers.put("alice", userAlice);
	      
	   UserDetails userBob = new UserDetails(); 
	   userBob.setUsername("bob"); 
	   userBob.setPassword(passwordEncryptor.encryptPassword("bob")); 
	   userBob.setTipo("voluntario");
	   knownUsers.put("bob", userBob);
  }

  @Override
  public UserDetails loadUserByUsername(String username, String password, String tipo) { 
      UserDetails user = knownUsers.get(username.trim());
      if (user == null)
          return null; // Usuari no trobat
      // Contrasenya
     BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor(); 
     if (passwordEncryptor.checkPassword(password, user.getPassword())) {
     // Es deuria esborrar de manera segura el camp password abans de tornar-lo
         return user; 
        } 
     else {
         return null; // bad login!
         }
      }

      @Override 
      public Collection<UserDetails> listAllUsers() {
         return knownUsers.values();
      }
}