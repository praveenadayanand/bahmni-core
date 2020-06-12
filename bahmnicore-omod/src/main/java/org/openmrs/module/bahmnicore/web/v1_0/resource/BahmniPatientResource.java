package org.openmrs.module.bahmnicore.web.v1_0.resource;

import org.openmrs.Patient;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.RepHandler;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.NamedRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.v1_0.resource.openmrs1_9.PatientResource1_9;

@Resource(name = RestConstants.VERSION_1 + "/patient", order = 0, supportedClass = Patient.class, supportedOpenmrsVersions = { "1.9.*", "1.10.*", "1.11.*", "1.12.*", "2.0.*", "2.1.*" })
public class BahmniPatientResource extends PatientResource1_9 {

    @RepHandler(value=NamedRepresentation.class, name="bahmni")
    public SimpleObject asBahmni(Patient delegate)  {
        DelegatingResourceDescription description = super.getRepresentationDescription(Representation.FULL);
        description.removeProperty("person");
        description.addProperty("person", new NamedRepresentation("bahmni"));
        return convertDelegateToRepresentation(delegate, description);
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
        if(rep instanceof NamedRepresentation && rep.getRepresentation().equals("bahmni")) {
            DelegatingResourceDescription description = super.getRepresentationDescription(Representation.FULL);
            description.removeProperty("person");
            description.addProperty("person", new NamedRepresentation("bahmni"));
            return description;
        }
        return super.getRepresentationDescription(rep);
    }
}
