package br.com.marcooliveira.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController //PARA PERMITIR A CRIAÇÃO DE API.
@RequestMapping("/users") //ROTA QUE O BROWSER USARÁ PARA ACESSAR ESTA PARTE DA APLICAÇÃO.
public class UserController { //CRIAÇÃO DA CLASSE.
    
    @Autowired //PARA O SPRING GERENCIAR O CICLO DE VIDA.
    private InterfaceUserRepository userRepository; //INSTANCIANDO/CHAMANDO A INTERFACE/REPOSITORIO.

    //CRIAÇÃO DO MÉTODO DE CADASTRO.
    @PostMapping("/")    
    public ResponseEntity create(@RequestBody UserModel userModel){ /*ResponseEntity PARA RETORNAR
    TANTO OS CASOS DE ERROS QUANTO OS CASOS DE SUCESSO.*/

        //PARA VERIFICAR SE O USUÁRIO JÁ EXISTE E ARMAZENARÁ NA VARIÁVEL VAR.
        var user = this.userRepository.findByUserName(userModel.getUserName()); 

        //CONDICIONAL PARA CASO O USUÁRIO JÁ EXISTA, RETORNAR UMA MSG INFORMANDO.
        if(user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }

        //PARA CRIPTOGRAFAR A SENHA.
        var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashred);

        var userCreated = this.userRepository.save(userModel); //IRÁ RETORNAR A ENTIDADE CRIADA.
        return ResponseEntity.status(HttpStatus.OK).body(userCreated); 
        /*IRÁ RETORNAR O USUÁRIO QUE FOI CRIADO.*/

    }
    
}
