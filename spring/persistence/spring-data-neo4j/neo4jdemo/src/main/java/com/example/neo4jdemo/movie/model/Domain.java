package com.example.neo4jdemo.movie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NodeEntity
@ToString(exclude = {"parent", "children"})
public class Domain {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private DomainStatus status;

    @EqualsAndHashCode.Exclude
    @Relationship(type="GLOSSARY_HIERARCHY")
    private Domain parent;

    @EqualsAndHashCode.Exclude
    @Relationship(type="GLOSSARY_HIERARCHY", direction=Relationship.INCOMING)
    private Collection<Domain> children = new ArrayList<>();

    public static DomainBuilder builder() {
        return new DomainBuilder();
    }

    public static class DomainBuilder {
        private String name;
        private DomainStatus status = DomainStatus.DRAFT;
        private Collection<Domain> children = new ArrayList<>();

        public DomainBuilder name(String name) {
            this.name = name;
            return this;
        }
        public DomainBuilder status(DomainStatus status) {
            this.status = status;
            return this;
        }
        public DomainBuilder children(Collection<Domain> children) {
            this.children = children;
            return this;
        }
        public Domain build() {
            Domain domain = new Domain();
            domain.setName(name);
            domain.setStatus(status);
            domain.setChildren(children);
            return domain;
        }
    }
}
