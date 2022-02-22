package com.example.motivationapp

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

lateinit var arrayAdapter: ArrayAdapter<*>
var dueDates = ArrayList<String>()
var subjects = ArrayList<String>()
var assignments = ArrayList<String>()
lateinit var sharedPreferences : SharedPreferences

class DeadlineActivity : AppCompatActivity() {
    lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deadline)

        listView = findViewById(R.id.listView)

        getData()

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dueDates)
        listView.adapter = arrayAdapter
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val intent = Intent(applicationContext, editorDeadlineActivity::class.java)
                intent.putExtra("Loc", i)
                startActivity(intent)
            }//OnItemClickListener

        listView.onItemLongClickListener =
            AdapterView.OnItemLongClickListener { adapterView, view, i, l ->

                val itemDelete = i
                AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Are you sure!")
                    .setMessage("Do you want to delete this assignment?")
                    .setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                        dueDates.removeAt(itemDelete)
                        subjects.removeAt(itemDelete)
                        assignments.removeAt(itemDelete)
                        arrayAdapter.notifyDataSetChanged()
                        sharedPreferences.edit()
                            .putString("AllDueDates", ObjectSerializer.serialize(dueDates))
                            .apply()
                        sharedPreferences.edit()
                            .putString("AllSubjects", ObjectSerializer.serialize(subjects))
                            .apply()
                        sharedPreferences.edit().putString(
                            "AllAssignments",
                            ObjectSerializer.serialize(assignments)
                        )
                            .apply()

                    }
                    .setNegativeButton("No", null)
                    .show()

                return@OnItemLongClickListener true
            }//OnItemCLongClickListener
    }//onCreate

    fun getData() {
        sharedPreferences = applicationContext.getSharedPreferences(
            "com.example.motivationapp", Context.MODE_PRIVATE
        )

        var newDeadlines = java.util.ArrayList<String?>()
        newDeadlines = ObjectSerializer
            .deserialize(
                sharedPreferences
                    .getString("AllDueDates", ObjectSerializer.serialize(java.util.ArrayList<String>()))
            ) as java.util.ArrayList<String?>

        if (newDeadlines.size != 0) {
            dueDates = java.util.ArrayList(newDeadlines)
        }//if

        newDeadlines = java.util.ArrayList<String?>()
        newDeadlines = ObjectSerializer
            .deserialize(
                sharedPreferences
                    .getString("AllSubjects", ObjectSerializer.serialize(java.util.ArrayList<String>()))
            ) as java.util.ArrayList<String?>

        if (newDeadlines.size != 0) {
            subjects = java.util.ArrayList(newDeadlines)
        }//if

        newDeadlines = java.util.ArrayList<String?>()
        newDeadlines = ObjectSerializer
            .deserialize(
                sharedPreferences
                    .getString("AllAssignments", ObjectSerializer.serialize(java.util.ArrayList<String>()))
            ) as java.util.ArrayList<String?>

        if (newDeadlines.size != 0) {
            assignments = java.util.ArrayList(newDeadlines)
        }//if

    }//getData

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add, menu)
        return super.onCreateOptionsMenu(menu)
    }//onCreateOptionsMenu

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        if (item.itemId == R.id.add) {
            val intent = Intent(applicationContext, addDeadlineActivity::class.java)
            startActivity(intent)
            return true
        }
        return false
    }//onOptionsItemSelected

}//DeadlineActivity