package com.involves.migratefiletodatabase.writer

import com.involves.migratefiletodatabase.domain.DadosBancarios
import com.involves.migratefiletodatabase.domain.Pessoa
import org.springframework.batch.item.database.ItemPreparedStatementSetter
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.sql.Date
import javax.sql.DataSource

@Configuration
class BancoDadosBancariosWriterConfig {

    @Bean
    fun bancoDadosBancariosWriter(@Qualifier("appDatasource") dataSource: DataSource) : JdbcBatchItemWriter<DadosBancarios> =
        JdbcBatchItemWriterBuilder<DadosBancarios>()
            .dataSource(dataSource)
            .sql(
                "INSERT INTO dados_bancarios (id, pessoa_id, agencia, conta, banco) VALUES( :id, :pessoaId, :agencia, :conta, :banco)"
            )
            .beanMapped()
            .build()
}
