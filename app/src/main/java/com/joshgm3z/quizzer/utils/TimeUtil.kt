package com.joshgm3z.quizzer.utils

import android.text.format.DateUtils

class TimeUtil {
    companion object {
        fun getRelativeTime(from: Long, to: Long): String {
            return DateUtils.getRelativeTimeSpanString(
                from,
                to,
                0L,
                DateUtils.FORMAT_ABBREV_ALL
            ).toString()
        }
    }
}