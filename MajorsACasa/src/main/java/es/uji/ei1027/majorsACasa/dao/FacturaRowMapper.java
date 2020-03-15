package es.uji.ei1027.majorsACasa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;
import es.uji.ei1027.majorsACasa.model.Factura;

public final class FacturaRowMapper implements RowMapper<Factura>{
	public Factura mapRow(ResultSet rs, int rowNum) throws SQLException{
		Factura factura = new Factura();
		factura.setIdFactura(rs.getString("idFactura"));
		factura.setIdBeneficiario(rs.getString("idBeneficiario"));
		factura.setFechaCreacion(rs.getObject("fechaCreacion", LocalDate.class));
		factura.setFechaInicio(rs.getObject("fechaInicio", LocalDate.class));
		factura.setFechaFin(rs.getObject("fechaFin", LocalDate.class));
		factura.setCantidad(rs.getFloat("cantidad"));
		factura.setConcepto(rs.getString("concepto"));
		return factura;
	}
}
