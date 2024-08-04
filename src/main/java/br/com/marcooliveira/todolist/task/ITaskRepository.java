package br.com.marcooliveira.todolist.task;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepository extends JpaRepository <TaskModel, UUID> { /*PASSANDO QUAL VAI SER
A ENTIDADE E A CHAVE PRIMÁRIA DA ENTIDADE.*/

    //IRÁ TRAZER SOMENTE AS TASKS QUE TEM O ID DO USUÁRIO.
    List<TaskModel> findByIdUser(UUID idUser);

    
}
