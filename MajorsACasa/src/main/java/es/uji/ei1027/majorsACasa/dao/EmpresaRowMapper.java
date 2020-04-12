package es.uji.ei1027.majorsACasa.dao;


import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.majorsACasa.model.Empresa;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class EmpresaRowMapper implements RowMapper<Empresa>{
	public Empresa mapRow(ResultSet rs, int rowNum) throws SQLException {
	       Empresa empresa = new Empresa();
	       empresa.setIdEmpresa(rs.getString("idEmpresa"));
	       empresa.setNombreEmpresa(rs.getString("nombreEmpresa"));
	       empresa.setTipoServicio(rs.getString("tipoServicio"));
	       empresa.setNombreManager(rs.getString("nombreManager"));
	       empresa.setDireccion(rs.getString("direccion"));
	       empresa.setTelefono(rs.getString("telefono"));
	       empresa.setHorarioAtencionCliente(rs.getString("horarioAtencionCliente"));
	       empresa.setEmailManager(rs.getString("emailManager"));
	       return empresa;
	   }
}