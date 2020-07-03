package es.uji.ei1027.majorsACasa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.majorsACasa.model.Beneficiario;
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
		jdbcTemplate.update("INSERT INTO HorarioVoluntario VALUES(DEFAULT,?,?,?,?,?,?)",
			horarioVoluntario.getIdVoluntario(),
			horarioVoluntario.getIdBeneficiario(), horarioVoluntario.getFecha(), 
			horarioVoluntario.getHoraInicio(), horarioVoluntario.getHoraFin(), 
			horarioVoluntario.isLibre());
	}
	
	public void deleteHorarioVoluntario(int idHorario) {
		jdbcTemplate.update("DELETE FROM HorarioVoluntario WHERE idHorario=?", idHorario);
	}
	
	public void updateHorarioVoluntario(HorarioVoluntario horarioVoluntario) {
		jdbcTemplate.update("UPDATE HorarioVoluntario SET fecha=?, horaInicio=?, "
				+ "horaFin=?, libre=? WHERE idHorario=?",
				horarioVoluntario.getFecha(), horarioVoluntario.getHoraInicio(), 
				horarioVoluntario.getHoraFin(), horarioVoluntario.isLibre(), 
				horarioVoluntario.getIdHorario());
	}
	
	public HorarioVoluntario getHorarioVoluntario(int idHorario) {
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
	
	public List<HorarioVoluntario> getHorariosDeUnVoluntario(String idVoluntario){
		try {
			return jdbcTemplate.query("SELECT * FROM HorarioVoluntario WHERE idVoluntario=?",
					new Object[] {idVoluntario}, new HorarioVoluntarioRowMapper());
		} catch(EmptyResultDataAccessException e) {
			return new ArrayList<HorarioVoluntario>();
		}
	}
	
	public List<Beneficiario> getBeneficiariosPorHorario(int idHorario) {
		try {
			return this.jdbcTemplate.query(
					"SELECT beneficiario.* FROM beneficiario JOIN HorarioVoluntario USING(idBeneficiario) WHERE HorarioVoluntario.idHorario=?",
					new Object[] {idHorario}, new BeneficiarioRowMapper());
		}
		catch(EmptyResultDataAccessException e) {
		    return new ArrayList<>();
		}
	}
	
	public List<HorarioVoluntario> getHorariosVoluntariosLibres(){
		try {
			return jdbcTemplate.query("SELECT * FROM HorarioVoluntario WHERE libre='t'", new HorarioVoluntarioRowMapper());
		} catch(EmptyResultDataAccessException e) {
			return new ArrayList<HorarioVoluntario>();
		}
	}
	
}











