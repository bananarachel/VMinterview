# VMinterview
## Project Module: MergeCollection 
## Test : TestMergeCollection
  testSet() : Merge 2 sorted Set
  testList(): Merge 2 sorted List

 ## Project Module: ReaderAndWriter
 ## Test : TestMyIOQueue
 testReaderAndWriterOnIOQueue: use 3 writers and 2 readers to simulate usage of blocking IO queue.
                     
## Project Module: Painting  
### PainterPool 
allows user to fetch the painter to draw a certain shape. Basically, it is a painter factory
### Test : TestPainter

## Project Module: services
SpringBoot and JPA based. 
### Schema design
Table customer
Table service
Table subscribe

### RestApi 
I have not implemented ui for this project. Following restApi are provided to fullfill the requirements. 
Overall they are provided from user view, not admin view.

//get all the subscribed services for customer with cusId

/customers/services?cusId=

//get all the un-subscribed services for customer with cusId

/customers/unSubedServices?cusId=

//un-subscribe a service, return true if did 

/customers/unSubService?cusId=？&serId=？

//subscribe a service for the customer, return true if did

/customers/subService?cusId=？&serId=？

## Test : ServicesApplicationTests
testCustomerRestController()

# Other info
    1. jdk 1.8 && gradle 5.4.1 
    2. build : gradle clean assemble 
    3. run test: gralde build
    4. test results can be found in $project_name/build/reports/tests/test/index.html 

                   
