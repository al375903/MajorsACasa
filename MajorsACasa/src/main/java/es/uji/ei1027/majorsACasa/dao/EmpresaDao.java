package es.uji.ei1027.majorsACasa.dao;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.majorsACasa.model.Empresa;

@Repository
public class EmpresaDao {
	private JdbcTemplate jdbcTemplate;

	   @Autowired
	   public void setDataSource(DataSource dataSource) {
	       jdbcTemplate = new JdbcTemplate(dataSource);
	   }

	   /* Afegeix la empresa a la base de dades */
	   public void addEmpresa(Empresa empresa) {
	       jdbcTemplate.update("INSERT INTO Empresa VALUES(?, ?, ?, ?, ?, ?, ?)",
	              empresa.getIdEmpresa(), empresa.getNombreEmpresa(), empresa.getNombreManager(), empresa.getDireccion(), empresa.getTelefono(), empresa.getHorarioatencionCliente(), empresa.getEmailManager());
	   }

	   /* Esborra la empresa de la base de dades */
	   public void deleteEmpresa(String idEmpresa) {
	       jdbcTemplate.update("DELETE from Empresa where idEmpresa=?",
	                           idEmpresa);
	   }

	   /* Actualitza els atributs de la empresa */
	   public void updateEmpresa(Empresa empresa) {
	       jdbcTemplate.update("UPDATE Empresa SET nombreEmpresa=?, nombreManager=?, direccion=?, telefono=?, horarioAtencionCliente=?, emailManager=? where idEmpresa=?",
	    		   empresa.getNombreEmpresa(), empresa.getNombreManager(), empresa.getDireccion(), empresa.getTelefono(), empresa.getHorarioatencionCliente(), empresa.getEmailManager(), empresa.getIdEmpresa());
	   }

	   /* Obté empresa amb el id donat. Torna null si no existeix. */
	   public Empresa getEmpresa(String idEmpresa) {
	       try {
	           return jdbcTemplate.queryForObject("SELECT * from Empresa WHERE idEmpresa=?",
	                   new EmpresaRowMapper(), idEmpresa);
	       }
	       catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	   }

	   /* Obté totes les empreses. Torna una llista buida si no n'hi ha cap. */
	   public List<Empresa> getEmpresas() {
	       try {
	           return jdbcTemplate.query("SELECT * from Empresa",
	                   new EmpresaRowMapper());
	       } catch (EmptyResultDataAccessException e) {
	           return new ArrayList<Empresa>();
	       }
	   }
}
