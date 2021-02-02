package margretcraft.homeplants.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import margretcraft.homeplants.R
import java.util.*


@Parcelize
data class Plant(val nomer: Int,
                 val id: Int,
                 var category: Category = Category.BLOOMING,
                 var rod: String,
                 var vid: String,
                 var sort: String,
                 val image: String,
                 var supplier: String,
                 var color: String,
                 val lastChanged: Date = Date()) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Plant
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}

@Parcelize
enum class Category(val catname: Int) : Parcelable {
    DECICUOUS(R.string.decicuous),
    BLOOMING(R.string.blooming),
    SUCCULENT(R.string.succulent);

    companion object {
        fun getCategoryByInt(ID: Int): Category = when (ID) {
            0 -> DECICUOUS
            1 -> BLOOMING
            2 -> SUCCULENT
            else -> BLOOMING
        }
    }
}