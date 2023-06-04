package com.involves.migratefiletodatabase.reader

import com.involves.migratefiletodatabase.domain.DadosBancarios
import com.involves.migratefiletodatabase.domain.Pessoa
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.FileSystemResource
import java.util.*

@Configuration
class FileDadosBancariosReader {

    @Bean
    fun fileDadosBancariosReaderFlat() : FlatFileItemReader<DadosBancarios> {
        return FlatFileItemReaderBuilder<DadosBancarios>()
            .name("fileDadosBancariosReaderFlat")
            .resource(FileSystemResource("files/dados_bancarios.csv"))
            .delimited()
            .names("pessoaId","agencia","conta","banco","id")
            .addComment("--")
            .fieldSetMapper { db ->
                DadosBancarios(
                    db.readInt("pessoaId"),
                    db.readInt("agencia"),
                    db.readInt("conta"),
                    db.readInt("banco"),
                    db.readInt("id"),
                )
            }
            .build()
    }
}