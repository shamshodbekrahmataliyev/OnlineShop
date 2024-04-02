package com.example.onlineshop.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlineshop.Model.BrandModel
import com.example.onlineshop.Model.ItemsModel
import com.example.onlineshop.Model.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel() : ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _banner = MutableLiveData<List<SliderModel>>()
    private val _brand=MutableLiveData<MutableList<BrandModel>>()
    private val _popular=MutableLiveData<MutableList<ItemsModel>>()

    val brands:LiveData<MutableList<BrandModel>> =_brand
    val popular:LiveData<MutableList<ItemsModel>> =_popular
     val banners: LiveData<List<SliderModel>> = _banner

    fun loadBanners() {
        val Ref = firebaseDatabase.getReference("Banner")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()
                for (childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(SliderModel::class.java)
                    if (list != null){
                        lists.add(list)
                    }
                }
                _banner.value = lists
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
    fun loadBrand(){
        val Ref=firebaseDatabase.getReference("Category")
        Ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<BrandModel>()
                for (childSnapShot in snapshot.children){
                    val list = childSnapShot.getValue(BrandModel::class.java)
                    if (list != null){
                        lists.add(list)
                    }
                }
                _brand.value=lists
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
    fun loadPupolar(){
        val Ref=firebaseDatabase.getReference("Items")
        Ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (childSnapShot in snapshot.children){
                    val list = childSnapShot.getValue(ItemsModel::class.java)
                    if (list != null){
                        lists.add(list)
                    }
                }
                _popular.value=lists
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}