package org.kainos.ea;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import org.kainos.ea.resources.OrderController;
import org.kainos.ea.resources.ProductController;

public class DropWizardWebServiceApplication extends Application<DropWizardWebServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropWizardWebServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropWizardWebService";
    }

    @Override
    public void initialize(final Bootstrap<DropWizardWebServiceConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final DropWizardWebServiceConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        environment.jersey().register(new OrderController());
        environment.jersey().register(new ProductController());
    }

}
