package receptenadmin.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties


data class Recepten(
    val recipes: List<Recept>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Recept(
    val uuid: String?,
    val name: String?,
    val url: String?,
    val nrPersons: Int?,
    val localImageName: String?,
    val ingredients: List<ReceptIngredient>,
    val directions: String?,
    val remark: String?,
    val totalCookingTime: Int?,
    val preparingTime: Int?,
    val favorite: Boolean?,
    val dateAdded: Long?,
    val tags: List<String?>?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ReceptIngredient(
    val amount: Amount,
    val name: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Amount(val nrUnit: Double, val unit: String)
