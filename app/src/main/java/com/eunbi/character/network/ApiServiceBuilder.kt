package com.eunbi.character.network

class ApiServiceBuilder {

    suspend fun <T> apiGeneralBuilder(service: Class<T>): T {
        return ApiService.build(
            baseUrl = "https://rickandmortyapi.com/api/",
        ).create(service)
    }
}