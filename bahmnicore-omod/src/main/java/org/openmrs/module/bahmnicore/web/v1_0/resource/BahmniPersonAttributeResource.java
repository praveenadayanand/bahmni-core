package org.openmrs.module.bahmnicore.web.v1_0.resource;

import org.openmrs.Person;
import org.openmrs.PersonAttribute;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.annotation.RepHandler;
import org.openmrs.module.webservices.rest.web.annotation.SubResource;
import org.openmrs.module.webservices.rest.web.representation.NamedRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.v1_0.resource.openmrs1_8.PersonAttributeResource1_8;
import org.openmrs.module.webservices.rest.web.v1_0.resource.openmrs1_8.PersonResource1_8;

@SubResource(parent = BahmniPersonResource.class, order = 0, path = "attribute", supportedClass = PersonAttribute.class, supportedOpenmrsVersions = {
        "1.8.*", "1.9.*", "1.10.*", "1.11.*", "1.12.*", "2.0.*", "2.1.*" })
public class BahmniPersonAttributeResource extends PersonAttributeResource1_8 {

    @RepHandler(value=NamedRepresentation.class, name="bahmni")
    public SimpleObject asBahmni(PersonAttribute delegate)  {
        DelegatingResourceDescription description = super.getRepresentationDescription(Representation.FULL);
        return convertDelegateToRepresentation(delegate, description);
    }

    @Override
    public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
        if(rep instanceof NamedRepresentation && rep.getRepresentation().equals("bahmni")) {
            return super.getRepresentationDescription(Representation.FULL);
        }
        return super.getRepresentationDescription(rep);
    }
}
