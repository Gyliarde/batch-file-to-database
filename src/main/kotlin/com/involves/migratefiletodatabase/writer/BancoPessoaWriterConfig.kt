package com.involves.migratefiletodatabase.writer

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
class BancoPessoaWriterConfig {


    @Bean
    fun bancoPessoaWriter(@Qualifier("appDatasource") dataSource: DataSource) : JdbcBatchItemWriter<Pessoa> =
        JdbcBatchItemWriterBuilder<Pessoa>()
            .dataSource(dataSource)
            .sql(
                "INSERT INTO pessoa (id, nome, email, data_nascimento, idade) VALUES(?, ?, ?, ?, ?)"
            )
            .itemPreparedStatementSetter(itemPreparedStatementSetter())
            .build()

    private fun itemPreparedStatementSetter() : ItemPreparedStatementSetter<Pessoa> {
        return ItemPreparedStatementSetter<Pessoa> { item, ps ->
            ps.setInt(1, item.id)
            ps.setString(2, item.nome)
            ps.setString(3, item.email)
            ps.setDate(4, Date(item.dataNascimento.time))
            ps.setInt(5, item.idade)
        }
    }
}