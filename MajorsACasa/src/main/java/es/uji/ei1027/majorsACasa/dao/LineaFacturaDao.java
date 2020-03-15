package es.uji.ei1027.majorsACasa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.majorsACasa.model.LineaFactura;

@Repository
public class LineaFacturaDao {
	private JdbcTemplate jdbcTemplate;

	   @Autowired
	   public void setDataSource(DataSource dataSource) {
	       jdbcTemplate = new JdbcTemplate(dataSource);
	   }

	   /* Afegeix la linea factura a la base de dades */
	   public void addLineaFactura(LineaFactura lineaFactura) {
	       jdbcTemplate.update("INSERT INTO LineaFactura VALUES(?, ?, ?)",
	              lineaFactura.getIdFactura(), lineaFactura.getIdPeticion(), lineaFactura.getCodigoLinea());
	   }

	   /* Esborra la linea factura de la base de dades */
	   public void deleteLineaFactura(String idFactura, String idPeticion, String codigoLinea) {
	       jdbcTemplate.update("DELETE from LineaFactura WHERE idFactura=? AND idPeticion=? AND codigoLinea=?",
	                           idFactura, idPeticion, codigoLinea);
	   }

	   /* la clave primaria la forman todos los atributos, no pueden modificarse las lineas factura */
	   /*public void updateBeneficiario(LineaFactura lineaFactura) {
	       jdbcTemplate.update("UPDATE LineaFactura SET codigoLinea=? where idFactura=? and idPeticion=?",
	    		   lineaFactura.getCodigoLinea(), lineaFactura.getIdFactura(), lineaFactura.getIdPeticion());
	   }*/

	   /* Obté linea factura amb el id donat. Torna null si no existeix. */
	   public LineaFactura getLineaFactura(String idFactura, String idPeticion, String codigoLinea) {
	       try {
	           return jdbcTemplate.queryForObject("SELECT * from LineaFactura WHERE idFactura=? "
	           		+ "AND idPeticion=? AND codigoLinea=?",
	                   new LineaFacturaRowMapper(), idFactura, idPeticion, codigoLinea);
	       }
	       catch(EmptyResultDataAccessException e) {
	           return null;
	       }
	   }

	   /* Obté totes les línies de factura. Torna una llista buida si no n'hi ha cap. */
	   public List<LineaFactura> getLineasFactura() {
	       try {
	           return jdbcTemplate.query("SELECT * from LineaFactura",
	                   new LineaFacturaRowMapper());
	       } catch (EmptyResultDataAccessException e) {
	           return new ArrayList<LineaFactura>();
	       }
	   }
}
