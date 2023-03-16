package com.example.encryptedstoragekotlin.firebase

import com.google.firebase.database.*
import javax.inject.Inject

class FirebaseDB @Inject constructor(database: FirebaseDatabase){
    private var reference:DatabaseReference = database.reference
    private lateinit var callBack: CallBack
    private lateinit var childEventListener: ChildEventListener

    fun attachCallback(callBack:CallBack){
        this.callBack = callBack
        childEventListener = object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                callBack.retrieveData(snapshot.key, snapshot.getValue(String::class.java))
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                callBack.removeData(snapshot.key)
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {
                
            }
        }
        reference.addChildEventListener(childEventListener)
    }

    fun sendMessage(text:String) = reference.push().setValue(text)

    fun removeListeners(){
        reference.removeEventListener(childEventListener)
    }

    interface CallBack{
        fun retrieveData(key:String?, data:String?)
        fun removeData(key:String?)
    }

}