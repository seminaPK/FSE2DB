package it.fse2db.db;


import it.fse2db.bean.gtfs.Agency;
import it.fse2db.bean.gtfs.Routes;
import it.fse2db.bean.gtfs.StopTimes;
import it.fse2db.bean.gtfs.Stops;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;


public class Connection {
	
	public DB getConnection() {
		
		DB db = null;
	
		try {
			// To directly connect to a single MongoDB server (note that this will not auto-discover the primary even
			// if it's a member of a replica set:
			//MongoClient mongoClient = new MongoClient();	
			// or
			//MongoClient mongoClient = new MongoClient( "localhost" );
			// or
			MongoClient mongoClient = new MongoClient( "162.222.181.75" , 27017 );
			// or, to connect to a replica set, with auto-discovery of the primary, supply a seed list of members
			//MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017),
			                                      //new ServerAddress("localhost", 27018),
			                                      //new ServerAddress("localhost", 27019)));
		
			db = mongoClient.getDB( "fse2db" );
			
		}
		catch(Exception ex) {
			System.out.println("Errore" + ex);
		}
		
		return db;
	}
	
	public void populateDB() {
		DB db = getConnection();
		
		DBCollection coll = db.getCollection("agency");
		BasicDBObject doc = new BasicDBObject("name", "agency")
        .append("agency_id", "FSE")
        .append("agency_name", "Ferrovie del Sud Est e Servizi Automobilistici")
        .append("agency_url", "http://www.fseonline.it/")
        .append("agency_timezone", "Europe/Rome")
        .append("agency_lang", "it")
        .append("agency_phone", "800079090");
		
		coll.insert(doc);
	}
	
	public void populateDB(List<Stops> list, Agency ag, Routes rt) {
		DB db = getConnection();
		
		// Agency
		DBCollection colla = db.getCollection("agency");
		BasicDBObject doca = new BasicDBObject("agency_id", ag.getAgencyId())
        .append("agency_name", ag.getAgencyName())
        .append("agency_url", ag.getAgencyUrl())
        .append("agency_timezone", ag.getAgencyTimezone())
        .append("agency_lang", ag.getAgencyLang())
        .append("agency_phone", ag.getAgencyPhone());
		// inserisco l'azienda di trasporti
		colla.insert(doca);
		
		// Routes
		DBCollection collr = db.getCollection("routes");	
		BasicDBObject docr = new BasicDBObject("route_id", rt.getRouteId())
		.append("route_short_name", "")
		.append("route_long_name", "")
		.append("route_desc", rt.getRouteDesc())
		.append("route_type", "");
		// inserisco le tratta
		collr.insert(docr);
		
		for(Stops st : list) {
			
			// Stops
			DBCollection coll = db.getCollection("stops");
			BasicDBObject doc = new BasicDBObject();
			
			doc.append("stop_id", st.getStopId())
			.append("stop_name", st.getStopName())
			.append("stop_desc", "")
			.append("stop_lat", "")
			.append("stop_lon", "")
			.append("zone_id", "")
			.append("stop_url", "")
			.append("location_type", "");
			
			for(StopTimes stt : st.getListStopTimes()) {
				// Stop_times
				DBCollection collt = db.getCollection("stop_times");
				BasicDBObject doct = new BasicDBObject();
				doct.append("trip_id", "")
				.append("arrival_time", stt.getArrivalTime())
				.append("departure_time", stt.getDepartureTime())
				.append("stop_id", stt.getStopId())
				.append("stop_sequence", "")
				.append("pickup_type", "")
				.append("drop_off_type", "");
				// inserisco gli orari di partenza
				collt.insert(doct);
				System.out.println("salvato orario di partenza di " + st.getStopName() + " - " + stt.getDepartureTime());
			}
			
			// inserisco le fermate
			coll.insert(doc);
			System.out.println("salvata fermata: " + st.getStopName());
		}
		
	}
	
}
