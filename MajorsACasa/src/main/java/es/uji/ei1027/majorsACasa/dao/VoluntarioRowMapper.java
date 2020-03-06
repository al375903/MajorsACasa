package es.uji.ei1027.majorsACasa.dao;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.majorsACasa.model.Voluntario;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class VoluntarioRowMapper implements RowMapper<Voluntario>{
	public Voluntario mapRow(ResultSet rs, int rowNum) throws SQLException{
		Voluntario voluntario = new Voluntario();
		voluntario.setIdVoluntario(rs.getString("idVoluntario"));
		voluntario.setNombre(rs.getString("nombre"));
		voluntario.setEmail(rs.getString("email"));
		voluntario.setHobby(rs.getString("hobby"));
		// Aquí debería haber 3 sentencias para los atributos de tipo Date y Time
		voluntario.setAceptado(rs.getBoolean("aceptado"));
		voluntario.setGenero(rs.getString("genero"));
		voluntario.setEdad(rs.getInt("edad"));
		voluntario.setContrasenya(rs.getString("contrasenya"));
		return voluntario;
	}
}
