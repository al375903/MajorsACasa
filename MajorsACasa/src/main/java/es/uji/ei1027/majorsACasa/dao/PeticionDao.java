package es.uji.ei1027.majorsACasa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.majorsACasa.model.Peticion;
import es.uji.ei1027.majorsACasa.services.PeticionService;

@Repository
public class PeticionDao {
	private JdbcTemplate jdbcTemplate;
	PeticionService peticionService;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
	       jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void addPeticion(Peticion peticion) {
		jdbcTemplate.update("INSERT INTO Peticion VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				peticion.getIdPeticion(), peticion.getIdBeneficiario(), peticion.getIdContrato(), 
				peticion.getComentarios(), peticion.getTipoServicio(), peticion.getEstado() , 
				peticion.getFechaAprobacion(), peticion.getFechaDenegacion(), 
				peticion.getFechaCreacion(), peticion.getFechaCancelacion());
	}
	
	public void deletePeticion(String idPeticion) {
		jdbcTemplate.update("DELETE FROM Peticion WHERE idPeticion=?", idPeticion);
	}
	
	public void updatePeticion(Peticion peticion) {
		jdbcTemplate.update("UPDATE Peticion SET comentarios=?, idContrato=?, tipoServicio=?, estado=?, "
				+ "fechaAprobacion=?, fechaDenegacion=?, fechaCreacion=?, fechaCancelacion=? "
				+ "WHERE idPeticion=?",
				peticion.getComentarios(), peticion.getIdContrato(), peticion.getTipoServicio(), peticion.getEstado(), 
				peticion.getFechaAprobacion(), peticion.getFechaDenegacion(),
				peticion.getFechaCreacion(), peticion.getFechaCancelacion(), 
				peticion.getIdPeticion());
	}
	
	public Peticion getPeticion(String idPeticion) {
	       try {
	           return jdbcTemplate.queryForObject("SELECT Peticion.*, Contrato.nombreEmpresa FROM Peticion LEFT JOIN Contrato USING(idContrato) WHERE idPeticion=?",
	                   new PeticionRowMapper(), idPeticion);
	       }
	       catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	}
	
	public List<Peticion> getPeticiones() {
	       try {
	           return jdbcTemplate.query("SELECT Peticion.*, Contrato.nombreEmpresa FROM Peticion LEFT JOIN Contrato USING(idContrato) ORDER BY Peticion.idPeticion",
	                   new PeticionRowMapper());
	       } catch (EmptyResultDataAccessException e) {
	           return new ArrayList<Peticion>();
	       }
	}
	
	public List<Peticion> getPeticionBeneficiario(String idBeneficiario){ //No muestra los que idContrato=NULL
		try {
	           return jdbcTemplate.query("SELECT Peticion.*, Contrato.nombreEmpresa FROM Peticion LEFT JOIN Contrato USING(idContrato) WHERE idBeneficiario=?",
	                   new Object[] {idBeneficiario}, new PeticionRowMapper());
	       } catch (EmptyResultDataAccessException e) {
	           return new ArrayList<>();
	       }
	}

}
