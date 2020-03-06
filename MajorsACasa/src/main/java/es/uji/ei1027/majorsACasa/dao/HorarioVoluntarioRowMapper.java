package es.uji.ei1027.majorsACasa.dao;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.majorsACasa.model.HorarioVoluntario;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class HorarioVoluntarioRowMapper implements RowMapper<HorarioVoluntario>{
	public HorarioVoluntario mapRow(ResultSet rs, int rowNum) throws SQLException{
		HorarioVoluntario horarioVoluntario = new HorarioVoluntario();
		horarioVoluntario.setIdHorario(rs.getString("idHorario"));
		horarioVoluntario.setIdVoluntario(rs.getString("idVoluntario"));
		horarioVoluntario.setIdBeneficiario(rs.getString("idBeneficiario"));
		// Aquí debería haber 3 sentencias para los atributos de tipo Date y Time
		horarioVoluntario.setLibre(rs.getBoolean("libre"));
		return horarioVoluntario;
	}
}
