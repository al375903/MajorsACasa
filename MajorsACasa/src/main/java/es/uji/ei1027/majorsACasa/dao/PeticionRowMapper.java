package es.uji.ei1027.majorsACasa.dao;

import java.time.LocalDate;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import es.uji.ei1027.majorsACasa.model.Peticion;

public final class PeticionRowMapper implements RowMapper<Peticion>{
	public Peticion mapRow(ResultSet rs, int rowNum) throws SQLException {
			Peticion peticion = new Peticion();
			peticion.setIdPeticion(rs.getString("idPeticion"));
			peticion.setIdBeneficiario(rs.getString("idBeneficiario"));
			peticion.setIdContrato(rs.getString("idContrato"));
			peticion.setComentarios(rs.getString("comentarios"));
			peticion.setTipoServicio(rs.getString("tipoServicio"));
			peticion.setEstado(rs.getString("estado"));
			peticion.setFechaAprobacion(rs.getObject("fechaAprobacion", LocalDate.class));
			peticion.setFechaDenegacion(rs.getObject("fechaDenegacion", LocalDate.class));
			peticion.setFechaCreacion(rs.getObject("fechaCreacion", LocalDate.class));
			peticion.setFechaCancelacion(rs.getObject("fechaCancelacion", LocalDate.class));
			return peticion;
	}
}