package com.github.kkoscielniak.usersapp.database.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.kkoscielniak.usersapp.users.UserDetails
import com.github.kkoscielniak.usersapp.users.UserItem

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String,
    val address: Address,
    val company: Company
)


data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo
)

data class Geo(
    val lat: String,
    val lng: String
)

data class Company(
    val name: String,
    val catchPhrase: String,
    val bs: String
)

fun UserEntity.toUserItem(): UserItem {
    return UserItem(
        id = id,
        name = name,
        email = email,
        phone = phone,
        website = website,
    )
}

fun UserEntity.toUserDetails(): UserDetails {
    return UserDetails(
        id = id,
        name = name,
        username = username,
        email = email,
        phone = phone,
        website = website,
        address = com.github.kkoscielniak.usersapp.users.Address(
            street = address.street,
            suite = address.suite,
            city = address.city,
            zipcode = address.zipcode,
            geo = com.github.kkoscielniak.usersapp.users.Geo(
                lat = address.geo.lat,
                lng = address.geo.lng
            )
        ),
        company = com.github.kkoscielniak.usersapp.users.Company(
            name = company.name,
            catchPhrase = company.catchPhrase,
            bs = company.bs
        )
    )
}

fun UserDetails.toUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        username = username,
        email = email,
        phone = phone,
        website = website,
        address = Address(
            street = address.street,
            suite = address.suite,
            city = address.city,
            zipcode = address.zipcode,
            geo =Geo(
                lat = address.geo.lat,
                lng = address.geo.lng
            )
        ),
        company = Company(
            name = company.name,
            catchPhrase = company.catchPhrase,
            bs = company.bs
        )
    )
}

fun UserDetails.toUserItem(): UserItem {
    return UserItem(
        id = id,
        name = name,
        email = email,
        phone = phone,
        website = website
    )
}
