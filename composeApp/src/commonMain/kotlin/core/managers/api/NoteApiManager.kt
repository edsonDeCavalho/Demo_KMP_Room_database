package core.managers.api

import data.Note
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.SerializationException
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


/**
 * Note Api manager
 */
class NoteApiManager {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
    }


    private val jsonFormat = Json { ignoreUnknownKeys = true }

    private fun parseNotes(json: String): List<Note> {
        return try {
            jsonFormat.decodeFromString<List<Note>>(json)
        } catch (e: SerializationException) {
            println("Error parsing notes: ${e.message}")
            emptyList()
        }
    }


    private fun parseNote(json: String): Note {
        return Json.decodeFromString(Note.serializer(), json)
    }
    /**
     * Récuperation d'une note
     */
    suspend fun fetchNotes(): List<Note>? {
        println("fetch")
        return try {
            val response: HttpResponse = client.request("http://93.90.200.94:8013/notes/allnotes") {
                method = HttpMethod.Get
            }
            val body: String = response.bodyAsText()
            println(body)
            val notes: List<Note> = parseNotes(body)
            notes
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }
    /**
     * Insertion d'une note
     */
    suspend fun addNote(note: Note): Note? {
        return try {
            val response: HttpResponse = client.post("http://93.90.200.94:8013/notes/create") {
                contentType(ContentType.Application.Json)
                setBody(note)
            }
            val body: String = response.bodyAsText()

            val createdNote: Note = parseNote(body)
            createdNote
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }

    /**
     * Update une note
     */
    suspend fun updateNote(note: Note): Note? {
        return try {
            val response: HttpResponse = client.put("http://93.90.200.94:8013/notes/update") {
                contentType(ContentType.Application.Json)
                setBody(note)
            }
            val body: String = response.bodyAsText()

            val updatedNote: Note = parseNote(body)
            updatedNote
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }

    /**
     * Supprimer une note
     */
    suspend fun deleteNote(id: Long): Boolean {
        return try {
            val response: HttpResponse = client.post("http://93.90.200.94:8013/notes/delete") {
                contentType(ContentType.Application.Json)
                setBody(id)
            }
            println("Status delete :"+response.status)
            response.status == HttpStatusCode.OK
        } catch (e: Exception) {
            println("Error: ${e.message}")
            false
        }
    }
    fun closeClient() {
        client.close()
    }
}
