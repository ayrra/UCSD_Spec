package module3;

//Java utilities libraries
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

//Processing library
import processing.core.PApplet;

//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

//Parsing library
import parsing.ParseFeed;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author AY
 * */
public class EarthquakeCityMap extends PApplet {

	// You can ignore this.  It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = false;
	
	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 3;

	
	
	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// The map
	private UnfoldingMap map;
	
	//feed with magnitude 2.5+ Earthquakes
	//private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
	
	private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_month.atom";

	
	public void setup() {
		size(950, 600, OPENGL);

		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom"; 	// Same feed, saved Aug 7, 2015, for working offline
		}
		else {
			map = new UnfoldingMap(this, 200, 50, 700, 500, new Microsoft.AerialProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			//earthquakesURL = "2.5_week.atom";
		}
		
	    map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);	
			
	    // The List you will populate with new SimplePointMarkers
	    List<Marker> markers = new ArrayList<Marker>();

	    //Use provided parser to collect properties for each earthquake
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    
	    //loop to create markers for each earthquake
	    for (PointFeature earthquake: earthquakes) {
	    	Marker mark = createMarker(earthquake);
	    	markers.add(mark);
	    }
	    
	    // Add the markers to the map so that they are displayed
	    map.addMarkers(markers);
	}
		
	/* createMarker: A suggested helper method that takes in an earthquake 
	 * feature and returns a SimplePointMarker for that earthquake
	*/
	private SimplePointMarker createMarker(PointFeature feature)
	{  
		
		// Create a new SimplePointMarker at the location given by the PointFeature
		SimplePointMarker marker = new SimplePointMarker(feature.getLocation());
		
		Object magObj = feature.getProperty("magnitude");
		float mag = Float.parseFloat(magObj.toString());
		
		//colors used for representing on map.
	    int yellow = color(255, 255, 0);
	    int green = color(0, 255, 0);
	    int red = color(255, 0, 0);
		
		//set size and color depending on magnitude
	    if (mag >= THRESHOLD_MODERATE) {
	    	marker.setColor(red);
	    	marker.setRadius(13);
	    }
	    else if (mag < THRESHOLD_MODERATE && mag > THRESHOLD_LIGHT) {
	    	marker.setColor(yellow);
	    	marker.setRadius(10);
	    }
	    else {
	    	marker.setColor(green);
	    	marker.setRadius(8);
	    }  
	    
	    // Finally return the marker
	    return marker;
	}
	
	public void draw() {
	    background(10);
	    map.draw();
	    addKey();
	}


	// helper method to draw key in GUI
	private void addKey() 
	{	
		// Remember you can use Processing's graphics methods here
		//box
		fill(220);
		rect(25, 50, 150, 150);

		//title
		fill(0);
		text("Earthquake Key", 25, 60, 150, 150);
		textAlign(CENTER);

		//mag > 5
		fill(255, 0, 0);
		ellipse(50, 95, 13, 13);
		fill(0);
		text("Magnitude 5.0+", 110, 100);
		
		//4 < mag < 6
		fill(255, 255, 0);
		ellipse(50, 120, 10, 10);
		fill(0);
		text("Magnitude 4.0+", 110, 125);
		
		// mag < 4
		fill(0, 255, 0);
		ellipse(50, 145, 8, 8);
		fill(0);
		text("Magnitude < 4.0", 110, 150);
	}
}
