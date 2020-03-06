package es.uji.ei1027.majorsACasa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import es.uji.ei1027.majorsACasa.model.Contrato;

public final class ContratoRowMapper implements RowMapper<Contrato>{
	public Contrato mapRow(ResultSet rs, int rowNum) throws SQLException {
	       Contrato contrato = new Contrato();
	       
	       return contrato;
	   }
}
