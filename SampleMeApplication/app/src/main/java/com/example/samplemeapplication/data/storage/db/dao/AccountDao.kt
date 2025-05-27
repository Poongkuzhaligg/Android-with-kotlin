package com.example.samplemeapplication.data.storage.db.dao

import androidx.room.*
import com.example.samplemeapplication.data.storage.db.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    // Insert operations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: AccountEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(accounts: List<AccountEntity>)

    // Update operations
    @Update
    suspend fun update(account: AccountEntity)

    @Query("UPDATE account SET isSynced = :isSynced WHERE id = :accountId")
    suspend fun updateSyncStatus(accountId: String, isSynced: Boolean)

    // Delete operations
    @Delete
    suspend fun delete(account: AccountEntity)

    @Query("DELETE FROM account WHERE id = :accountId")
    suspend fun deleteById(accountId: String)

    @Query("DELETE FROM account WHERE isSynced = :isSynced")
    suspend fun deleteBySyncStatus(isSynced: Boolean)

    // Select operations
    @Query("SELECT * FROM account WHERE id = :accountId")
    suspend fun getById(accountId: String): AccountEntity?

    @Query("SELECT * FROM account")
    fun getAll(): Flow<List<AccountEntity>>

    @Query("SELECT * FROM account WHERE isSynced = :isSynced")
    fun getBySyncStatus(isSynced: Boolean): Flow<List<AccountEntity>>

    @Query("SELECT * FROM account WHERE email = :email")
    suspend fun getByEmail(email: String): AccountEntity?

    @Query("SELECT * FROM account WHERE isLoggedIn = :isLoggedIn")
    fun getByLoginStatus(isLoggedIn: Boolean): Flow<List<AccountEntity>>

    // Custom queries
    @Query("SELECT * FROM account WHERE isActiveAccount = :isActive")
    fun getByActiveStatus(isActive: Boolean): Flow<List<AccountEntity>>

    @Query("SELECT * FROM account WHERE isExpired = :isExpired")
    fun getByExpiredStatus(isExpired: Boolean): Flow<List<AccountEntity>>
} 