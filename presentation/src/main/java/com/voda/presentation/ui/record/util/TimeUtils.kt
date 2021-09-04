package com.voda.presentation.ui.record.util

object TimeUtils {

    fun getRecordTimerText(milliseconds: Int): String {
        var finalTimerString = ""
        var minutesString = ""
        var secondsString = ""
        var milliSecondsString = ""


        // Convert total duration into time
        val hours = (milliseconds / (100 * 60 * 60)).toInt()
        val minutes = (milliseconds % (100 * 60 * 60)) / (100 * 60)
        val seconds = (milliseconds % (100 * 60 * 60) % (100 * 60) / 100)
        val milli = (milliseconds % 100)
        // Add hours if there
        if (hours > 0) {
            finalTimerString = "$hours:"
        }

        if (minutes > 0) {
            minutesString = "$minutes:"
        }

        minutesString = if (minutes < 10) {
            "0$minutes"
        } else {
            "" + minutes
        }

        // Prepending 0 to seconds if it is one digit
        secondsString = if (seconds < 10) {
            "0$seconds"
        } else {
            "" + seconds
        }

        milliSecondsString = if (milli < 10) {
            "0$milli"
        } else {
            "" + milli
        }

        finalTimerString = "$finalTimerString$minutesString:$secondsString:$milliSecondsString"

        // return timer string
        return finalTimerString
    }

    fun milliSecondsToTimer(milliseconds: Int): String {
        var finalTimerString = ""
        var secondsString = ""

        // Convert total duration into time
        val hours = (milliseconds / (1000 * 60 * 60)).toInt()
        val minutes = (milliseconds % (1000 * 60 * 60)).toInt() / (1000 * 60)
        val seconds = (milliseconds % (1000 * 60 * 60) % (1000 * 60) / 1000).toInt()
        // Add hours if there
        if (hours > 0) {
            finalTimerString = "$hours:"
        }

        // Prepending 0 to seconds if it is one digit
        secondsString = if (seconds < 10) {
            "0$seconds"
        } else {
            "" + seconds
        }
        finalTimerString = "$finalTimerString$minutes:$secondsString"

        // return timer string
        return finalTimerString
    }
}