package com.eshop.watches.order.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.jboss.logging.Logger;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;

@ApplicationScoped
class MicroServiceLifeCycle {

  private static final Logger LOGGER = Logger.getLogger(MicroServiceLifeCycle.class);
  
  void onStart(@Observes StartupEvent ev) {
    LOGGER.info("");


    LOGGER.info(" $$$$$$\\                  $$\\                     ");
    LOGGER.info("$$  __$$\\                 $$ |                    ");
    LOGGER.info("$$ /  $$ | $$$$$$\\   $$$$$$$ | $$$$$$\\   $$$$$$\\  ");
    LOGGER.info("$$ |  $$ |$$  __$$\\ $$  __$$ |$$  __$$\\ $$  __$$\\ ");
    LOGGER.info("$$ |  $$ |$$ |  \\__|$$ /  $$ |$$$$$$$$ |$$ |  \\__|");
    LOGGER.info("$$ |  $$ |$$ |      $$ |  $$ |$$   ____|$$ |      ");
    LOGGER.info(" $$$$$$  |$$ |      \\$$$$$$$ |\\$$$$$$$\\ $$ |      ");
    LOGGER.info(" \\______/ \\__|       \\_______| \\_______|\\__|");

    LOGGER.infof("The Orders API is starting with profile: %s", ProfileManager.getActiveProfile());
    LOGGER.info("");
  }

  void onStop(@Observes ShutdownEvent ev) {
      LOGGER.info("The Order API is stopping...");
  }
}

