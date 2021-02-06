package margretcraft.homeplants.ui

import margretcraft.homeplants.model.Category
import java.util.*

const val DATE_TIME_FORMAT = "dd.MMM.yy HH:mm"

fun Date.format(): String = java.text.SimpleDateFormat(margretcraft.homeplants.ui.DATE_TIME_FORMAT,
        Locale.getDefault()).format(this)

fun Int.getCategoryByInt(): Category =
        when (this) {
            0 -> Category.DECICUOUS
            1 -> Category.BLOOMING
            2 -> Category.SUCCULENT
            else -> Category.BLOOMING
        }