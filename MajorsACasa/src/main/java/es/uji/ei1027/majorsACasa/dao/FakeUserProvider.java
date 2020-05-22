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
	    
		UserDetails responsableContratacion = new UserDetails(); 
		responsableContratacion.setUsername("casManager"); 
		responsableContratacion.setPassword(passwordEncryptor.encryptPassword("casManager")); 
		responsableContratacion.setTipo("casManager");
		knownUsers.put("casManager", responsableContratacion);
	      
	   UserDetails comite = new UserDetails(); 
	   comite.setUsername("casCommittee"); 
	   comite.setPassword(passwordEncryptor.encryptPassword("casCommittee")); 
	   comite.setTipo("casCommittee");
	   knownUsers.put("casCommittee", comite);
	   
	   UserDetails supervisorVoluntarios = new UserDetails(); 
	   supervisorVoluntarios.setUsername("casVolunteer"); 
	   supervisorVoluntarios.setPassword(passwordEncryptor.encryptPassword("casVolunteer")); 
	   supervisorVoluntarios.setTipo("casVolunteer");
	   knownUsers.put("casVolunteer", supervisorVoluntarios);
	   
	   //Usuario que tiene acceso a todo
	    UserDetails jefe = new UserDetails(); 
	    jefe.setUsername("jefe"); 
	    jefe.setPassword(passwordEncryptor.encryptPassword("jefe")); 
	    jefe.setTipo("jefe");
	    knownUsers.put("jefe", jefe);
	    
	    //Hacer put de todos los usuarios de las tablas
  }

  @Override
  public UserDetails loadUserByUsername(String username, String password) { 
	  UserDetails user = knownUsers.get(username.trim());
      //Si no es ninguno de estos buscar por tablas
      if (user == null) {
    	  //buscar por el resto de tablas con jdbtemplate
    	  //try catch
    	  return null; // Usuari no trobat
      // Contrasenya
  	}
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