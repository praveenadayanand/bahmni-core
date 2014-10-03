package org.bahmni.module.referencedata.labconcepts.model.event;

import org.bahmni.module.referencedata.labconcepts.contract.Department;
import org.bahmni.module.referencedata.labconcepts.contract.Sample;
import org.bahmni.module.referencedata.labconcepts.contract.Test;
import org.ict4h.atomfeed.server.service.Event;
import org.openmrs.Concept;
import org.openmrs.api.context.Context;

import java.net.URISyntaxException;
import java.util.List;

public class LabConceptSetEvent extends ConceptOperationEvent {

    public LabConceptSetEvent() {
    }

    @Override
    public boolean isResourceConcept(Concept concept) {
        return isLaboratoryConcept(concept) || isDepartmentConcept(concept) || isTestPanelConcept(concept);
    }

    private boolean isTestPanelConcept(Concept concept) {
        return concept.getName(Context.getLocale()) != null &&
                concept.getName(Context.getLocale()).getName().equals(Test.TEST_PARENT_CONCEPT_NAME);
    }

    private boolean isDepartmentConcept(Concept concept) {
        return concept.getName(Context.getLocale()) != null && concept.getName(Context.getLocale()).getName().equals(Department.DEPARTMENT_PARENT_CONCEPT_NAME);
    }

    private boolean isLaboratoryConcept(Concept concept) {
        return concept.getName(Context.getLocale()) != null && concept.getName(Context.getLocale()).getName().equals(Sample.SAMPLE_PARENT_CONCEPT_NAME);
    }


    @Override
    public Event asAtomFeedEvent(Object[] arguments) throws URISyntaxException {
        Concept concept = (Concept) arguments[0];
        List<Concept> setMembers = concept.getSetMembers();
        for (Concept setMember : setMembers) {
            if (!isResourceConcept(setMember)) {
                Context.getConceptService().saveConcept(setMember);
            }
        }
        return null;
    }
}