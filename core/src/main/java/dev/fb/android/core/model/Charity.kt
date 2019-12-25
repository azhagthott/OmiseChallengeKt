package dev.fb.android.core.model

data class Charity(
    val id: Int,
    val name: String,
    val logo_url: String
)

data class Charities(
    val data: List<Charity>
)