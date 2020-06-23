package com.trainingcenter;

import com.trainingcenter.db.YoutubeDao;
import com.trainingcenter.resources.YoutubeResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

public class ProductServiceApplication extends Application<ProductServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ProductServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "ProductService";
    }

    @Override
    public void initialize(final Bootstrap<ProductServiceConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<ProductServiceConfiguration>() {
            @Override
            public void run(ProductServiceConfiguration productServiceConfiguration, Environment environment) throws Exception {
            }

            @Override
            public DataSourceFactory getDataSourceFactory(ProductServiceConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }

            @Override
            public String name() {
                return "db";
            }
        }

        );


    }

    @Override
    public void run(final ProductServiceConfiguration configuration,
                    final Environment environment) {

        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "h2");
        YoutubeDao youtubeDao = jdbi.onDemand(YoutubeDao.class);

        //Resources
        YoutubeResource youtubeResource = new YoutubeResource(youtubeDao);
        environment.jersey().register(youtubeResource);
        environment.jersey().setUrlPattern("/api/*");

    }



}
