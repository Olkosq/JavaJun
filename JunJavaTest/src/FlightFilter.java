import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class FlightFilter {
    //Метод фильтрации по количеству сегментов, равное count и больше (меньше)
    static void filterByCountSegment(ArrayList<Flight> listFl, int count, boolean isIncrease)
    {

        for(int i = 0; i < listFl.size(); i++)
        {
            if(isIncrease && listFl.get(i).getSegments().size() < count) {
                listFl.remove(i);
                i--;
            }
            else if(!isIncrease && listFl.get(i).getSegments().size() > count) {
                listFl.remove(i);
                i--;
            }
        }
    }
    //Метод фильтрации по количеству сегментов, равное count
    static void filterByCountSegment(ArrayList<Flight> listFl, int count)
    {
        filterByCountSegment(listFl, count, true);
        filterByCountSegment(listFl, count, false);
    }
    //Метод фильтрации по дате вылета, равное timeDep и позже true (и раньше false)
    static void filterByDateDeparture(ArrayList<Flight> listFl, LocalDateTime timeDep, boolean isAfter)
    {
        for(int i = 0; i < listFl.size(); i++)
        {
            LocalDateTime timeDepFl = listFl.get(i).getSegments().get(0).getDepartureDate();
            if(!isAfter && timeDepFl.isAfter(timeDep) ) {
                listFl.remove(i);
                i--;
            }
            else if(isAfter && timeDepFl.isBefore(timeDep))
            {
                listFl.remove(i);
                i--;
            }
        }
    }
    //Метод фильтрации по дате вылета, равное timeDep
    static void filterByDateDeparture(ArrayList<Flight> listFl, LocalDateTime timeDep)
    {

        filterByDateDeparture(listFl, timeDep, false);
        filterByDateDeparture(listFl, timeDep, true);
    }
    //Метод фильтрации по общей длительности перерывов, равное hour (в часах)  и дольше true (меньше false)
    static void filterBySumDurationPause(ArrayList<Flight> listFl, long hour, boolean isLater)
    {
        for(int i = 0; i < listFl.size(); i++)
        {
            if(listFl.get(i).getSegments().size() == 1)
            {
                listFl.remove(i);
                i--;
            }
            else if(isLater && getSumDurationPause(listFl.get(i)) < hour)
            {
                listFl.remove(i);
                i--;
            }
            else if(!isLater && getSumDurationPause(listFl.get(i)) > hour)
            {
                listFl.remove(i);
                i--;
            }
        }
    }
    //Метод фильтрации по общей длительности перерывов , равное hour (в часах)
    static void filterBySumDurationPause(ArrayList<Flight> listFl, long hour)
    {
        filterBySumDurationPause(listFl, hour, true);
        filterBySumDurationPause(listFl, hour, false);
    }
    //Метод фильтрации по длительности сегментов , равное hour (в часах)
    static void filterByDurationSegment(ArrayList<Flight> listFl, long hour, boolean isLater)
    {
        for(int i = 0; i < listFl.size(); i++)
        {
            for(int j = 0; j < listFl.get(i).getSegments().size(); j++)
            {
                if(isLater && getDurationSegment(listFl.get(i).getSegments().get(j)) < hour)
                {
                    listFl.remove(i);
                    i--;
                    break;
                }
                else if(!isLater && getDurationSegment(listFl.get(i).getSegments().get(j)) > hour)
                {
                    listFl.remove(i);
                    i--;
                    break;
                }
            }
        }
    }
    private static long getDurationSegment(Segment segment)
    {
        long hoursBetween = 0;

        LocalDateTime startDuration = segment.getDepartureDate();
        LocalDateTime finalDuration = segment.getArrivalDate();
        hoursBetween += ChronoUnit.HOURS.between(startDuration, finalDuration);

        return hoursBetween;
    }
    private static long getSumDurationPause(Flight flight)
    {
        long hoursBetween = 0;
        for(int i = 0; i < flight.getSegments().size() - 1; i++)
        {
            LocalDateTime startDuration = flight.getSegments().get(i).getArrivalDate();
            LocalDateTime finalDuration = flight.getSegments().get(i + 1).getDepartureDate();
            hoursBetween += ChronoUnit.HOURS.between(startDuration, finalDuration);
        }
        return hoursBetween;
    }
}
