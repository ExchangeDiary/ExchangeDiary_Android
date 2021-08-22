package com.voda.presentation.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voda.domain.usecase.home.GetHomeUseCase
import com.voda.domain.util.getValue
import com.voda.presentation.ui.main.home.mapper.toHomeItems
import com.voda.presentation.ui.main.home.model.HomeItem
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(
    private val getHomeUseCase: GetHomeUseCase
): ViewModel() {

    private val _items = MutableLiveData<List<HomeItem>>()
    val items: LiveData<List<HomeItem>> = _items

    fun load() {
        viewModelScope.launch {
            try {
                getHomeUseCase().getValue().run {
                    _items.value = this.toHomeItems()
                }

            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
    }

}