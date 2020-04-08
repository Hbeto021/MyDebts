package com.htolintino.mydebts.commons.domain.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "debt")
data class Debt(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var description: String,
    var value: String,
    var month: String): Parcelable