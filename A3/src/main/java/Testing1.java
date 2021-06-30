public class Testing1 implements Runnable{
    private String testingVal = "";

    public Testing1(String testingVal){
        this.testingVal = testingVal;
    }
    @Override
    public void run() {
        for(int t=0; t<30; t++){
            printTesting(t+1 +" .)  "+testingVal);
        }
    }

    private void printTesting(String testingVal){
        System.out.println(testingVal);
    }
}
