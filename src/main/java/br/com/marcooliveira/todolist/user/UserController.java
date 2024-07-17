package br.com.marcooliveira.todolist.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //PARA PERMITIR A CRIAÇÃO DE API.
@RequestMapping("/users") //ROTA QUE O BROWSER USARÁ PARA ACESSAR ESTA PARTE DA APLICAÇÃO.
public class UserController { //CRIAÇÃO DA CLASSE.
    
    //CRIAÇÃO DO MÉTODO DE CADASTRO.
    @PostMapping("/")    
    public void create(@RequestBody UserModel userModel){ //PARA INFORMAR QUE AS REQUISIÇÕES DA CLASSE UserModel SERÁ ENVIADA DENTRO DO BODY.

        System.out.println(userModel.getName());
        System.out.println(userModel.getUserName());

    }
    
}
