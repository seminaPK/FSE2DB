package it.fse2db.main;

import it.fse2db.bean.gtfs.Agency;
import it.fse2db.bean.gtfs.Routes;
import it.fse2db.bean.gtfs.StopTimes;
import it.fse2db.bean.gtfs.Stops;
import it.fse2db.db.Connection;
import it.fse2db.utils.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
* Batch creato per l'esportazione su DB degli orari FSE presenti su file xls.
*
* @author  Cesare Lasorella
* @author  Beniamino Carriero
* @version 0.1
* @since   2014-11-08 
*/

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection conn = new Connection();
		
		try {

			FileInputStream file = new FileInputStream(new File("C:\\orariBari.xls"));
			
			// imposto ditta
			Agency ag = new Agency();
			ag.setAgencyId("FSE");
			ag.setAgencyName("Ferrovie del Sud Est e Servizi Automobilistici");
			ag.setAgencyTimezone("Europe/Rome");
			ag.setAgencyUrl("http://www.fseonline.it/");
			ag.setAgencyPhone("800079090");
			ag.setAgencyLang("it");
			
			// imposto tratta
			Routes r = new Routes();
			r.setRouteId("A");
			r.setRouteDesc("Tratta BARI-TARANTO");
			             
			//Get the workbook instance for XLS file 
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			 
			//Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			List<Stops> list = new ArrayList<Stops>();
			
			 //Iterate through each rows from first sheet
		    Iterator<Row> rowIterator = sheet.iterator();
		    
		    Stops st = new Stops();
		    while(rowIterator.hasNext()) {
		        Row row = rowIterator.next();
		         
		        //For each row, iterate through each columns
		        Iterator<Cell> cellIterator = row.cellIterator();
		        
		        List<StopTimes> listStt = new ArrayList<StopTimes>();
		        
		        String stopId = "";
		        
		        boolean fineFile = false;
		        boolean stopElab = false;
		        boolean rigaLetta = false;
		        
		        if(stopElab) 
		        	break;
		        
		        while(cellIterator.hasNext()) {
		        	
		        	Cell cell = cellIterator.next();
			        
			        if(!fineFile) {
			        
				        if(row.getRowNum() >= 7) {
				        	if(cell.getColumnIndex() == 0) {
				        		if(!Constants.NOTE.equalsIgnoreCase(cell.getStringCellValue()) 
				        				&& (!"".equalsIgnoreCase(cell.getStringCellValue()))) {
				        			rigaLetta = true;
					        		// nuova fermata
					        		st = new Stops();
					        		// imposto valori della fermata
					        		stopId = "S" + row.getRowNum();
					        		st.setStopId(stopId);
					        		st.setStopName(cell.getStringCellValue());
				        		}
				        		else if (Constants.NOTE.equalsIgnoreCase(cell.getStringCellValue())) {
				        			fineFile = true;
				        		}
	                    	}
				        	
				        	if(cell.getColumnIndex() >= 2) {
	                    		StopTimes stt = new StopTimes();
	                    		stt.setDepartureTime(cell.getStringCellValue());
	                    		stt.setStopId(stopId);
	                    		listStt.add(stt);	
	                    	}
				        	
				        }
			        
			        }
		        
		        }
		        
		        if(rigaLetta) {
		        
			        st.setListStopTimes(listStt);
	        		list.add(st);
	        		
	        		rigaLetta = false;
		        }
        		
        		if(fineFile) {
        			System.out.println("parsing file Ultimato");
        			stopElab = true;
        		}
		    }
		    file.close();
		    
		    workbook.close();
		    
		    conn.populateDB(list, ag, r);
		    
		    System.out.println("");
			
		}
		catch(Exception ex) {
			System.out.println("Errore " + ex);
		}
		
		

	}

}
