package com.example.neo4jdemo.movie.listener;

import com.example.neo4jdemo.movie.model.CatalogUnit;
import com.example.neo4jdemo.movie.model.DBUnit;
import com.example.neo4jdemo.movie.model.SystemUnit;
import lombok.extern.java.Log;
import org.neo4j.ogm.session.event.Event;
import org.neo4j.ogm.session.event.EventListener;

@Log
public class TestEventListener implements EventListener {
    @Override
    public void onPreSave(Event event) {
        log.info("onPreSave - instanceof SystemUnit? " + (event.getObject() instanceof SystemUnit));
        log.info("onPreSave - instanceof DBUnit? " + (event.getObject() instanceof DBUnit));
        log.info("onPreSave - instanceof CatalogUnit? " + (event.getObject() instanceof CatalogUnit));
        log.info("onPreSave >>>>>>>> " + event.getObject().toString());
    }

    @Override
    public void onPostSave(Event event) {
        log.info("onPostSave - instanceof SystemUnit? " + (event.getObject() instanceof SystemUnit));
        log.info("onPostSave - instanceof DBUnit? " + (event.getObject() instanceof DBUnit));
        log.info("onPostSave - instanceof CatalogUnit? " + (event.getObject() instanceof CatalogUnit));
        log.info("onPostSave >>>>>>>> " + event.getObject().toString());
    }

    @Override
    public void onPreDelete(Event event) {
        log.info("onPreDelete - instanceof SystemUnit? " + (event.getObject() instanceof SystemUnit));
        log.info("onPreDelete - instanceof DBUnit? " + (event.getObject() instanceof DBUnit));
        log.info("onPreDelete - instanceof CatalogUnit? " + (event.getObject() instanceof CatalogUnit));
    }

    @Override
    public void onPostDelete(Event event) {
        log.info("onPostDelete - instanceof SystemUnit? " + (event.getObject() instanceof SystemUnit));
        log.info("onPostDelete - instanceof DBUnit? " + (event.getObject() instanceof DBUnit));
        log.info("onPostDelete - instanceof CatalogUnit? " + (event.getObject() instanceof CatalogUnit));
    }
}
