package br.com.marcooliveira.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

//CLASSE PARA REPRESENTAR A TABELA DE TAREFAS NO BD.
    /*ID
     * USUÁRIO (ID_USUÁRIO)
     * DESCRIÇÃO
     * TÍTULO
     * DATA DE INÍCIO
     * DATA DE TÉRMINO
     * PRIORIDADE
     */
@Data //PARA GERAR OS GETTERS E SETTERS.
@Entity(name = "tb_tasks") //PARA ESPECÍFICAR QUE ESTA CLASSE SERÁ UMA TABELA NO BD.
public class TaskModel {

    @Id //CHAVE PRIMÁRIA DA TABELA.
    @GeneratedValue(generator = "UUID") //PARA O ID SER GERADO AUTOMATICAMENTE.
    private UUID id;
    
    private String description;

    @Column(length = 50) //PARA ESPECIFICAR A QUANTIDADE MÁXIMA DE CARACTERES PERMITIDA NO TITULO.
    private String title;

    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    private UUID idUser;

    //PARA ESPECIFICAR QUANDO O DADO FOI CRIADO NO BD.
    @CreationTimestamp
    private LocalDateTime createdAt; //ATRIBUTO QUE REGISTRARÁ QUANDO A TAREFA FOI CRIADA.

    public void setTitle(String title) throws Exception { /*O throws Exception IRÁ TRANFERIR
        A REPONSABILIDADE DE TRATAMENTO DE EXCEÇÃO PARA QUEM ESTIVER CHAMANDO O MÉTODO setTitle.*/

        //CONDICIONAL PARA CASO O TITULO SEJA MAIOR QUE 50 CARACTERES.
        if(title.length() > 50) {
            //EXCEÇÃO PARA RETORNAR UM ERRO AO USUÁRIO.
            throw new Exception("O CAMPO TITLE DEVE CONTER NO MAXIMO 50 CARACTERES!");
        }
        this.title = title;
    }

    
}
