package es.uji.ei1027.majorsACasa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;
import es.uji.ei1027.majorsACasa.model.Contrato;

public final class ContratoRowMapper implements RowMapper<Contrato>{
	public Contrato mapRow(ResultSet rs, int rowNum) throws SQLException {
	       Contrato contrato = new Contrato();
	       contrato.setIdContrato(rs.getString("idContrato"));
	       contrato.setIdEmpresa(rs.getString("idEmpresa"));
	       contrato.setPrecio(rs.getFloat("precio"));
	       contrato.setFechaInicio(rs.getObject("fechaInicio", LocalDate.class));
	       contrato.setFechaFin(rs.getObject("fechaFin", LocalDate.class));
	       return contrato;
	   }
}
