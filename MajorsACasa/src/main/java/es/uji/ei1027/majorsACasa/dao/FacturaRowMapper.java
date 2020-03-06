package es.uji.ei1027.majorsACasa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import es.uji.ei1027.majorsACasa.model.Factura;

public final class FacturaRowMapper implements RowMapper<Factura>{
	public Factura mapRow(ResultSet rs, int rowNum) throws SQLException{
		Factura factura = new Factura();
		factura.setIdFactura(rs.getString("idFactura"));
		factura.setIdBeneficiario(rs.getString("idBeneficiario"));
		// Aquí debería haber 3 sentencias para los atributos de tipo Date y Time
		factura.setCantidad(rs.getFloat("cantidad"));
		factura.setConcepto(rs.getString("concepto"));
		return factura;
	}
}
