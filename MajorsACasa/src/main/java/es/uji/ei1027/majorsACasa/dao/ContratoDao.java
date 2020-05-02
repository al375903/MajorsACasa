package es.uji.ei1027.majorsACasa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.majorsACasa.model.Contrato;
import es.uji.ei1027.majorsACasa.model.Empresa;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class ContratoDao {

	private JdbcTemplate jdbcTemplate;
	public EmpresaDao empresaDao;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void addContrato(Contrato contrato, String tipoServicio) {
		jdbcTemplate.update("INSERT INTO Contrato VALUES(?,?,?,?,?,?)",
				contrato.getIdContrato(), contrato.getIdEmpresa(), 
				contrato.getPrecio(), tipoServicio, 
				contrato.getFechaInicio(), contrato.getFechaFin());
	}
	
	public void deleteContrato(String idContrato) {
		jdbcTemplate.update("DELETE FROM Contrato WHERE idContrato=?", idContrato);
	}
	
	public void updateContrato(Contrato contrato) {
		jdbcTemplate.update("UPDATE Contrato SET precio=?, tipoServicio=?, fechaInicio=?, "
				+ "fechaFin=? WHERE idContrato=?",
				contrato.getPrecio(), contrato.getTipoServicio(), contrato.getFechaInicio(), 
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
	
	public List<Contrato> getContratos(){
		try {
			return jdbcTemplate.query("SELECT * FROM Contrato", new ContratoRowMapper());
		} catch(EmptyResultDataAccessException e) {
			return new ArrayList<Contrato>();
		}
	}
	
	public List<Contrato> getContratoPorId(String id) {
		try {
			return this.jdbcTemplate.query(
					"SELECT * FROM contrato WHERE idContrato=?",
					new Object[] {id}, new ContratoRowMapper());
		}
		catch(EmptyResultDataAccessException e) {
		    return null;
		}
	}

}




