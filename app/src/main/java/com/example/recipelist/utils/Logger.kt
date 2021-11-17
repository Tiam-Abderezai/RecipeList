package com.example.recipelist.utils


import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.StringBuilder
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

object Logger {

    // MEMBERS

    //    private const val classTag = Globals.SEARCH_STRING + "Logger"
    private val LOG_CRUMB_LOCK = Any()
    private val LOCALE: Locale = Locale.US
    private val datePattern = Pattern.compile("(\\d{2})-(\\d{2})-(\\d{4})")
    private val timePattern = Pattern.compile("(\\d{2}):(\\d{2}):(\\d{2})")
    private const val LOG_PRIORITY_STRING_WTF = "WTF"
    private const val LOG_PRIORITY_STRING_ERROR = "E"
    private const val LOG_PRIORITY_STRING_WARNING = "W"
    private const val LOG_PRIORITY_STRING_INFO = "I"
    private const val LOG_PRIORITY_STRING_VERBOSE = "V"
    private const val LOG_PRIORITY_STRING_DEBUG = "D"


    // ENUMS
    enum class LogLevel { NONE, WTF, ERROR, WARNING, INFO, VERBOSE, DEBUG }

    var currentLogLevel = LogLevel.DEBUG
    var currentVisibleLogLevel = MutableLiveData(LogLevel.DEBUG)
    val currentVisibleLogLevelName = MutableLiveData(LogLevel.DEBUG.name)

    // INIT
    init {
        currentVisibleLogLevelName.observeForever { name ->
            currentVisibleLogLevel.value = LogLevel.values().find { it.name == name }
        }
    }

    fun logd(tag: String, msg: String?) = GlobalScope.launch(Dispatchers.Main) {
        val log = LogModel(tag, LogLevel.DEBUG, msg ?: "null")
        Timber.tag(tag).d(msg ?: "null")
        if (currentLogLevel >= LogLevel.DEBUG) {
            Timber.tag(tag).d(msg ?: "null")
        }
    }

    fun logv(tag: String, msg: String?) = GlobalScope.launch(Dispatchers.Main) {
        val log = LogModel(tag, LogLevel.VERBOSE, msg ?: "null")
        if (currentLogLevel >= LogLevel.VERBOSE) {
            Timber.tag(tag).v(msg ?: "null")
        }
    }

    fun loge(tag: String, msg: String?, ex: java.lang.Exception) =
        GlobalScope.launch(Dispatchers.Main) {
            val log = LogModel(tag, LogLevel.ERROR, msg ?: "null" + " : " + ex.message)
            if (currentLogLevel >= LogLevel.ERROR) {
                val message = msg + " : " + ex.message
                Timber.tag(tag).e(message)
            }
        }


    private fun getCurrentDateTimeString(): String? {
        val cal = Calendar.getInstance()
        cal.timeInMillis = System.currentTimeMillis()
        val df: DateFormat = SimpleDateFormat("MM-dd HH:mm:ss", LOCALE)
        return df.format(cal.time)
    }

    class LogModel(
        val tag: String,
        val logType: LogLevel,
        val message: String
    ) {
        fun getLogColor(): Int {
            return when (logType) {
                LogLevel.DEBUG -> Color.GRAY
                LogLevel.VERBOSE -> Color.CYAN
                LogLevel.INFO -> Color.GREEN
                LogLevel.WARNING -> Color.YELLOW
                LogLevel.ERROR -> Color.RED
                else -> Color.WHITE
            }
        }

        override fun toString(): String {
            val builder = StringBuilder()
            builder.append("[")
            builder.append(logType)
            builder.append("]")
            builder.append(getCurrentDateTimeString())
            builder.append(tag)
            builder.append(":")
            builder.append(message)

            return builder.toString()
        }
    }
}

