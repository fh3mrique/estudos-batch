package com.estudos.helloWorldSpringBatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    @Bean // Define o bean do Job
   public Job job (JobRepository jobRepository, Step step){
        // Cria um Job chamado "job" que inicia com o Step "step"
       return new JobBuilder("job", jobRepository)
               .start(step)
               .build();
   }

   @Bean // Define o bean do Step
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager){
       // Cria um Step chamado "step" que executa uma Tasklet
        return new StepBuilder("step", jobRepository )
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        // Lógica da tarefa: imprime "Olá mundo" no console
                        System.out.println("Olá mundo");
                        // Indica que a tarefa foi concluída com sucesso
                        return RepeatStatus.FINISHED;
                    }
                }, transactionManager)
                .build();
   }
}
