package es.uji.ei1027.majorsACasa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import es.uji.ei1027.majorsACasa.model.Factura;

public final class FacturaRowMapper implements RowMapper<Factura>{
	public Factura mapRow(ResultSet rs, int rowNum) throws SQLException {
		Factura factura = new Factura();
	       
	       return factura;
	   }
}
