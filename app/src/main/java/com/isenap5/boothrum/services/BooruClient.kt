
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request

@Serializable
data class ImageBoard (
    @SerialName("id"                     ) var id                  : Int?        = null,
    @SerialName("score"                  ) var score               : Int?        = null,
    @SerialName("source"                 ) var source              : String?     = null,
    @SerialName("rating"                 ) var rating              : String?     = null,
    @SerialName("tag_string"             ) var tagString           : String?     = null,
    @SerialName("tag_string_general"     ) var tagStringGeneral    : String?     = null,
    @SerialName("tag_string_character"   ) var tagStringCharacter  : String?     = null,
    @SerialName("tag_string_copyright"   ) var tagStringCopyright  : String?     = null,
    @SerialName("tag_string_artist"      ) var tagStringArtist     : String?     = null,
    @SerialName("tag_string_meta"        ) var tagStringMeta       : String?     = null,
    @SerialName("file_url"               ) var fileUrl             : String?     = null,
)

class BooruClient {
    private val client = OkHttpClient()
    var url = "https://safebooru.donmai.us/posts.json?limit=200"

    fun getPosts(): List<ImageBoard>? {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).execute().use { response ->
            return if (response.isSuccessful) {
                response.body?.string()?.let { jsonString ->
                    val json = Json { ignoreUnknownKeys = true }
                    json.decodeFromString<List<ImageBoard>>(jsonString)
                }
            } else {
                null
            }
        }
    }
}
