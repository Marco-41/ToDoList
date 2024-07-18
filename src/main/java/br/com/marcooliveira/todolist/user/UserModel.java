package br.com.marcooliveira.todolist.user;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data /*LIB DO LOMBOK PARA GERAR OS GETTERS E SETTERS IMPLICITAMENTE, SEM PRECISAR DECLARÁ-LOS NO 
BODY DA CLASSE*/
@Entity(name = "tb_users") //PARA ESPECIFICAR QUE ESTA CLASSE SERÁ UMA TABELA NO BD.
public class UserModel {

    @Id
    @GeneratedValue //PARA GERAR O VALOR DO ID DE FORMA AUTOMÁTICA. 
    private UUID id; // CHAVE PRIMÁRIA DA TABELA.

    private String userName;
    private String name;
    private String password;

}
