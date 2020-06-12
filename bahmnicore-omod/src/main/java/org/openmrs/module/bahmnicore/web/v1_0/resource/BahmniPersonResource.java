package org.openmrs.module.bahmnicore.web.v1_0.resource;


import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.annotation.RepHandler;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.NamedRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.v1_0.resource.openmrs1_11.PersonResource1_11;
import org.openmrs.module.webservices.rest.web.v1_0.resource.openmrs1_8.PersonResource1_8;

@Resource(
        name = "v1/person",
        order = 0,
        supportedClass = Person.class,
        supportedOpenmrsVersions = {"1.8.*", "1.9.*", "1.10.*", "1.11.*", "1.12.*", "2.0.*", "2.1.*"}
)
public class BahmniPersonResource extends PersonResource1_11 {

    @RepHandler(value=NamedRepresentation.class, name="bahmni")
    public SimpleObject asBahmni(Person delegate)  {
        DelegatingResourceDescription description = super.getRepresentationDescription(Representation.FULL);
        description.removeProperty("attributes");
        description.addProperty("attributes", new NamedRepresentation("bahmni"));
        return convertDelegateToRepresentation(delegate, description);
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
        if(rep instanceof NamedRepresentation && rep.getRepresentation().equals("bahmni")) {
            DelegatingResourceDescription description = super.getRepresentationDescription(Representation.FULL);
            description.removeProperty("attributes");
            description.addProperty("attributes", new NamedRepresentation("bahmni"));
            return description;
        }
        return super.getRepresentationDescription(rep);
    }
}
