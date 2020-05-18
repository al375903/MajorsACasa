package es.uji.ei1027.majorsACasa.services;
import java.util.Map;

import es.uji.ei1027.majorsACasa.model.Empresa;

import java.util.List;
public interface ContratoService {

	public String getEmpresaTipoServicio(String id);
	public boolean getIdEmpresa(String id);
	
	public Map<String,List<Empresa>> getContratoByTipo(String idEmpresa);
}
