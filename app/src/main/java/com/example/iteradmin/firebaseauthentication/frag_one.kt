package com.example.iteradmin.firebaseauthentication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*

class frag_one : AppCompatActivity() {

    private lateinit var database:FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frag_one)

        database= FirebaseDatabase.getInstance()

        val name=findViewById<EditText>(R.id.name)
        val college=findViewById<EditText>(R.id.college)
        val branch=findViewById<EditText>(R.id.branch)


        val s=findViewById<Button>(R.id.save)
        val txt=findViewById<TextView>(R.id.data)

        s.setOnClickListener{
            val name:String=name.text.toString()
            val college:String=college.text.toString()
            val branch:String=branch.text.toString()
            val ref:DatabaseReference=database.getReference("users").child("bibek")
            ref.child("name").setValue(name)
            ref.child("college").setValue(college)
            ref.child("branch").setValue(branch)
        }

        val ref=database.getReference("users").child("tofik")
        ref.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                //val str=p0.getValue(String::class.java)

                //txt.text=str
                val children=p0.children
                val str=StringBuilder()
                for(child in children){
                    str.append(child.key + " ; "+child.value+ "\n")
                }
                txt.text=str
            }

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(applicationContext,"Value not found",Toast.LENGTH_LONG).show()
            }
        })
    }
}
