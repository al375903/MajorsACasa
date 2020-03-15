package es.uji.ei1027.majorsACasa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.majorsACasa.model.Factura;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FacturaDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	void addFactura(Factura factura) {
		jdbcTemplate.update("INSERT INTO Factura VALUES(?,?,?,?,?,?,?)",
				factura.getIdFactura(), factura.getIdBeneficiario(), 
				factura.getFechaCreacion(), factura.getFechaInicio(), 
				factura.getFechaFin(), factura.getCantidad(), factura.getConcepto());
	}
	
	void deleteFactura(String idFactura) {
		jdbcTemplate.update("DELETE FROM Factura WHERE idFactura=?", idFactura);
	}
	
	void updateFactura(Factura factura) {
		jdbcTemplate.update("UPDATE Factura SET fechaCreacion=?, fechaInicio=?, "
				+ "fechaFin=?, cantidad=?, concepto=? WHERE idFactura=?",
				factura.getFechaCreacion(), factura.getFechaInicio(), factura.getFechaFin(), 
				factura.getCantidad(), factura.getConcepto(), factura.getIdFactura());
	}
	
	Factura getFactura(String idFactura) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM Factura WHERE idFactura=?",
					new FacturaRowMapper(), idFactura);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	List<Factura> getFacturas(){
		try {
			return jdbcTemplate.query("SELECT * FROM Factura", new FacturaRowMapper());
		} catch(EmptyResultDataAccessException e) {
			return new ArrayList<Factura>();
		}
	}
}





