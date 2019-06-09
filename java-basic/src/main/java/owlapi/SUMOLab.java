package owlapi;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

import java.io.File;
import java.util.Set;
import java.util.stream.Stream;

public class SUMOLab {
    public static void main(String[] args) {
        OWLOntologyManager owlOntologyManager = OWLManager.createOWLOntologyManager();
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        try {
            OWLOntology sumo = owlOntologyManager.loadOntologyFromOntologyDocument(new File("/Users/tzuyichao/Documents/ontology/SUMO.owl"));
            OWLReasoner reasoner = reasonerFactory.createReasoner(sumo);

            PrefixManager pm = new DefaultPrefixManager("http://www.ontologyportal.org/SUMO.owl#");
            OWLDataProperty abbrProperty = owlOntologyManager.getOWLDataFactory().getOWLDataProperty("abbreviation", pm);

            Stream<OWLClass> classStream = sumo.classesInSignature();
            classStream.forEach(owlClass -> {
                System.out.println(owlClass.getIRI());
                // subClasses
//                NodeSet<OWLClass> subClasses = reasoner.getSubClasses(owlClass, true);
//                subClasses.entities().forEach(subClass -> {
//                    System.out.println("  SubClass: " + subClass.getIRI());
//                });
                NodeSet<OWLNamedIndividual> owlNamedIndividualNodeSet = reasoner.getInstances(owlClass, false);
                owlNamedIndividualNodeSet.entities().forEach(owlNamedIndividual -> {
                    System.out.println("  Named Individual: " + owlNamedIndividual.getIRI().getShortForm());
                    if("CrownTobagoIsAirport".equals(owlNamedIndividual.getIRI().getShortForm())) {
                        System.out.println("-------");
                        System.out.println(abbrProperty.toString());
                        Set<OWLLiteral> values = reasoner.getDataPropertyValues(owlNamedIndividual, abbrProperty);
                        values.stream().forEach(owlLiteral -> {
                            System.out.println("    abbr: " + owlLiteral);
                        });
                    }
//                    Stream<OWLAnnotationProperty> propertyStream = owlNamedIndividual.annotationPropertiesInSignature();
//                    propertyStream.forEach(owlAnnotationProperty -> {
//                        System.out.println("    Property: " + owlAnnotationProperty);
//                    });
                });
            });
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
        owlOntologyManager.clearOntologies();
    }
}
