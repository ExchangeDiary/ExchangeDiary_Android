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

    private val _isProgressing = MutableLiveData<Boolean>().apply { value = false }
    val isProgressing: LiveData<Boolean> = _isProgressing

    private val _isScrollTop = MutableLiveData<Boolean>().apply { value = true }
    val isScrollTop: LiveData<Boolean> = _isScrollTop

    fun setScrollTop(isTop: Boolean) {
        _isScrollTop.value = isTop
    }

    fun load() {
        viewModelScope.launch {
            try {
                _isProgressing.value = true
                getHomeUseCase().getValue().run {
                    _items.value = this.toHomeItems()
                }

            } catch (e: Exception) {
                e.printStackTrace()

            } finally {
                _isProgressing.value = false
            }
        }
    }

}