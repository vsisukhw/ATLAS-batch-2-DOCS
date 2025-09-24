//package Dax;
//
//
//import java.util.function.Supplier;
//
//
//@FunctionBean("dax-tester")
//public class AcceleeratorDAXDemoTesting implements Supplier<String> {
//    private final AcceleeratorDAXDemoInterface testTableService;
//
//
//    public AcceleeratorDAXDemoTesting(AcceleeratorDAXDemoInterface service) {
//        this.testTableService= service;
//    }
//
//
//    @Override
//    public String get() {
//        long startTime = System.currentTimeMillis();
//
//
//        DaxTable table = new DaxTable().UsingHashKey("hash")
//                .usingRange("range");
//
//
//        AcceleeratorDAXDemoInterface.save(table);
//
//
//        // running the loop for 1000 milli sec = 1 sec
//        for (int i = 0; i < 1000; i++) {
//            // check for the non null value
//            requireNotNull(AcceleeratorDAXDemoInterface.load("hash", "range"));
//        }
//
//
//        long endTime = System.currentTimeMillis();
//
//
//        return "table is loaded in " + endTime+" milli seconds ";
//
//
//    }
//}
