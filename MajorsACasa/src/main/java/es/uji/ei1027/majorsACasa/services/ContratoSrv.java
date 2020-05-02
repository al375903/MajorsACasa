package es.uji.ei1027.majorsACasa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uji.ei1027.majorsACasa.dao.ContratoDao;
import es.uji.ei1027.majorsACasa.dao.EmpresaDao;
import es.uji.ei1027.majorsACasa.model.Contrato;
import es.uji.ei1027.majorsACasa.model.Empresa;

@Service
public class ContratoSrv implements ContratoService{

	@Autowired
	EmpresaDao empresaDao;
	
	@Autowired
	ContratoDao contratoDao;
	
	@Override
	public String getEmpresaTipoServicio(String id) {
		List<Contrato> listaContrato = contratoDao.getContratoPorId(id);
 		String tipoServicio = null;
		for(Contrato contrato : listaContrato) {
			List<Empresa> listaEmpresa = empresaDao.getEmpresaPorId(id);
			for(Empresa empresa: listaEmpresa) {
				if(contrato.getIdEmpresa() == empresa.getIdEmpresa()) {
					tipoServicio = empresa.getTipoServicio();
				}
			}
		}
		return tipoServicio;
	}
	
}
