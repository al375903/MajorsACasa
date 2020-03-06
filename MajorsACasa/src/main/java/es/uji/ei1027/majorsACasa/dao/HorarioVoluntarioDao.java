package es.uji.ei1027.majorsACasa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.majorsACasa.model.HorarioVoluntario;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


public class HorarioVoluntarioDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	void addHorarioVoluntario(HorarioVoluntario horarioVoluntario) {
		jdbcTemplate.update("INSERT INTO HorarioVoluntario VALUES(?,?,?,?,?,?,?)",
			horarioVoluntario.getIdHorario(), horarioVoluntario.getIdVoluntario(),
			horarioVoluntario.getIdBeneficiario(), null, null,
			null, horarioVoluntario.isLibre());
	}
	
	void deleteHorarioVoluntario(HorarioVoluntario horarioVoluntario) {
		jdbcTemplate.update("DELETE FROM HorarioVoluntario WHERE idHorario=?", horarioVoluntario.getIdHorario());
	}
	
	void updateHorarioVoluntario(HorarioVoluntario horarioVoluntario) {
		jdbcTemplate.update("UPDATE HorarioVoluntario SET fecha=?, horaInicio=?, horaFin=?, libre=? WHERE idHorario=?",
				null, null, null, horarioVoluntario.isLibre());
	}
	
	HorarioVoluntario getHorarioVoluntario(String idHorario) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM HorarioVoluntario WHERE idHorario=?",
					new HorarioVoluntarioRowMapper(), idHorario);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	List<HorarioVoluntario> getHorariosVoluntarios(){
		try {
			return jdbcTemplate.query("SELECT * FROM HorarioVoluntario", new HorarioVoluntarioRowMapper());
		} catch(EmptyResultDataAccessException e) {
			return new ArrayList<HorarioVoluntario>();
		}
	}
	
}











