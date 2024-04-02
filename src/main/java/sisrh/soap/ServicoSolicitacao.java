// Classe ServicoSolicitacao
package sisrh.soap;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.WebServiceContext;

import segurança.Autenticador;
import sisrh.dto.Solicitacao;
import sisrh.dto.Solicitacoes;
import sisrh.dto.Usuario;
import sisrh.banco.Banco;

@WebService
@SOAPBinding(style = Style.RPC)
public class ServicoSolicitacao {
    
    @Resource
    WebServiceContext context;

    @WebMethod(action = "listarSolicitacoes")
    public Solicitacoes listarSolicitacoes() throws Exception {
        
        Autenticador.autenticarUsuarioSenha(context);
        
        String emailUsuarioLogado = Autenticador.getUsuario(context);
        
        Usuario usuarioLogado = Banco.buscarUsuarioPorEmail(emailUsuarioLogado);
        String matriculaUsuarioLogado = usuarioLogado.getMatricula();
        
        Solicitacoes solicitacoes = new Solicitacoes();
        
        List<Solicitacao> lista = Banco.listarSolicitacoes(matriculaUsuarioLogado);
        solicitacoes.setSolicitacoes(lista);
        
        return solicitacoes;
    }
}
