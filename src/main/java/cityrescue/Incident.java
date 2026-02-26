package cityrescue;

import cityrescue.enums.IncidentStatus;
import cityrescue.enums.IncidentType;

public class Incident {
    IncidentType incidentType;
    IncidentStatus incidentStatus;
    int[] loc;
    int severity;
    int Id;

    public Incident(IncidentType incidentType, int severity, int[] loc, int Id){
        this.incidentType = incidentType;
        this.incidentStatus = IncidentStatus.REPORTED;
        this.severity = severity;
        this.loc = loc;
        this.Id = Id;
    }
}
