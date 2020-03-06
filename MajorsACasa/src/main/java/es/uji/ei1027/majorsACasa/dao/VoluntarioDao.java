package es.uji.ei1027.majorsACasa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.majorsACasa.model.Voluntario;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class VoluntarioDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	void addVoluntario(Voluntario voluntario) {
		jdbcTemplate.update("INSERT INTO Voluntario VALUES(?,?,?,?,?,?,?,?,?,?,?,?)",
				voluntario.getIdVoluntario(), voluntario.getNombre(), voluntario.getEmail(),
				voluntario.getDireccion(), voluntario.getHobby(), null, null, null,
				voluntario.isAceptado(), voluntario.getGenero(), voluntario.getEdad(),
				voluntario.getContrasenya());
	}
	
	void deleteVoluntario(Voluntario voluntario) {
		jdbcTemplate.batchUpdate("DELETE FROM Voluntario WHERE idVoluntario=?", voluntario.getIdVoluntario());
	}
	
	void updateVoluntario(Voluntario voluntario) {
		jdbcTemplate.update("UPDATE Voluntario SET nombre=?, email=?, direccion=?,"
				+ "hobby=?, fechaPeticionVoluntariado=?, fechaAceptacionVoluntariado=?,"
				+ "fechaFin=?, aceptado=?, genero=?, edad=?, contrasenya=?",
				voluntario.getNombre(), voluntario.getEmail(), voluntario.getDireccion(),
				voluntario.getHobby(), null, null, null, voluntario.isAceptado(),
				voluntario.getGenero(), voluntario.getEdad(), voluntario.getContrasenya());
	}
	
	Voluntario getVoluntario(String idVoluntario) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM Voluntario WHERE idVoluntario=?",
					new VoluntarioRowMapper(), idVoluntario);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	List<Voluntario> getVoluntarios(){
		try{
			return jdbcTemplate.query("SELECT * FROM Voluntario", new VoluntarioRowMapper());
		} catch(EmptyResultDataAccessException e) {
			return new ArrayList<Voluntario>();
		}
	}
}









