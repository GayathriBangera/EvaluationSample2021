package com.gayathri.domain.models.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_table")
class Bookmark(@PrimaryKey @ColumnInfo(name = "title") val title: String)