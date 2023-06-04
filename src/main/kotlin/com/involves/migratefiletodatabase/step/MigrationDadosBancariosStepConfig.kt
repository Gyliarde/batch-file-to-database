package com.involves.migratefiletodatabase.step

import com.involves.migratefiletodatabase.domain.DadosBancarios
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
class MigrationDadosBancariosStepConfig {

    @Bean
    fun migrationDadosBancariosStep(
        jobRepository: JobRepository,
        @Qualifier("transactionalManagerApp") transactionManager: PlatformTransactionManager,
        @Qualifier("fileDadosBancariosReaderFlat") fileDadosBancariosReaderFlat: ItemReader<DadosBancarios>,
        @Qualifier("bancoDadosBancariosWriter") bancoDadosBancariosWriter: ItemWriter<DadosBancarios>
    ): Step =
        StepBuilder("migrationDadosBancariosStep", jobRepository)
            .chunk<DadosBancarios, DadosBancarios>(10000, transactionManager)
            .reader(fileDadosBancariosReaderFlat)
            .writer(bancoDadosBancariosWriter)
            .build()
}