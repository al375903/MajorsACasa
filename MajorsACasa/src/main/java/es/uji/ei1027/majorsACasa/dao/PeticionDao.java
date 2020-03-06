package es.uji.ei1027.majorsACasa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.majorsACasa.model.Peticion;

@Repository
public class PeticionDao {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
	       jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void addPeticion(Peticion p) {
		jdbcTemplate.update("INSERT INTO Peticion VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				p.getIdPeticion(), p.getIdBeneficiario(), p.getIdContrato(), p.getComentarios(), p.getTipoServicio(), p.getEstado(), null, null, null, null);
	}
	
	public void deletePeticion(String idPeticion) {
		jdbcTemplate.update("DELETE from Peticion where idPeticion=?",
				idPeticion);
	}
	
	public void updatePeticion(Peticion p) {
		jdbcTemplate.update("UPDATE Peticion SET comnetarios=?, tipoServicio=?, estado=?, fechaAprobacion=?, fechaDenegacion=?, fechaCreacion=?, fechaCancelacion=? where idPeticion=?",
				p.getComentarios(), p.getTipoServicio(), p.getEstado(), null, null, null, null);
	}
	
	public Peticion getPeticion(String idPeticion) {
	       try {
	           return jdbcTemplate.queryForObject("SELECT * from Peticion WHERE idPeticion=?",
	                   new PeticionRowMapper(), idPeticion);
	       }
	       catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	}
	
	public List<Peticion> getPeticiones() {
	       try {
	           return jdbcTemplate.query("SELECT * from CPeticion",
	                   new PeticionRowMapper());
	       } catch (EmptyResultDataAccessException e) {
	           return new ArrayList<Peticion>();
	       }
	   }

}
