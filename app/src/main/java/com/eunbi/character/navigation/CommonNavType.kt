package com.eunbi.character.navigation

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import java.io.Serializable

class CommonNavType<T : Serializable?>(
    private val clazz: Class<T>,
    isNullable: Boolean
) : NavType<T?>(isNullableAllowed = isNullable) {
    override fun get(bundle: Bundle, key: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getSerializable(key, clazz)
        } else {
            bundle.getSerializable(key) as? T
        }
    }

    override fun parseValue(value: String): T? {
        return Gson().fromJson(value, clazz)
    }

    override fun put(bundle: Bundle, key: String, value: T?) {
        bundle.putSerializable(key, value)
    }
}

fun Serializable.toJsonString(): String {
    return Uri.encode(Gson().toJson(this))
}