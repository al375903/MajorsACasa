package es.uji.ei1027.majorsACasa.dao;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.majorsACasa.model.Beneficiario;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class BeneficiarioRowMapper implements RowMapper<Beneficiario>{
	public Beneficiario mapRow(ResultSet rs, int rowNum) throws SQLException {
	       Beneficiario beneficiario = new Beneficiario();
	       beneficiario.setIdBeneficiario(rs.getString("idBeneficiario"));
	       beneficiario.setNombre(rs.getString("nombre"));
	       beneficiario.setEmail(rs.getString("email"));
	       beneficiario.setDireccion(rs.getString("direccion"));
	       beneficiario.setGenero(rs.getString("genero"));
	       beneficiario.setEdad(rs.getInt("edad"));
	       beneficiario.setContrasenya(rs.getString("contrasenya"));
	       beneficiario.setHobbies(rs.getString("hobbies"));
	       beneficiario.setAlergias(rs.getString("alergias"));
	       beneficiario.setEnfermedades(rs.getString("enfermedades"));
	       return beneficiario;
	   }
}
