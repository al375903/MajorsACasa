package es.uji.ei1027.majorsACasa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import es.uji.ei1027.majorsACasa.model.Peticion;

public final class PeticionRowMapper implements RowMapper<Peticion>{
	public Peticion mapRow(ResultSet rs, int rowNum) throws SQLException {
		Peticion peticion = new Peticion();
	       
	       return peticion;
	   }
}