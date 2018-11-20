package com.mkyong.utility;


import java.util.Date;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.gen.exc.ReqlError;
import com.rethinkdb.gen.exc.ReqlQueryLogicError;
import com.rethinkdb.model.MapObject;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;


public class DatabaseEngine {
	public static final RethinkDB r = RethinkDB.r;
	private Connection conn;
	
	public void save(String record) {
		
	}
	
	public DatabaseEngine(){
		 conn = r.connection().hostname("localhost").port(28015).connect();
	}
	
	public void createTable(Connection conn) {
		try{
			r.db("test").tableCreate("comments").run(conn);
		}catch(Exception e) {
			
		}
	
	}
	
	
	public void insertData(String comments) {
		createTable(conn);
		r.table("comments").insert(				
				r.array(
			    r.hashMap("image", "image2")
			    .with("pUserId", "459")
			     .with("posts", r.array(
			        r.hashMap("date_inserted", new Date().getTime())
			         .with("content", comments).with("userId", 1).with("imageId", 45)
			        )
			    )
		
			)).run(conn);
	}
	
	public void retrieveData() {
		//Cursor cursor = r.table("comments").filter(row -> row.g("posts").contains(posts ->posts.g("date_inserted").eq(Long.valueOf("1542649949897")) )).run(conn);
		//Cursor cursor = r.table("comments").filter(row -> row.g("posts").contains(posts ->posts.g("date_inserted").le(Long.valueOf("1542649949897")) )).run(conn);
		//r.table("comments").indexCreate("image").run(conn);

		Cursor cursor =r.table("comments").orderBy().optArg("index", "image").run(conn);
		
		System.out.println("workss");
		

		for (Object doc : cursor) {
		    System.out.println(doc);
		}
	}

}
