import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        phrase = phrase.toLowerCase();
        int length = 0;
        for (QuakeEntry qe : quakeData){
            String title = qe.getInfo();
            title = title.toLowerCase();
            if (title.indexOf(phrase) == 0 && where.equals("start")){
                answer.add(qe);
            }
            else if (title.indexOf(phrase) != -1 && where.equals("any")){
                answer.add(qe);
            }
            else if(title.lastIndexOf(phrase) >= phrase.length() && where.equals("end")){
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData){
            if (qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData){
            Location location = qe.getLocation();
            if (location.distanceTo(from) < distMax*1000.0){
                answer.add(qe);
            } 
        }

        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> byMag = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : byMag){
            System.out.println(qe);
        }

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        // TODO
        double dist = 1000.0;
        ArrayList<QuakeEntry> quakeNear = filterByDistanceFrom(list,dist,city);
        for (QuakeEntry qe : quakeNear){
            Location loc = qe.getLocation();
            System.out.println(loc.distanceTo(city)/1000.0 + " " + qe.getInfo());
        }
        System.out.println("There are " + quakeNear.size() + " quakes that meet the criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth){
        ArrayList<QuakeEntry> answer= new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData){
            if (qe.getDepth() > minDepth && qe.getDepth() < maxDepth){
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> quakesDepth = filterByDepth(list, -4000.0, -2000.0);
        for (QuakeEntry qe : quakesDepth){
            System.out.println(qe);
        }
        System.out.println("Found " + quakesDepth.size() + " quakes that match cireteria");
    }
    
    public void quakesWithPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> quakesDepth = filterByPhrase(list, "any","Can");
        for (QuakeEntry qe : quakesDepth){
            System.out.println(qe);
            
        }
        System.out.println("Found " + quakesDepth.size() + " quakes that match cireteria");
    }
}
