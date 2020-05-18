package es.uji.ei1027.majorsACasa.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<Contrato> listaContrato = contratoDao.getContratoEmpresa(id);
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
		List<Contrato> listaContrato = contratoDao.getContratoEmpresa(id);
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
	
	public Map<String,List<Empresa>> getContratoByTipo(String idEmpresa){
		List<Contrato> contrEmpresa = contratoDao.getContratoEmpresa(idEmpresa);
		HashMap<String,List<Empresa>> empresaPorTipo = new HashMap<String,List<Empresa>>();
		for (Contrato c: contrEmpresa) {
			Empresa empresa = empresaDao.getEmpresa(c.getIdEmpresa());
			if(!empresaPorTipo.containsKey(empresa.getTipoServicio()))
				empresaPorTipo.put(empresa.getTipoServicio(), new ArrayList<Empresa>());
			empresaPorTipo.get(empresa.getTipoServicio()).add(empresa);
		}
		return empresaPorTipo;
	}
	
}
