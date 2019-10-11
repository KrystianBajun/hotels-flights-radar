package com.flightradar.flightradar.model.historicalFlight;


import java.math.BigDecimal;

public class HistoricalDeserialization
{
    private String actual;

    private String distance;

    private String trip_class;

    private String origin;

    private String destination;

    private String depart_date;

    private String number_of_changes;

    private String duration;

    private String show_to_affiliates;

    private String gate;

    private BigDecimal value;

    private String return_date;

    private String found_at;

    public String getActual ()
    {
        return actual;
    }

    public void setActual (String actual)
    {
        this.actual = actual;
    }

    public String getDistance ()
    {
        return distance;
    }

    public void setDistance (String distance)
    {
        this.distance = distance;
    }

    public String getTrip_class ()
    {
        return trip_class;
    }

    public void setTrip_class (String trip_class)
    {
        this.trip_class = trip_class;
    }

    public String getOrigin ()
    {
        return origin;
    }

    public void setOrigin (String origin)
    {
        this.origin = origin;
    }

    public String getDestination ()
    {
        return destination;
    }

    public void setDestination (String destination)
    {
        this.destination = destination;
    }

    public String getDepart_date ()
    {
        return depart_date;
    }

    public void setDepart_date (String depart_date)
    {
        this.depart_date = depart_date;
    }

    public String getNumber_of_changes ()
    {
        return number_of_changes;
    }

    public void setNumber_of_changes (String number_of_changes)
    {
        this.number_of_changes = number_of_changes;
    }

    public String getDuration ()
    {
        return duration;
    }

    public void setDuration (String duration)
    {
        this.duration = duration;
    }

    public String getShow_to_affiliates ()
    {
        return show_to_affiliates;
    }

    public void setShow_to_affiliates (String show_to_affiliates)
    {
        this.show_to_affiliates = show_to_affiliates;
    }

    public String getGate ()
    {
        return gate;
    }

    public void setGate (String gate)
    {
        this.gate = gate;
    }

    public BigDecimal getValue ()
    {
        return value;
    }

    public void setValue (BigDecimal value)
    {
        this.value = value;
    }

    public String getReturn_date ()
    {
        return return_date;
    }

    public void setReturn_date (String return_date)
    {
        this.return_date = return_date;
    }

    public String getFound_at ()
    {
        return found_at;
    }

    public void setFound_at (String found_at)
    {
        this.found_at = found_at;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [actual = "+actual+", distance = "+distance+", trip_class = "+trip_class+", origin = "+origin+", destination = "+destination+", depart_date = "+depart_date+", number_of_changes = "+number_of_changes+", duration = "+duration+", show_to_affiliates = "+show_to_affiliates+", gate = "+gate+", value = "+value+", return_date = "+return_date+", found_at = "+found_at+"]";
    }
}
