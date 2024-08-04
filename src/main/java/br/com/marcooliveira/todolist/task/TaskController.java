package br.com.marcooliveira.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcooliveira.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired //PARA O SPRING GERENCIAR A INSTANCIA DO REPOSITORIO.
    private ITaskRepository taskRepository;

    @PostMapping("/") //CAMINHO DE ACESSO AO MÉTODO.
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {

        System.out.println("Chegou no Controller!");
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);
        
        //VALIDANDO AS HORAS E A DATA.
        var currentDate = LocalDateTime.now();

        //VERIFICANDO SE A DATA ATUAL É MAIOR DO QUE A DATA DE INÍCIO.
        if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("A DATA DE INÍCIO / DATA DE TÉRMINO DEVE SER MAIOR DO QUE A DATA ATUAL.");
        }

        if(taskModel.getStartAt().isAfter(taskModel.getEndAt())) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("A DATA DE INÍCIO DEVE SER MENOR DO QUE A DATA DE TÉRMINO.");
        }

        /*CHAMANDO O REPOSITORIO PASSANDO A taskModel E ARMAZENANDO A TAREFA NUMA VARIAVEL.*/
        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);

    }

    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request){

        var idUser = request.getAttribute("idUser");

        var tasks = this.taskRepository.findByIdUser((UUID)idUser);
        return tasks;

    }

    //MÉTODO PARA REALIZAR O UPDATE DAS TASKS.   
    @PutMapping("/{id}") //O SPRINGBOOT IRÁ PROCURAR PELO ID E IRÁ SUBSTITUÍ-LO.
    public ResponseEntity update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id) {

        var task = this.taskRepository.findById(id).orElse(null);

        //CASO A TAREFA NÃO EXISTA.
        if(task == null) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("TAREFA NÃO ENCONTRADA!");
        }

        //VERIFICAÇÃO PARA CASO O ID DO USUÁRIO SEJA DIFERENTE DO ID DO USUÁRIO QUE CRIOU A TASK.
        var idUser = request.getAttribute("idUser");
        if(!task.getIdUser().equals(idUser)) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("ESTE USUÁRIO NÃO TEM PERMISSÃO PARA ALTERAR ESTA TAREFA!");
        }

        Utils.copyNonNullProperties(taskModel, task);

        var taskUpdated = this.taskRepository.save(task);
        return ResponseEntity.ok().body(taskUpdated);

    }
    
}
