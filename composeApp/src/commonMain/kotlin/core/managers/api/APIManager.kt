package core.managers.api

import data.Note
import io.ktor.client.HttpClient
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import org.koin.core.logger.Logger

class APIManager {


    private val client = HttpClient {
    }

    suspend fun getAllNotes(): HttpResponse {
        return client.get("http://localhost:8080/notes/allnotes")
    }

    suspend fun getNoteById(id: Long): HttpResponse {
        return client.get("http://localhost:8080/notes/$id")
    }

    @OptIn(InternalAPI::class)
    suspend fun createNote(note: Note): HttpResponse {
        return client.post("http://localhost:8080/notes/create") {
            contentType(ContentType.Application.Json)
            body = note
        }
    }

    @OptIn(InternalAPI::class)
    suspend fun updateNote(note: Note): HttpResponse {
        return client.post("http://localhost:8080/notes/update") {
            contentType(ContentType.Application.Json)
            body = note
        }
    }

    @OptIn(InternalAPI::class)
    suspend fun deleteNoteById(id: Long): HttpResponse {
        return client.post("http://localhost:8080/notes/delete") {
            contentType(ContentType.Application.Json)
            body = id
        }
    }

}
