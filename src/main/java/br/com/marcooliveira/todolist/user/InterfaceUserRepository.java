package br.com.marcooliveira.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;




public interface InterfaceUserRepository extends JpaRepository <UserModel, UUID> { /*ESTE REPOSITÓRIO
ESTÁ REPRESENTANDO A CLASSE "UserModel", E O ID DESTA CLASSE QUE ELE ESTÁ REPRESENTANDO É DO TIPO UUID*/

    //MÉTODO PARA PROCURAR O ATRIBUTO userName PARA QUE ELE NÃO SE REPITA NO BD.
    UserModel findByUserName(String userName);
    
}
