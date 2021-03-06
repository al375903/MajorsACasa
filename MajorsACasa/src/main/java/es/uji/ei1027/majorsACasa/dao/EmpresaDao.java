package es.uji.ei1027.majorsACasa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.majorsACasa.model.Beneficiario;
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
	       jdbcTemplate.update("INSERT INTO Empresa VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
	              empresa.getIdEmpresa(), empresa.getNombreEmpresa(), 
	              empresa.getTipoServicio(), empresa.getNombreManager(), 
	              empresa.getDireccion(), empresa.getTelefono(), 
	              empresa.getHorarioAtencionCliente(), empresa.getEmailManager());
	   }

	   /* Esborra la empresa de la base de dades */
	   public void deleteEmpresa(String idEmpresa) {
		   jdbcTemplate.update("DELETE FROM Empresa WHERE idEmpresa=?",
	                           idEmpresa);
	   }

	   /* Actualitza els atributs de la empresa */
	   public void updateEmpresa(Empresa empresa) {
	       jdbcTemplate.update("UPDATE Empresa SET nombreEmpresa=?, tipoServicio=?, "
	       		+ "nombreManager=?, " + "direccion=?, telefono=?, horarioAtencionCliente=?, "
	       		+ "emailManager=? WHERE idEmpresa=?",
	    		   empresa.getNombreEmpresa(), empresa.getTipoServicio(), empresa.getNombreManager(), 
	    		   empresa.getDireccion(), empresa.getTelefono(), empresa.getHorarioAtencionCliente(), 
	    		   empresa.getEmailManager(), empresa.getIdEmpresa());
	   }

	   /* Obté empresa amb el id donat. Torna null si no existeix. */
	   public Empresa getEmpresa(String idEmpresa) {
	       try {
	           return jdbcTemplate.queryForObject("SELECT * FROM Empresa WHERE idEmpresa=?",
	                   new EmpresaRowMapper(), idEmpresa);
	       }
	       catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	   }

	   /* Obté totes les empreses. Torna una llista buida si no n'hi ha cap. */
	   public List<Empresa> getEmpresas() {
	       try {
	           return jdbcTemplate.query("SELECT * FROM Empresa",
	                   new EmpresaRowMapper());
	       } catch (EmptyResultDataAccessException e) {
	           return new ArrayList<Empresa>();
	       }
	   }
	   
	   public Empresa getEmpresaByNombre(String nombreEmpresa) {
	       try {
	           return jdbcTemplate.queryForObject("SELECT * FROM Empresa WHERE nombreEmpresa=?",
	                   new EmpresaRowMapper(), nombreEmpresa);
	       }
	       catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	   }
	   
	   public List<Empresa> getEmpresaPorId(String id) {
			try {
				return this.jdbcTemplate.query(
						"SELECT * FROM empresa WHERE idEmpresa=?",
						new Object[] {id}, new EmpresaRowMapper());
			}
			catch(EmptyResultDataAccessException e) {
			    return new ArrayList<>();
			}
		}
	   
	   public List<Beneficiario> getBeneficiariosPorContrato(String idContrato) {
			try {
				return this.jdbcTemplate.query(
						"SELECT beneficiario.* FROM beneficiario JOIN peticion USING(idBeneficiario) WHERE peticion.idContrato=?",
						new Object[] {idContrato}, new BeneficiarioRowMapper());
			}
			catch(EmptyResultDataAccessException e) {
			    return new ArrayList<>();
			}
		}
	   
}
