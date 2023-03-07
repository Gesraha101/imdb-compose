package com.example.local.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

object ConverterUtils {

    inline fun <reified T> fromString(value: String?): MutableList<T>? {
        return if (value != null && !value.contentEquals("null")) {
            val listType = Types.newParameterizedType(
                MutableList::class.java,
                T::class.java
            )
            val adapter: JsonAdapter<List<T>> =
                Moshi.Builder().build().adapter(listType)
            return adapter.fromJson(value) as? MutableList<T>
        } else null
    }

    inline fun <reified T> toString(list: MutableList<T>?): String? {
        list?.let {
            val listType = Types.newParameterizedType(
                MutableList::class.java,
                T::class.java
            )
            return Moshi.Builder().build()
                .adapter<MutableList<T>>(listType)
                .toJson(list)
        }
        return null
    }
}