package br.com.marcooliveira.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.marcooliveira.todolist.user.InterfaceUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component //PARA O SPRING GERENCIAR ESTA CLASSE.
public class FilterTaskAuth extends OncePerRequestFilter{

    @Autowired
    private InterfaceUserRepository interfaceUserRepository;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                var servletPath = request.getServletPath();

                if(servletPath.equals("/tasks/")){

                    //PEGAR A AUTENTICAÇÃO (USUÁRIO E SENHA)
                    var authorization = request.getHeader("Authorization");

                    var authEncoded = authorization.substring("Basic".length()).trim(); /*SUBSTRING PARA EXTRAIR UMA
                    PARTE DE UM TEXTO, E O TRIM PARA REMOVER OS ESPAÇOS DA STRING.*/

                    byte[] authDecode = Base64.getDecoder().decode(authEncoded);

                    var authString = new String(authDecode);

                    String[] credentials = authString.split(":");
                    String username = credentials[0];
                    String password = credentials[1];

                    //VALIDAR USUÁRIO.
                    var user = this.interfaceUserRepository.findByUserName(username);

                    if(user == null){
                        response.sendError(401);
                    }
                    else{
                    //VALIDAR A SENHA.

                    var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                    
                    if(passwordVerify.verified){
                        //SEGUE O SEU CAMINHO.
                        request.setAttribute("idUser", user.getId());
                        //SE ESTIVER TUDO OK, SEGUIRÁ O SEU CAMINHO.
                        filterChain.doFilter(request, response);
                    }
                    else{
                        response.sendError(401);
                    }

                    }

                } 
                else{
                    //SE ESTIVER TUDO OK, SEGUIRÁ O SEU CAMINHO.
                    filterChain.doFilter(request, response);
                }

            }
    
}
