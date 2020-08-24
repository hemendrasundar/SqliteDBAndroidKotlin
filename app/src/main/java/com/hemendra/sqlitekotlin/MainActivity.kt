package com.hemendra.sqlitekotlin

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var Dbase:SQLiteDatabase = openOrCreateDatabase("TestDB",Context.MODE_PRIVATE,null)

        Dbase.execSQL("create table if not exists employee(_id integer primary key autoincrement,eid integer,ename varchar(50),edept varchar(50))")


        btn_insert.setOnClickListener {

            var cv:ContentValues = ContentValues()
            cv.put("eid",et_id.text.toString().toInt())
            cv.put("ename",et_name.text.toString())
            cv.put("edept",et_dep.text.toString())

        var status:Long =  Dbase.insert("employee",null,cv)

            if(status!=-1L)
            {
                Toast.makeText(this@MainActivity,"insertion sucessful",Toast.LENGTH_SHORT).show()
                et_id.setText("");
                et_name.setText("");
                et_dep.setText("");

            }
            else{
                Toast.makeText(this@MainActivity,"insertion failed",Toast.LENGTH_SHORT).show()

            }




        }
        btn_read.setOnClickListener {
            /*Dbase.query("employee", arrayOf("eid","ename"),null,null,null,null,null)*/
            /*Dbase.query("employee",null,"eid=? and edept=?", arrayOf(et_id.text.toString(),et_dep.text.toString()),null,null,null)*/
            /*Dbase.query("employee",null,null,null,"edept",null,null)*/
            /* Dbase.query("employee",null,null,null,"salary","salary>30000","eid desc")*/

        var c:Cursor= Dbase.query("employee",null,null,null,null,null,null)

            var coulmnnames:Array<String> = arrayOf("eid","ename","edept")
            var views:IntArray = intArrayOf(R.id.txt_id,R.id.txt_name,R.id.txt_dep)

            var listAdapter = SimpleCursorAdapter(this@MainActivity,R.layout.list_item,c,coulmnnames,views,0)
            listview.adapter = listAdapter

        }
        btn_update.setOnClickListener {

            var cv:ContentValues = ContentValues()
            cv.put("ename",et_name.text.toString())
            cv.put("edept",et_dep.text.toString())

   var status=  Dbase.update("employee",cv,"eid=?", arrayOf(et_id.text.toString()))

            if(status!=0)
            {
                Toast.makeText(this@MainActivity,"Updation sucessful",Toast.LENGTH_SHORT).show()
                et_id.setText("");
                et_name.setText("");
                et_dep.setText("");

            }
            else{
                Toast.makeText(this@MainActivity,"Updation failed",Toast.LENGTH_SHORT).show()

            }

        }
        btn_delete.setOnClickListener {



         var status:Int=   Dbase.delete("employee","eid=?", arrayOf(et_id.text.toString()))

            if(status!=0)
            {
                Toast.makeText(this@MainActivity,"Deleteion sucessful",Toast.LENGTH_SHORT).show()
                et_id.setText("");
                et_name.setText("");
                et_dep.setText("");

            }
            else{
                Toast.makeText(this@MainActivity,"Deletion failed",Toast.LENGTH_SHORT).show()

            }

        }
    }
}