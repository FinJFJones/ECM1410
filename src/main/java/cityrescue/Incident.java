package cityrescue;

import cityrescue.enums.IncidentStatus;
import cityrescue.enums.IncidentType;

public class Incident {
    IncidentType incidentType;
    IncidentStatus incidentStatus;
    int[] loc;
    int severity;

    public Incident(IncidentType incidentType, int severity, int[] loc){
        this.incidentType = incidentType;
        this.incidentStatus = IncidentStatus.REPORTED;
        this.severity = severity;
        this.loc = loc;
    }
}
