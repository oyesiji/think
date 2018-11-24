package com.mkyong.utility;


import java.util.Date;
import java.util.List;

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
			         .with("content", comments).with("userId", 1).with("imageId", 45),
			         r.hashMap("date_inserted", new Date().getTime())
			         .with("content", comments).with("userId", 1).with("imageId", 65),
			         r.hashMap("date_inserted", new Date().getTime())
			         .with("content", comments).with("userId", 1).with("imageId", 165)
			        
			        )
			    		
			    )
		
			)).run(conn);
	}
	
	public void updateNote() {
		/*MapObject newNote = r.hashMap("date_inserted", r.now()).with("imageId", 255).with("userId", 65).with("content",
				"You killed my father");

		r.table("comments").get("d3ff3daf-03a7-4d3b-b52c-39d4b1d28391")
				.update(row -> r.hashMap("posts", row.g("posts").append(newNote))).run(conn);*/
		
		
		r.db("test").table("comments").get("84089623-1d97-43d5-b57b-ed993885f93e").update(doc -> r.hashMap("notes",doc.g("notes").merge(notes -> r.hashMap("content", "who there")))).run(conn);
		
		
	
		 
		System.out.println("###it worksXXXX #####");
	}
	
	public void updateNotes() {
		 r.table("comments").filter(row -> row.g("posts").contains(posts ->posts.g("date_inserted").eq(Long.valueOf("1542906694024")) ).update(r.hashMap("userId", 5)) ).run(conn);
		
		 System.out.println("### we are here###");
		
	}
	
	public void retrieveData() {
		//Cursor cursor = r.table("comments").filter(row -> row.g("posts").contains(posts ->posts.g("date_inserted").eq(Long.valueOf("1542649949897")) )).run(conn);
		//Cursor cursor = r.table("comments").filter(row -> row.g("posts").contains(posts ->posts.g("date_inserted").le(Long.valueOf("1542649949897")) )).run(conn);
		//r.table("comments").indexCreate("image").run(conn);

		//Cursor cursor =r.table("comments").orderBy().optArg("index", "id").run(conn);
		//Cursor cursor =r.table("comments").orderBy().optArg("index", "image").merge(row -> row.g("posts").orderBy().optArg("index", "date_inserted")).run(conn);
		//List tempList=r.table("comments").orderBy(comments -> comments.g("post").g("date_inserted") ).run(conn);
		List tempList=r.table("comments").orderBy("index","id",comments -> comments.g("post").g("date_inserted") ).run(conn);
		System.out.println("workss"+tempList.size());
		

		//for (Object doc : cursor) {
		  //  System.out.println(doc);
		//}
	}

}
