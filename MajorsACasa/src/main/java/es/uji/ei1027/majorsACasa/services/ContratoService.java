package es.uji.ei1027.majorsACasa.services;

import es.uji.ei1027.majorsACasa.model.Contrato;

import java.util.List;
public interface ContratoService {
	public List<Contrato> getContratosDeUnaEmpresa(String nombreEmpresa);
}
