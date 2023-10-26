package de.calorieme.modules.mainmenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import de.calorieme.R
import de.calorieme.common.base.BaseFragment
import de.calorieme.modules.calcalorien.BmiActivity
import de.calorieme.modules.calcalorien.MainCalSelectGenderActivity
import kotlinx.android.synthetic.main.fragment_healthymenu.recycler_view
import kotlinx.android.synthetic.main.fragment_healthymenu.swipe_refresh


class HealthyOptionMenuFragment : BaseFragment() , HealthyOptionMenuAdapter.Listener{

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(HealthyOptionMenuViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_healthymenu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initInstance()
        initViewModel()

    }

    private fun initViewModel() {
        viewModel.whenDataLoadItem.observe(viewLifecycleOwner, Observer {
            it?.let {
                swipe_refresh.isRefreshing = false
                recycler_view?.apply {
                    layoutManager = LinearLayoutManager(context)
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = HealthyOptionMenuAdapter(it?: listOf(), this@HealthyOptionMenuFragment )
                }
            }
        })
    }

    private fun initInstance() {
        viewModel.getMenuItem()
    }

    override fun onTopicClick(itemId: Int, item: HealthyOptionMenuViewModel.MenuItem) {
        if(itemId==0){
            BmiActivity.startWithOutData(context )
//            MainCalSelectGenderActivity.start(context)
        }else if(itemId==1){

        }else if(itemId==2){

        }else{

        }

    }

}

