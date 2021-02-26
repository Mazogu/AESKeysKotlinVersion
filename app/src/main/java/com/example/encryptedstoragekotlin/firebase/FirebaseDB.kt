package com.example.encryptedstoragekotlin.firebase

import com.google.firebase.database.*

class FirebaseDB {
    private val database:FirebaseDatabase?
    private val reference:DatabaseReference?
    private lateinit var callBack: CallBack
    private lateinit var childEventListener: ChildEventListener

    init {
        database = FirebaseDatabase.getInstance()
        reference = database?.reference
    }

    fun attachCallback(callBack:CallBack){
        this.callBack = callBack
        childEventListener = object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                callBack.retrieveData(snapshot.getValue(String::class.java), snapshot.key)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                callBack.removeData(snapshot.key)
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        fun sendMessage(text:String) = reference?.push()?.setValue(text)

        fun removeListeners(){
            reference?.removeEventListener(childEventListener)
        }
    }

    interface CallBack{
        fun retrieveData(data:String?, key:String?)
        fun removeData(key:String?)
    }

}