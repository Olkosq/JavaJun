import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String args[] )
    {
        ArrayList<Flight> filteredFlightList1 = new ArrayList<>(FlightBuilder.createFlights());
        ArrayList<Flight> filteredFlightList2 = new ArrayList<>(FlightBuilder.createFlights());
        ArrayList<Flight> filteredFlightList3 = new ArrayList<>(FlightBuilder.createFlights());

        FlightFilter.filterByDateDeparture(filteredFlightList1, LocalDateTime.now(), false);
        FlightFilter.filterByDurationSegment(filteredFlightList2, 0, false);
        FlightFilter.filterBySumDurationPause(filteredFlightList3, 2 , false);
        System.out.println("Список перелетов с вылетов до текущего момента времени:");
        for(Flight flight : filteredFlightList1)
        {
            System.out.println(flight.getSegments());
        }
        System.out.println("\nСписок перелетов, имеющих сегменты с датой прилета раньше даты вылета:");
        for(Flight flight : filteredFlightList2)
        {
            System.out.println(flight.getSegments());
        }
        System.out.println("\nСписок перелетов с общим проведенным временем на земле, не превышающим два часа:");
        for(Flight flight : filteredFlightList3)
        {
            System.out.println(flight.getSegments());
        }
    }
}
