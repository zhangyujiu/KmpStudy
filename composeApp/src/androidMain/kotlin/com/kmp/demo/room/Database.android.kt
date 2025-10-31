package com.kmp.demo.room

import androidx.room.Room
import androidx.room.RoomDatabase
import com.kmp.demo.Apps

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val appContext = Apps.application.applicationContext
    val dbFile = appContext.getDatabasePath("todo_room.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}