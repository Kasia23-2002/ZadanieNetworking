package com.github.kkoscielniak.usersapp.database.database
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromAddress(address: Address): String {
        return gson.toJson(address)
    }

    @TypeConverter
    fun toAddress(json: String): Address {
        val type = object : TypeToken<Address>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromCompany(company: Company): String {
        return gson.toJson(company)
    }

    @TypeConverter
    fun toCompany(json: String): Company {
        val type = object : TypeToken<Company>() {}.type
        return gson.fromJson(json, type)
    }
}
