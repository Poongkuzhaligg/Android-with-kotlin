package com.example.samplemeapplication.data.storage.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "entry",
    foreignKeys = [
        ForeignKey(
            entity = AccountEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class EntryEntity(
    @PrimaryKey
    val id: String,
    val isSynced: Boolean
    val userId: String,
    val entryTimestamp: String,
    val serverTimestamp: String,
    val operationType: String,
    val weight: Float,
    val bodyFat: Float,
    val muscleMass: Float,
    val water: Float,
    val bmi: Float,
    val verified: Boolean,
    val source: String,
    val isPlaceholder: Boolean,
    val attempts: Int,
) 