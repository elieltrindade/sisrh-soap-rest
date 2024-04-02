package sisrh.soap;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.WebServiceContext;

import segurança.Autenticador;
import sisrh.dto.Empregado;
import sisrh.dto.Empregados;
import sisrh.banco.Banco;

@WebService
@SOAPBinding(style = Style.RPC)
public class ServicoEmpregado {
	
	@Resource
	WebServiceContext context;
	
	@WebMethod(action = "listar")
	public Empregados listar() throws Exception {
		
		Autenticador.autenticarUsuarioSenha(context);
		
		Empregados empregados = new Empregados();
		
		List<Empregado> lista = Banco.listarEmpregados();
		for(Empregado emp: lista) {
			empregados.getEmpregados().add(emp);
		}
		return empregados;
	}
	
	@WebMethod(action = "listarAtivos")
	public Empregados listarAtivos() throws Exception {
	    Autenticador.autenticarUsuarioSenha(context);
	    
	    Empregados empregados = new Empregados();
	    
	    List<Empregado> lista = Banco.listarEmpregadosAtivos();
	    for (Empregado emp : lista) {
	        empregados.getEmpregados().add(emp);
	    }
	    
	    return empregados;
	}

	@WebMethod(action = "listarInativos")
	public Empregados listarInativos() throws Exception {
	    Autenticador.autenticarUsuarioSenha(context);
	    
	    Empregados empregados = new Empregados();
	    
	    List<Empregado> lista = Banco.listarEmpregadosInativos();
	    for (Empregado emp : lista) {
	        empregados.getEmpregados().add(emp);
	    }
	    
	    return empregados;
	}

}

