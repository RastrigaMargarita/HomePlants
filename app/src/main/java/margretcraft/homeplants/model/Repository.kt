package margretcraft.homeplants.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object Repository {

    private val plantsLiveData = MutableLiveData<List<Plant>>()

    private val plants: MutableList<Plant> = mutableListOf(
            Plant(22, 1, Category.BLOOMING,"Пеларгония", "Зональная", "Peppermint twist", "", "Leroy Merlin", "красный"),
            Plant(25, 2, Category.BLOOMING,"Пеларгония", "Остролистная", "Diabolo", "", "Leroy Merlin", "темно-синий"),
            Plant(30, 3, Category.BLOOMING,"Сенполия", "Midi махровый", "Ramona", "", "ЕК", "розовый"),
            Plant(30, 4, Category.SUCCULENT,"Асфоделовые", "Хавортия", "Жемчужная", "", "сайт tsvety.ru", ""),
            Plant(30, 5, Category.SUCCULENT,"Кактусовые", "Маммилярия", "Бомбицина", "", "сайт tsvety.ru", ""),
    )

    init {
        plantsLiveData.value = plants
    }

    fun getPlants(): LiveData<List<Plant>> = plantsLiveData

    fun savePlant(plant: Plant){
        addOrReplace(plant)
        plantsLiveData.value = plants
    }

    private  fun addOrReplace(plant: Plant) {
        for (i in 0 until plants.size){
            if (plants[i]==plant){
                plants[i] =plant
                return
            }
        }
        plants.add(plant)
    }
}