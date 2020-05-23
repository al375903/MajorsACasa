package es.uji.ei1027.majorsACasa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uji.ei1027.majorsACasa.dao.ContratoDao;
import es.uji.ei1027.majorsACasa.model.Contrato;

@Service
public class ContratoSrv implements ContratoService{
	
	@Autowired
	ContratoDao contratoDao;

	@Override
	public List<Contrato> getContratosDeUnaEmpresa(String nombreEmpresa) {
		List<Contrato> lista = contratoDao.getContratoEmpresa(nombreEmpresa);
		return lista;
	}
	
	
	
}
