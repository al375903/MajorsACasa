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
 		String tipoServicio = "";
		for(Contrato contrato : listaContrato) {
			List<Empresa> listaEmpresa = empresaDao.getEmpresaPorId(contrato.getIdEmpresa());
			for(Empresa empresa: listaEmpresa) {
				if(contrato.getIdEmpresa().equals(empresa.getIdEmpresa())) {
					tipoServicio = tipoServicio + empresa.getTipoServicio();
				}
			}
		}
		return tipoServicio;
	}
	
	public boolean getIdEmpresa(String id) {
		List<Contrato> listaContrato = contratoDao.getContratoPorId(id);
		boolean existe = false;
		for(Contrato contrato : listaContrato) {
			List<Empresa> listaEmpresa = empresaDao.getEmpresas();
			for(Empresa empresa: listaEmpresa) {
				if(contrato.getIdEmpresa().equals(empresa.getIdEmpresa())) {
					existe = true;
				}
			}
		}
		return existe;
	}
	
}
