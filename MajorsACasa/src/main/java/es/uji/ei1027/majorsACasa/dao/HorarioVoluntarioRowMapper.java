package es.uji.ei1027.majorsACasa.dao;

import org.springframework.jdbc.core.RowMapper;

import es.uji.ei1027.majorsACasa.model.HorarioVoluntario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public final class HorarioVoluntarioRowMapper implements RowMapper<HorarioVoluntario>{
	public HorarioVoluntario mapRow(ResultSet rs, int rowNum) throws SQLException{
		HorarioVoluntario horarioVoluntario = new HorarioVoluntario();
		horarioVoluntario.setIdHorario(rs.getString("idHorario"));
		horarioVoluntario.setIdVoluntario(rs.getString("idVoluntario"));
		horarioVoluntario.setIdBeneficiario(rs.getString("idBeneficiario"));
	    horarioVoluntario.setFecha(rs.getObject("fecha", LocalDate.class));
	    horarioVoluntario.setHoraInicio(rs.getObject("horaInicio", LocalTime.class));
	    horarioVoluntario.setHoraFin(rs.getObject("horaFin", LocalTime.class));
	    horarioVoluntario.setLibre(rs.getBoolean("libre"));
		return horarioVoluntario;
	}
}
