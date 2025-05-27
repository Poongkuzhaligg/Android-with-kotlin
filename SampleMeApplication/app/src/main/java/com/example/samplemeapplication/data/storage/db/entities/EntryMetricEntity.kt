package com.example.samplemeapplication.data.storage.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "entry_metric",
    foreignKeys = [
        ForeignKey(
            entity = AccountEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class EntryMetricEntity(
    @PrimaryKey
    val id: String,
    val userId: String,
    val entryTimestamp: String,
    val bmr: Float,
    val metabolicAge: Float,
    val proteinPercent: Float,
    val pulse: Float,
    val skeletalMusclePercent: Float,
    val subcutaneousFatPercent: Float,
    val visceralFatLevel: Float,
    val boneMass: Float,
    val impedance: Float,
    val unit: String,
    val isOffline: Boolean
) 