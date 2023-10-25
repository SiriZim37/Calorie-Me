package de.calorieme.modules.mainmenu

import androidx.lifecycle.MutableLiveData
import de.calorieme.common.base.BaseViewModel

class HealthyOptionMenuViewModel : BaseViewModel()  {

    val whenDataLoadItem = MutableLiveData<List<MenuItem>>()

    fun getMenuItem(){

        val listMenu = mutableListOf( MenuItem( mName = "Calorien" , mtype = "M1" ),
                                     MenuItem( mName = "Training" , mtype = "M2" ),
                                     MenuItem( mName = "Food selection" , mtype = "M3" ),
                                     MenuItem( mName = "Dashboard" , mtype = "M4" ))

        whenDataLoadItem.postValue(listMenu)
    }

    data class MenuItem(
        val mName: String = "",
        var mtype: String = ""

    )
}