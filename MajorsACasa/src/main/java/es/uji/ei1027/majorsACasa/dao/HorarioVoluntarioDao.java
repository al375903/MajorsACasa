package es.uji.ei1027.majorsACasa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.majorsACasa.model.HorarioVoluntario;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HorarioVoluntarioDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void addHorarioVoluntario(HorarioVoluntario horarioVoluntario) {
		jdbcTemplate.update("INSERT INTO HorarioVoluntario VALUES(?,?,?,?,?,?,?)",
			horarioVoluntario.getIdHorario(), horarioVoluntario.getIdVoluntario(),
			horarioVoluntario.getIdBeneficiario(), horarioVoluntario.getFecha(), 
			horarioVoluntario.getHoraInicio(), horarioVoluntario.getHoraFin(), 
			horarioVoluntario.isLibre());
	}
	
	public void deleteHorarioVoluntario(String idHorario) {
		jdbcTemplate.update("DELETE FROM HorarioVoluntario WHERE idHorario=?", idHorario);
	}
	
	public void updateHorarioVoluntario(HorarioVoluntario horarioVoluntario) {
		jdbcTemplate.update("UPDATE HorarioVoluntario SET fecha=?, horaInicio=?, "
				+ "horaFin=?, libre=? WHERE idHorario=?",
				horarioVoluntario.getFecha(), horarioVoluntario.getHoraInicio(), 
				horarioVoluntario.getHoraFin(), horarioVoluntario.isLibre(), 
				horarioVoluntario.getIdHorario());
	}
	
	public HorarioVoluntario getHorarioVoluntario(String idHorario) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM HorarioVoluntario WHERE idHorario=?",
					new HorarioVoluntarioRowMapper(), idHorario);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<HorarioVoluntario> getHorariosVoluntarios(){
		try {
			return jdbcTemplate.query("SELECT * FROM HorarioVoluntario", new HorarioVoluntarioRowMapper());
		} catch(EmptyResultDataAccessException e) {
			return new ArrayList<HorarioVoluntario>();
		}
	}
	
}











