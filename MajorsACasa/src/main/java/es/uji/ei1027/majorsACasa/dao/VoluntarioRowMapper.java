package es.uji.ei1027.majorsACasa.dao;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.majorsACasa.model.Voluntario;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class VoluntarioRowMapper implements RowMapper<Voluntario>{
	public Voluntario mapRow(ResultSet rs, int rowNum) throws SQLException{
		Voluntario voluntario = new Voluntario();
		voluntario.setIdVoluntario(rs.getString("idVoluntario"));
		voluntario.setNombre(rs.getString("nombre"));
		voluntario.setEmail(rs.getString("email"));
		voluntario.setDireccion(rs.getString("direccion"));
		voluntario.setHobbies(rs.getString("hobbies"));
		voluntario.setFechaPeticionVoluntariado(rs.getObject("fechaPeticionVoluntariado", LocalDate.class));
		voluntario.setFechaAceptacionVoluntariado(rs.getObject("fechaAceptacionVoluntariado", LocalDate.class));
		voluntario.setFechaFin(rs.getObject("fechaFin", LocalDate.class));
		voluntario.setAceptado(rs.getString("aceptado"));
		voluntario.setGenero(rs.getString("genero"));
		voluntario.setEdad(rs.getInt("edad"));
		voluntario.setContrasenya(rs.getString("contrasenya"));
		return voluntario;
	}
}
