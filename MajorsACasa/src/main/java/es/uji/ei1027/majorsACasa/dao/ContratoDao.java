package es.uji.ei1027.majorsACasa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.majorsACasa.model.Contrato;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContratoDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	void addContrato(Contrato contrato) {
		jdbcTemplate.update("INSERT INTO Contrato VALUES(?,?,?,?,?,?)",
				contrato.getIdContrato(), contrato.getIdEmpresa(), contrato.getPrecio(),
				contrato.getTipoServicio(), null, null);
	}
	
	void deleteContrato(Contrato contrato) {
		jdbcTemplate.update("DELETE FROM Contrato WHERE idContrato=?", contrato.getIdContrato());
	}
	
	void updateContrato(Contrato contrato) {
		jdbcTemplate.update("UPDATE Contrato SET precio=?, tipoServicio=?, fechaInicio=?, "
				+ "fechaFin=? WHERE idContrato=?",
				contrato.getPrecio(), contrato.getTipoServicio(), null, null, contrato.getIdContrato());
	}
	
	Contrato getContrato(String idContrato) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM Contrato WHERE idContrato=?",
					new ContratoRowMapper(), idContrato);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	List<Contrato> getContratos(){
		try {
			return jdbcTemplate.query("SELECT * FROM Contrato", new ContratoRowMapper());
		} catch(EmptyResultDataAccessException e) {
			return new ArrayList<Contrato>();
		}
	}
}






