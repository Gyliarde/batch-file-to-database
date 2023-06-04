package com.involves.migratefiletodatabase.reader

import com.involves.migratefiletodatabase.domain.Pessoa
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.FileSystemResource
import java.util.*

@Configuration
class FilePessoaReader {

    @Bean
    fun filePessoaReaderFlat() : FlatFileItemReader<Pessoa> {
        return FlatFileItemReaderBuilder<Pessoa>()
            .name("filePessoaReaderFlat")
            .resource(FileSystemResource("files/pessoas.csv"))
            .delimited()
            .names("nome","email","dataNascimento","idade", "id")
            .addComment("--")
            .fieldSetMapper { pessoa ->
               Pessoa(
                   pessoa.readInt("id"),
                   pessoa.readString("nome"),
                   pessoa.readString("email"),
                   Date(pessoa.readDate("dataNascimento", "yyyy-MM-dd HH:mm:ss").time),
                   pessoa.readInt("idade"),
               )
            }
            .build()
    }
}