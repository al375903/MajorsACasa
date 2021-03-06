package es.uji.ei1027.majorsACasa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.majorsACasa.model.Contrato;
import es.uji.ei1027.majorsACasa.model.Empresa;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContratoDao {

	private JdbcTemplate jdbcTemplate;
	public EmpresaDao empresaDao;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void addContrato(Contrato contrato) {
		jdbcTemplate.update("INSERT INTO Contrato VALUES(?,?,?,?,?)",
				contrato.getIdContrato(), contrato.getNombreEmpresa(), 
				contrato.getPrecio(), contrato.getFechaInicio(), contrato.getFechaFin());
	}
	
	 public void addEmpresa(Empresa empresa) {
	       jdbcTemplate.update("INSERT INTO Empresa VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
	              empresa.getIdEmpresa(), empresa.getNombreEmpresa(), 
	              empresa.getTipoServicio(), empresa.getNombreManager(), 
	              empresa.getDireccion(), empresa.getTelefono(), 
	              empresa.getHorarioAtencionCliente(), empresa.getEmailManager());
	   }
	
	public void deleteContrato(String idContrato) {
		jdbcTemplate.update("DELETE FROM Contrato WHERE idContrato=?", idContrato);
	}
	
	public void updateContrato(Contrato contrato) {
		jdbcTemplate.update("UPDATE Contrato SET precio=?, fechaInicio=?, "
				+ "fechaFin=? WHERE idContrato=?",
				contrato.getPrecio(), contrato.getFechaInicio(), 
				contrato.getFechaFin(), contrato.getIdContrato());
	}
	
	public Contrato getContrato(String idContrato) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM Contrato WHERE idContrato=?",
					new ContratoRowMapper(), idContrato);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public Contrato getContratoYTipo(String idContrato) {
		try {
			return jdbcTemplate.queryForObject("SELECT Contrato.*, Empresa.* FROM Contrato JOIN Empresa USING (nombreEmpresa) WHERE idContrato=?",
					new ContratoRowMapper(), idContrato);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Contrato> getContratos(){
		try {
			return jdbcTemplate.query("SELECT Contrato.*, Empresa.* FROM Contrato JOIN Empresa USING (nombreEmpresa)", new ContratoRowMapper());
		} catch(EmptyResultDataAccessException e) {
			return new ArrayList<Contrato>();
		}
	}
	
	public List<Contrato> getContratoEmpresa(String nombreEmpresa) {
		try {
			return this.jdbcTemplate.query(
					"SELECT Contrato.*, Empresa.* FROM Contrato JOIN Empresa USING (nombreEmpresa) WHERE nombreEmpresa=?",//"SELECT * FROM contrato WHERE nombreEmpresa=?"
					new Object[] {nombreEmpresa}, new ContratoRowMapper());
		}
		catch(EmptyResultDataAccessException e) {
		    return new ArrayList<>();
		}
	}

}




