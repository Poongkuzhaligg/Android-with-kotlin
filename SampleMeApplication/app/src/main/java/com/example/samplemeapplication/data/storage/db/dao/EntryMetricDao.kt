package com.example.samplemeapplication.data.storage.db.dao

import androidx.room.*
import com.example.samplemeapplication.data.storage.db.EntryMetricEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryMetricDao {
    // Insert operations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(metric: EntryMetricEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(metrics: List<EntryMetricEntity>)

    // Update operations
    @Update
    suspend fun update(metric: EntryMetricEntity)

    @Query("UPDATE entry_metric SET isSynced = :isSynced WHERE id = :metricId")
    suspend fun updateSyncStatus(metricId: String, isSynced: Boolean)

    // Delete operations
    @Delete
    suspend fun delete(metric: EntryMetricEntity)

    @Query("DELETE FROM entry_metric WHERE id = :metricId")
    suspend fun deleteById(metricId: String)

    @Query("DELETE FROM entry_metric WHERE userId = :userId")
    suspend fun deleteByUserId(userId: String)

    // Select operations
    @Query("SELECT * FROM entry_metric WHERE id = :metricId")
    suspend fun getById(metricId: String): EntryMetricEntity?

    @Query("SELECT * FROM entry_metric")
    fun getAll(): Flow<List<EntryMetricEntity>>

    @Query("SELECT * FROM entry_metric WHERE userId = :userId")
    fun getByUserId(userId: String): Flow<List<EntryMetricEntity>>

    @Query("SELECT * FROM entry_metric WHERE isSynced = :isSynced")
    fun getBySyncStatus(isSynced: Boolean): Flow<List<EntryMetricEntity>>

    // Metric-specific queries
    @Query("SELECT * FROM entry_metric WHERE userId = :userId AND metricType = :metricType")
    fun getByMetricType(userId: String, metricType: String): Flow<List<EntryMetricEntity>>

    @Query("SELECT * FROM entry_metric WHERE userId = :userId AND metricType = :metricType AND entryTimestamp BETWEEN :startDate AND :endDate")
    fun getByMetricTypeAndDateRange(userId: String, metricType: String, startDate: String, endDate: String): Flow<List<EntryMetricEntity>>

    // Aggregation queries
    @Query("SELECT AVG(metricValue) FROM entry_metric WHERE userId = :userId AND metricType = :metricType AND entryTimestamp BETWEEN :startDate AND :endDate")
    suspend fun getAverageMetricValue(userId: String, metricType: String, startDate: String, endDate: String): Double?

    @Query("SELECT MAX(metricValue) FROM entry_metric WHERE userId = :userId AND metricType = :metricType AND entryTimestamp BETWEEN :startDate AND :endDate")
    suspend fun getMaxMetricValue(userId: String, metricType: String, startDate: String, endDate: String): Double?

    @Query("SELECT MIN(metricValue) FROM entry_metric WHERE userId = :userId AND metricType = :metricType AND entryTimestamp BETWEEN :startDate AND :endDate")
    suspend fun getMinMetricValue(userId: String, metricType: String, startDate: String, endDate: String): Double?

    // Custom queries
    @Query("SELECT * FROM entry_metric WHERE userId = :userId AND metricType = :metricType AND metricValue >= :threshold")
    fun getMetricsAboveThreshold(userId: String, metricType: String, threshold: Double): Flow<List<EntryMetricEntity>>

    @Query("SELECT * FROM entry_metric WHERE userId = :userId AND metricType = :metricType AND metricValue <= :threshold")
    fun getMetricsBelowThreshold(userId: String, metricType: String, threshold: Double): Flow<List<EntryMetricEntity>>
} 