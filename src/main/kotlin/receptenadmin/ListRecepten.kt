package receptenadmin
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import receptenadmin.model.Recepten
import java.io.FileInputStream

fun main(args: Array<String>) {
    val mapper = jacksonObjectMapper()

    val objectMapper = jacksonObjectMapper()
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

    val serviceAccount = FileInputStream("/Users/robbertvdzon/receptenapp-firebase-adminsdk-baike-8705b709d3.json")

    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://receptenapp.firebaseio.com")
        .build()

    FirebaseApp.initializeApp(options)

    val dbFirestore = FirestoreClient.getFirestore()

    val documentSnapshotApiFuture = dbFirestore.collection("robbert@vdzon.com").document("recipes").get()
    val documentSnapshot = documentSnapshotApiFuture.get()
    val recepten = documentSnapshot["v1"]
    println(recepten)

    val rec: Recepten = objectMapper.readValue(recepten.toString(),Recepten::class.java)
    val list1 = rec.recipes.flatMap { it.ingredients?.map{it.name}?: emptyList() }
    val list = rec.recipes.flatMap { it.ingredients?.map{it.name}?: emptyList() }.distinct()
    println(list)

    val knoflookRecepten = rec.recipes.filter { it.ingredients.any { it.name == "knoflook" }}

    println("ok")


}

