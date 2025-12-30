package com.eunbi.character.model

data class CharacterInfo (
    val info: Info = Info(),
    val results: List<Character> = listOf()
)

data class Info(
    val count: Int = 0,
    val pages: Int = 0,
    val next: String? = "",
    val prev: String? = ""
)

data class Character(
    val id: Int = 0,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = "",
    val origin: NameInfo = NameInfo(),
    val location: NameInfo = NameInfo(),
    val image: String = "",
    val episode: List<String> = listOf(),
    val url: String = "",
    val created: String = ""
)

data class NameInfo(
    val name: String = "",
    val url: String = ""
)