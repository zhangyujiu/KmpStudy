package com.kmp.demo.room

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "todo_room.db")
    return Room.databaseBuilder<AppDatabase>(
            name = dbFile.absolutePath,
    )
}