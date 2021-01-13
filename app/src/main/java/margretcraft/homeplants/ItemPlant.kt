package margretcraft.homeplants

import android.os.Parcel
import android.os.Parcelable

class ItemPlant(val nomer: Int, val id: Int, val rod: String?, val vid: String?, val sort: String?, val image: String?, val supplier: String?, val color: String?) : Parcelable {


    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
    )

    override fun describeContents(): Int {
        return 0;
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(nomer)
        dest?.writeInt(id)
        dest?.writeString(rod)
        dest?.writeString(vid)
        dest?.writeString(sort)
        dest?.writeString(image)
        dest?.writeString(supplier)
        dest?.writeString(color)
    }

    companion object CREATOR : Parcelable.Creator<ItemPlant> {
        override fun createFromParcel(parcel: Parcel): ItemPlant {
            return ItemPlant(parcel)
        }

        override fun newArray(size: Int): Array<ItemPlant?> {
            return arrayOfNulls(size)
        }
    }
}