package com.example.samplemeapplication.data.storage.db.dao

import androidx.room.*
import com.example.samplemeapplication.data.storage.db.EntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao {
    // Insert operations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: EntryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entries: List<EntryEntity>)

    // Update operations
    @Update
    suspend fun update(entry: EntryEntity)

    @Query("UPDATE entry SET isSynced = :isSynced WHERE id = :entryId")
    suspend fun updateSyncStatus(entryId: String, isSynced: Boolean)

    // Delete operations
    @Delete
    suspend fun delete(entry: EntryEntity)

    @Query("DELETE FROM entry WHERE id = :entryId")
    suspend fun deleteById(entryId: String)

    @Query("DELETE FROM entry WHERE userId = :userId")
    suspend fun deleteByUserId(userId: String)

    // Select operations
    @Query("SELECT * FROM entry WHERE id = :entryId")
    suspend fun getById(entryId: String): EntryEntity?

    @Query("SELECT * FROM entry")
    fun getAll(): Flow<List<EntryEntity>>

    @Query("SELECT * FROM entry WHERE userId = :userId")
    fun getByUserId(userId: String): Flow<List<EntryEntity>>

    @Query("SELECT * FROM entry WHERE isSynced = :isSynced")
    fun getBySyncStatus(isSynced: Boolean): Flow<List<EntryEntity>>

    // Date range queries
    @Query("SELECT * FROM entry WHERE userId = :userId AND entryTimestamp BETWEEN :startDate AND :endDate")
    fun getByDateRange(userId: String, startDate: String, endDate: String): Flow<List<EntryEntity>>

    @Query("SELECT * FROM entry WHERE entryTimestamp BETWEEN :startDate AND :endDate")
    fun getAllByDateRange(startDate: String, endDate: String): Flow<List<EntryEntity>>

    // Join queries with entry_metric
    @Transaction
    @Query("SELECT * FROM entry WHERE userId = :userId AND entryTimestamp = :timestamp")
    suspend fun getEntryWithMetrics(userId: String, timestamp: String): EntryWithMetrics?

    @Transaction
    @Query("SELECT * FROM entry WHERE userId = :userId AND entryTimestamp BETWEEN :startDate AND :endDate")
    fun getEntriesWithMetricsByDateRange(userId: String, startDate: String, endDate: String): Flow<List<EntryWithMetrics>>

    // Custom queries
    @Query("SELECT * FROM entry WHERE userId = :userId AND isPlaceholder = :isPlaceholder")
    fun getByPlaceholderStatus(userId: String, isPlaceholder: Boolean): Flow<List<EntryEntity>>

    @Query("SELECT * FROM entry WHERE userId = :userId AND verified = :verified")
    fun getByVerifiedStatus(userId: String, verified: Boolean): Flow<List<EntryEntity>>
}

data class EntryWithMetrics(
    @Embedded val entry: EntryEntity,
    @Relation(
        parentColumn = "entryTimestamp",
        entityColumn = "entryTimestamp"
    )
    val metrics: EntryMetricEntity?
) 