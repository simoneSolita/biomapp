package com.goblin.consulting.biomapp.repositories

import com.goblin.consulting.biomapp.dao.PinDao
import com.goblin.consulting.biomapp.model.PinItem
import java.util.*
import javax.inject.Inject

class PinRepository @Inject constructor(
    private val pinDao: PinDao
) {

    fun insertPin(pinItem: PinItem) {
        pinDao.insertPin(pinItem)
    }

    fun getPinByRange(
        dateFrom: Date?,
        dateTo: Date?
    ) = selectQuery(dateFrom, dateTo)

    private fun selectQuery(
        dateFrom: Date?,
        dateTo: Date?
    ): List<PinItem> {
        if (dateFrom == null || dateTo == null) {
            return pinDao.getAllPins()
        }

        return pinDao.getPinsByDate(dateFrom, dateTo)
    }

}