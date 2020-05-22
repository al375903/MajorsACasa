package es.uji.ei1027.majorsACasa.services;
import java.util.Map;

import es.uji.ei1027.majorsACasa.model.Empresa;

import java.util.List;
public interface ContratoService {

	public String getEmpresaTipoServicio(String idEmpresa);
	public boolean getIdEmpresa(String id);
	
	public Map<String,List<Empresa>> getContratoByTipo(String idEmpresa);
	public List<Empresa> getEmpresasByTipo(String tipo);
}
