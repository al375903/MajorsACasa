package es.uji.ei1027.majorsACasa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import es.uji.ei1027.majorsACasa.model.LineaFactura;

public final class LineaFacturaRowMapper implements RowMapper<LineaFactura>{
	public LineaFactura mapRow(ResultSet rs, int rowNum) throws SQLException {
		   LineaFactura lineaFactura = new LineaFactura();
	       lineaFactura.setIdFactura(rs.getString("idFactura"));
	       lineaFactura.setIdPeticion(rs.getString("idPeticion"));
	       lineaFactura.setCodigoLinea(rs.getString("codigoLinea"));
	       return lineaFactura;
	   }
}
