package com.involves.migratefiletodatabase.step

import com.involves.migratefiletodatabase.domain.Pessoa
import org.springframework.batch.core.Step
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class MigrationPessoaStepConfig {

    @Bean
    fun migrationPessoaStep(
        jobRepository: JobRepository,
        @Qualifier("transactionalManagerApp") transactionManager: PlatformTransactionManager,
        @Qualifier("filePessoaReaderFlat") filePessoaReaderFlat: ItemReader<Pessoa>,
        @Qualifier("bancoPessoaWriter") bancoPessoaWriter: ItemWriter<Pessoa>
    ): Step =
        StepBuilder("migrationPessoaStep", jobRepository)
            .chunk<Pessoa, Pessoa>(10000, transactionManager)
            .reader(filePessoaReaderFlat)
            .writer(bancoPessoaWriter)
            .build()
}