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
	    responsableContratacion.setTipo("Responsable Contratación");
	    knownUsers.put("casManager", responsableContratacion);
	      
	   UserDetails comite = new UserDetails(); 
	   comite.setUsername("casCommitee"); 
	   comite.setPassword(passwordEncryptor.encryptPassword("casCommitee")); 
	   comite.setTipo("Comité");
	   knownUsers.put("casCommitee", comite);
	   
	   UserDetails supervisorVoluntarios = new UserDetails(); 
	   supervisorVoluntarios.setUsername("casVolunteer"); 
	   supervisorVoluntarios.setPassword(passwordEncryptor.encryptPassword("casVolunteer")); 
	   supervisorVoluntarios.setTipo("Supervisor Voluntarios");
	   knownUsers.put("casVolunteer", supervisorVoluntarios);
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