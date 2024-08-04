package br.com.marcooliveira.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data /*LIB DO LOMBOK PARA GERAR OS GETTERS E SETTERS IMPLICITAMENTE, SEM PRECISAR DECLARÁ-LOS NO 
BODY DA CLASSE*/
@Entity(name = "tb_users") //PARA ESPECIFICAR QUE ESTA CLASSE SERÁ UMA TABELA NO BD.
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")//PARA GERAR O VALOR DO ID DE FORMA AUTOMÁTICA E DO TIPO UUID.  
    private UUID id; // CHAVE PRIMÁRIA DA TABELA.

    //@Column(name = "usuario") //PARA ESPECIFICAR O NOME DA COLUNA NO BD.
    @Column(unique = true) /*VALIDAÇÃO PARA NÃO SER POSSÍVEL CADASTRAR UM NOME 
    QUE JÁ EXISTA NA TABELA DO BD.*/
    private String userName;

    @Column(name = "nome") //PARA ESPECIFICAR O NOME DA COLUNA NO BD.
    private String name;

    @Column(name = "senha") //PARA ESPECIFICAR O NOME DA COLUNA NO BD.
    private String password;

    //PARA ESPECIFICAR QUANDO O DADO NO BD FOI CRIADO.
    @CreationTimestamp
    private LocalDateTime createdAt;

}
