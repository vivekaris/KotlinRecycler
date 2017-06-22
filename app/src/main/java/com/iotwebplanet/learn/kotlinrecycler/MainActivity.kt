package com.iotwebplanet.learn.kotlinrecycler


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import android.widget.Spinner
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.ohmerhe.kolley.request.Http
import java.nio.charset.Charset
import com.iotwebplanet.learn.kotlinrecycler.R.id.spinner
import android.widget.ArrayAdapter



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Http.init(this)
        val gson = Gson()

        //getting recyclerview from xml
        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView

        val spin=findViewById(R.id.spinner) as Spinner

        val adapter = ArrayAdapter.createFromResource(this,R.array.planets_array, android.R.layout.simple_spinner_item)
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
// Apply the adapter to the spinner
        spin.setAdapter(adapter)

        //adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

val purl="http://192.168.1.2/ApnaScore/all_data.php"

     /*
      [  {
    "hostel_name": "'A' Block Hostel",
    "hostel_description": "B.Sc.(Ag.)",
    "hostel_wardenid": "",
    "hostel_attandent": "Rajesh "
  },
  {
    "hostel_name": "'B' Block Hostel",
    "hostel_description": "B.Sc.(Ag.)",
    "hostel_wardenid": "",
    "hostel_attandent": "Siddiqui"
  },
      ]
         */
        //crating an arraylist to store users using the data class user
        val users = ArrayList<User>()

        var students=ArrayList<Hostel>()

         Http.get {
                       url=purl

                       val tag = "HTTP_LOG" //for debug

                         params {
                          // "name"-"value"  //parameters

                         }

                         onStart {
                             Log.d(tag.toString(),"on start")
                         }

                         onSuccess { bytes ->
                           //  Log.d(tag.toString(),"on success ${bytes.toString(Charset.defaultCharset())}")
                             val text =bytes.toString(Charset.defaultCharset())
                             println(text);
                             val list1 = gson.fromJson<ArrayList<Hostel>>(text)
                             students = gson.fromJson<List<Hostel>>(text) as ArrayList<Hostel>

                             /*
                             val s=list1.size;
                                for (i in 0..s-1) {
                             //printing list from loop
                                    println("Products Data : "+list1.get(i).hostel_name+list1.get(i).hostel_description)
                             //println("Products Data : SKU "+list1.get(i).sku+ " Position :"+list1.get(i).name+ " Cat :"+list1.get(i).id)

                              }



                             //testing
                             val si=list1.size
                             for (i in 0..si-1) {
                                 //printing list from loop
                                 println("Hostel Data : "+ list1[i].hostel_name+ list1[i].hostel_description)
                                 users.add(User(list1[i].hostel_name, list1[i].hostel_description))
                             }

                           */
                             //creating our adapter
                             //val adapter = CustomAdapter(users)

                              val adapter = MyAdapter(list1)

                             //now adding the adapter to recyclerview
                             recyclerView.adapter = adapter


                         }

                         onFail { error ->
                             Log.d(tag.toString(),"on fail ${error.toString()}")
                         }

                         onFinish { Log.d(tag.toString(), "on finish")
                            // val adapter = MyAdapter(students)
                         }

                     }

        //adding some dummy data to the list
        users.add(User("Belal Khan", "Ranchi Jharkhand"))
      //  users.add(User("Ramiz Khan", "Ranchi Jharkhand"))
       // users.add(User("Faiz Khan", "Ranchi Jharkhand"))
       // users.add(User("Yashar Khan", "Ranchi Jharkhand"))


    }
}