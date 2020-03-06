package es.uji.ei1027.majorsACasa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.majorsACasa.model.Beneficiario;

@Repository
public class BeneficiarioDao {
	private JdbcTemplate jdbcTemplate;

	   @Autowired
	   public void setDataSource(DataSource dataSource) {
	       jdbcTemplate = new JdbcTemplate(dataSource);
	   }

	   /* Afegeix la beneficiari a la base de dades */
	   public void addBeneficiario(Beneficiario beneficiario) {
	       jdbcTemplate.update("INSERT INTO Beneficiario VALUES(?, ?, ?, ?, ?, ?, ?)",
	              beneficiario.getIdBeneficiario(), beneficiario.getNombre(), beneficiario.getEmail(), beneficiario.getDireccion(), beneficiario.getGenero(), beneficiario.getEdad(), beneficiario.getContrasenya());
	   }

	   /* Esborra la beneficiari de la base de dades */
	   public void deleteBeneficiario(String idBeneficiario) {
	       jdbcTemplate.update("DELETE from Beneficiario where idBeneficiario=?",
	                           idBeneficiario);
	   }

	   /* Actualitza els atributs del beneficiari */
	   public void updateBeneficiario(Beneficiario beneficiario) {
	       jdbcTemplate.update("UPDATE Beneficiario SET nombre=?, email=?, direccion=?, genero=?, edad=?, contrasenya=? where idBeneficiario=?",
	    		   beneficiario.getNombre(), beneficiario.getEmail(), beneficiario.getDireccion(), beneficiario.getGenero(), beneficiario.getEdad(), beneficiario.getContrasenya(), beneficiario.getIdBeneficiario());
	   }

	   /* Obté beneficiari amb el id donat. Torna null si no existeix. */
	   public Beneficiario getBeneficiario(String idBeneficiario) {
	       try {
	           return jdbcTemplate.queryForObject("SELECT * from Beneficiario WHERE idBeneficiario=?",
	                   new BeneficiarioRowMapper(), idBeneficiario);
	       }
	       catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	   }

	   /* Obté tots els beneficiaris. Torna una llista buida si no n'hi ha cap. */
	   public List<Beneficiario> getBeneficiarios() {
	       try {
	           return jdbcTemplate.query("SELECT * from Beneficiario",
	                   new BeneficiarioRowMapper());
	       } catch (EmptyResultDataAccessException e) {
	           return new ArrayList<Beneficiario>();
	       }
	   }
}
