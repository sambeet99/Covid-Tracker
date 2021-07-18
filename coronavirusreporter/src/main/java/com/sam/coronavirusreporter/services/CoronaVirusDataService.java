package com.sam.coronavirusreporter.services;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sam.coronavirusreporter.models.LocationStats;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


@Service
public class CoronaVirusDataService {
	
	private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
	
	static String output="";
	private final OkHttpClient httpClient = new OkHttpClient();
	
	private List<LocationStats> allstats = new ArrayList<>();
	public List<LocationStats> getAllstats() {
		return allstats;
	}
	public void setAllstats(List<LocationStats> allstats) {
		this.allstats = allstats;
	}
	@PostConstruct
	@Scheduled(cron="* * 1 * * *")
	public void fetchVirusData()  {
		
		 List<LocationStats> newstats = new ArrayList<>();
		
		 Request request = new Request.Builder()
	                .url(VIRUS_DATA_URL).build();
	                                

	        try {
	        	
	        	Response response = httpClient.newCall(request).execute();

	            if (!response.isSuccessful()) throw new IOException();

	              // Get response body
	            output=response.body().string();
	           
	            StringReader csvreader = new StringReader(output);
		        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvreader);
		        for (CSVRecord record : records) {
		        	
		        	LocationStats locationStat =new LocationStats();
		        	locationStat.setState(record.get("Province/State"));
		            locationStat.setCountry(record.get("Country/Region"));
		            locationStat.setLatestTotal(Integer.parseInt(record.get(record.size()-1)));
		            newstats.add(locationStat);
		            newstats.add(locationStat);
		            
		        }
		        this.allstats=newstats;
		        
	            
	        } catch(IOException ex) {
	        	
	        	ex.getMessage();
	        	
	        	
	        }
	        

	    }

		
		
		
	}
	


