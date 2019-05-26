# VMinterview
- Collections:  
Write a java merge class that will merge two sorted collections of the same kind into a single sorted collection. You need to think of how to design this class in a generic form and efficiently.
  -- Project Module: MergeCollection 
  -- Test          : TestMergeCollection
                       --testSet() : Merge 2 sorted Set
                       --testList(): Merge 2 sorted List

- Concurrency: 
Use java concurrency packages to write a queue that supports multi-writer and multi-reader, i.e. the writer pushes the stuff into the queue and the reader pops the stuff out the queue. All the writer's stuff can't be lost and must be into the queue, and each reader can't pops out the same stuff. Think of how you would simulate the situation and perform the testing. Using the java blocking queue is not allowed.
 -- Project Module: ReaderAndWriter
 -- Test          : TestMyIOQueue
                     --testReaderAndWriterOnIOQueue: use 3 writers and 2 readers to simulate usage of blocking IO queue.
                     
- OO Design: 
Consider the following design problem and come up with a class hierarchy: Given a drawing mechanism, we always need to invoke some preprocessing before drawing, and postprocessing after drawing, and also each user will draw different shapes like circle, square, whatever he/she likes. Now, make an OO design of classes so that the routine preprocessing/postprocessing can be hidden, and the user can supply whatever the shape he/she wants to draw. 
-- Project Module: Painting  
                   -- PainterPool allows user to fetch the painter to draw a certain shape.
                      Basically, it is a painter factory
-- Test          : TestPainter

- DB Project:
Consider that we are a service provider to provide customers different services. Each customer can subscribe to multiple service and each service can be subscribed to by multiple customers. Come up a database schema to model the service-customer relationship. And use java/html/tomcat/db of your choice to implement a simple web app that will display a customer's subscriptions and let the customer subscribe to a new service, modify or delete a subscribed service.
-- Project Module: services
                   -- Schema design
                   -- SpringBoot and JPA based. I have not implemented ui for this project. 
                      Following restApi are provided to fullfill the requirements. 
                      Overall they are provided from user view, not admin view.
                      /**
                      *  get all the subscribed services for customer with cusId
                      */
                      /customers/services?cusId=
                      /**
                      *  get all the un-subscribed services for customer with cusId
                      */
                      /customers/unSubedServices?cusId=
                      /**
                      *  un-subscribe a service, return true if did 
                      */
                      /customers/unSubService?cusId=？&serId=？
                      /**
                      * subscribe a service for the customer, return true if did
                      */
                      /customers/subService?cusId=？&serId=？
-- Test              : ServicesApplicationTests
                       -- testCustomerRestController()
#Other info
    1. jdk 1.8 && gradle 5.4.1 
    2. build : gradle clean assemble 
    3. run test: gralde build
    4. test results can be found in $project_name/build/reports/tests/test/index.html 

                   
