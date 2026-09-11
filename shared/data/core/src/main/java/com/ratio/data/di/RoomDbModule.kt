package com.ratio.data.di

import android.content.Context
import com.ratio.data.db.RatioRoomDatabase
import com.ratio.data.db.dao.read.AccountDao
import com.ratio.data.db.dao.read.BudgetDao
import com.ratio.data.db.dao.read.CategoryDao
import com.ratio.data.db.dao.read.ExchangeRatesDao
import com.ratio.data.db.dao.read.LoanDao
import com.ratio.data.db.dao.read.LoanRecordDao
import com.ratio.data.db.dao.read.PlannedPaymentRuleDao
import com.ratio.data.db.dao.read.SettingsDao
import com.ratio.data.db.dao.read.TagAssociationDao
import com.ratio.data.db.dao.read.TagDao
import com.ratio.data.db.dao.read.TransactionDao
import com.ratio.data.db.dao.read.UserDao
import com.ratio.data.db.dao.write.WriteAccountDao
import com.ratio.data.db.dao.write.WriteBudgetDao
import com.ratio.data.db.dao.write.WriteCategoryDao
import com.ratio.data.db.dao.write.WriteExchangeRatesDao
import com.ratio.data.db.dao.write.WriteLoanDao
import com.ratio.data.db.dao.write.WriteLoanRecordDao
import com.ratio.data.db.dao.write.WritePlannedPaymentRuleDao
import com.ratio.data.db.dao.write.WriteSettingsDao
import com.ratio.data.db.dao.write.WriteTagAssociationDao
import com.ratio.data.db.dao.write.WriteTagDao
import com.ratio.data.db.dao.write.WriteTransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDbModule {

    @Provides
    @Singleton
    fun provideRatioRoomDatabase(
        @ApplicationContext appContext: Context,
    ): RatioRoomDatabase {
        return RatioRoomDatabase.create(
            applicationContext = appContext,
        )
    }

    @Provides
    fun provideUserDao(db: RatioRoomDatabase): UserDao {
        return db.userDao
    }

    @Provides
    fun provideAccountDao(db: RatioRoomDatabase): AccountDao {
        return db.accountDao
    }

    @Provides
    fun provideTransactionDao(db: RatioRoomDatabase): TransactionDao {
        return db.transactionDao
    }

    @Provides
    fun provideCategoryDao(db: RatioRoomDatabase): CategoryDao {
        return db.categoryDao
    }

    @Provides
    fun provideBudgetDao(db: RatioRoomDatabase): BudgetDao {
        return db.budgetDao
    }

    @Provides
    fun provideSettingsDao(db: RatioRoomDatabase): SettingsDao {
        return db.settingsDao
    }

    @Provides
    fun provideLoanDao(db: RatioRoomDatabase): LoanDao {
        return db.loanDao
    }

    @Provides
    fun provideLoanRecordDao(db: RatioRoomDatabase): LoanRecordDao {
        return db.loanRecordDao
    }

    @Provides
    fun providePlannedPaymentRuleDao(db: RatioRoomDatabase): PlannedPaymentRuleDao {
        return db.plannedPaymentRuleDao
    }

    @Provides
    fun provideTagDao(db: RatioRoomDatabase): TagDao {
        return db.tagDao
    }

    @Provides
    fun provideTagAssociationDao(db: RatioRoomDatabase): TagAssociationDao {
        return db.tagAssociationDao
    }

    @Provides
    fun provideExchangeRatesDao(
        roomDatabase: RatioRoomDatabase
    ): ExchangeRatesDao {
        return roomDatabase.exchangeRatesDao
    }

    @Provides
    fun provideWriteAccountDao(db: RatioRoomDatabase): WriteAccountDao {
        return db.writeAccountDao
    }

    @Provides
    fun provideWriteTransactionDao(db: RatioRoomDatabase): WriteTransactionDao {
        return db.writeTransactionDao
    }

    @Provides
    fun provideWriteCategoryDao(db: RatioRoomDatabase): WriteCategoryDao {
        return db.writeCategoryDao
    }

    @Provides
    fun provideWriteBudgetDao(db: RatioRoomDatabase): WriteBudgetDao {
        return db.writeBudgetDao
    }

    @Provides
    fun provideWriteSettingsDao(db: RatioRoomDatabase): WriteSettingsDao {
        return db.writeSettingsDao
    }

    @Provides
    fun provideWriteLoanDao(db: RatioRoomDatabase): WriteLoanDao {
        return db.writeLoanDao
    }

    @Provides
    fun provideWriteLoanRecordDao(db: RatioRoomDatabase): WriteLoanRecordDao {
        return db.writeLoanRecordDao
    }

    @Provides
    fun provideWritePlannedPaymentRuleDao(db: RatioRoomDatabase): WritePlannedPaymentRuleDao {
        return db.writePlannedPaymentRuleDao
    }

    @Provides
    fun provideWriteExchangeRatesDao(db: RatioRoomDatabase): WriteExchangeRatesDao {
        return db.writeExchangeRatesDao
    }

    @Provides
    fun provideWriteTagDao(db: RatioRoomDatabase): WriteTagDao {
        return db.writeTagDao
    }

    @Provides
    fun provideWriteTagAssociationDao(db: RatioRoomDatabase): WriteTagAssociationDao {
        return db.writeTagAssociationDao
    }
}
