import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        /*
        Filter f = new MinMagFilter(4.0); 
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        }
        */
        
       
        Filter f1 = new MagnitudeFilter(3.5,4.5,"Magnitude"); 
        Filter f2 = new DepthFilter(-55000.0,-20000.0,"Depth");
        ArrayList<QuakeEntry> mf  = filter(list, f1); 
        ArrayList<QuakeEntry> newMF = filter(mf,f2);
        for (QuakeEntry qe: newMF) { 
            System.out.println(qe);
        }
        
       /*
        Location location = new Location(39.7392, -104.9903);
        Filter f1 = new DistanceFilter(location,1000000.0,"Distance"); 
        Filter f2 = new PhraseFilter("end","a","Phrase");
        ArrayList<QuakeEntry> mf  = filter(list, f1); 
        ArrayList<QuakeEntry> newMF = filter(mf,f2);
        int counter = 0;
        for (QuakeEntry qe: newMF) {
            String title = qe.getInfo();
            int index = title.length();
            char ch = title.charAt(index - 1);
            if(ch == 'a'){
            System.out.println(qe);
            counter ++;
            }
        }
        */
        
        System.out.println(newMF.size());
    }
    
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        /*
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }
        */
        
        //to use addFilter need to create MatchAllFilter object not Filter
        MatchAllFilter maf = new MatchAllFilter();
        //maf.addFilter(new MagnitudeFilter(1.0,4.0,"Magnitude"));
        //maf.addFilter(new DepthFilter(-180000.0,-30000.0,"Depth"));
        maf.addFilter(new PhraseFilter("any","Can","Phrase"));
        ArrayList<QuakeEntry> output = filter(list,maf);
        
        for (QuakeEntry qe : output){
            System.out.println(qe);
        }
        System.out.println(output.size());
        System.out.println(maf.getName());
    }
    
    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        /*
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }
        */
        
        //to use addFilter need to create MatchAllFilter object not Filter
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0,5.0,"Magnitude"));
        Location loc = new Location(55.7308, 9.1153);
        maf.addFilter(new DistanceFilter(loc,3000000,"Distance"));
        maf.addFilter(new PhraseFilter("any","e","Phrase"));
        ArrayList<QuakeEntry> output = filter(list,maf);
        
        for (QuakeEntry qe : output){
            System.out.println(qe);
        }   
        System.out.println(output.size());
        System.out.println(maf.getName());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
