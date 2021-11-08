//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
////import org.opencv.core.Scalar;
////import org.openftc.easyopencv.OpenCvCamera;
////import org.openftc.easyopencv.OpenCvCameraFactory;
////import org.openftc.easyopencv.OpenCvCameraRotation;
////credit to pink to the future
//@Autonomous(name="OpenCV_Test")
//class OpenCV_Tutorial_Freight_Frenzy extends LinearOpMode {
//
////    private OpenCvCamera webcam;
//
//    private static final int CAMERA_WIDTH  = 640; // width  of wanted camera resolution
//    private static final int CAMERA_HEIGHT = 360; // height of wanted camera resolution
//
//    double CrLowerUpdate = 0;
//    double CbLowerUpdate = 0;
//    double CrUpperUpdate = 120;
//    double CbUpperUpdate = 120;
//
//    double lowerruntime = 0;
//    double upperruntime = 0;
//
//    // Its green now :)                                Y      Cr     Cb
//    public static Scalar scalarLowerYCrCb = new Scalar(0.0, 0.0, 0.0);
//    public static Scalar scalarUpperYCrCb = new Scalar(255.0, 120.0, 120.0);
//
//    @Override
//    public void runOpMode()
//    {
//        // OpenCV webcam
//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        // webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
//        //OpenCV Pipeline
//        VisionPipeline myPipeline;
//        // webcam.setPipeline(myPipeline = new VisionPipeline());
//        // Configuration of Pipeline
//        myPipeline.ConfigurePipeline(30, 30,30,30,  CAMERA_WIDTH, CAMERA_HEIGHT);
//        myPipeline.ConfigureScalarLower(scalarLowerYCrCb.val[0],scalarLowerYCrCb.val[1],scalarLowerYCrCb.val[2]);
//        myPipeline.ConfigureScalarUpper(scalarUpperYCrCb.val[0],scalarUpperYCrCb.val[1],scalarUpperYCrCb.val[2]);
//        // Webcam Streaming
//        // webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
//            @Override
//            public void onOpened() {
//                // webcam.startStreaming(CAMERA_WIDTH, CAMERA_HEIGHT, OpenCvCameraRotation.UPRIGHT);
//            }
//
//            @Override
//            public void onError(int errorCode) {
//                //not gonna happen
//            }
//        });
//
//        // Only if you are using ftcdashboard
////        FtcDashboard dashboard = FtcDashboard.getInstance();
////        telemetry = dashboard.getTelemetry();
////        FtcDashboard.getInstance().startCameraStream(webcam, 10);
//
//        telemetry.update();
//        waitForStart();
//
//        while (opModeIsActive())
//        {
//            if(myPipeline.error){
//                telemetry.addData("Exception: ", myPipeline.debug);
//            }
//
//            telemetry.addData("RectArea: ", myPipeline.getRectArea());
//            telemetry.update();
//
//            if(myPipeline.getRectArea() > 2000){
//                if(myPipeline.getRectMidpointX() > 400){
//                    AUTONOMOUS_C();
//                }
//                else if(myPipeline.getRectMidpointX() > 200){
//                    AUTONOMOUS_B();
//                }
//                else {
//                    AUTONOMOUS_A();
//                }
//            }
//        }
//    }
//    public void testing(VisionPipeline myPipeline){
//        if(lowerruntime + 0.05 < getRuntime()){
//            CrLowerUpdate += -gamepad1.left_stick_y;
//            CbLowerUpdate += gamepad1.left_stick_x;
//            lowerruntime = getRuntime();
//        }
//        if(upperruntime + 0.05 < getRuntime()){
//            CrUpperUpdate += -gamepad1.right_stick_y;
//            CbUpperUpdate += gamepad1.right_stick_x;
//            upperruntime = getRuntime();
//        }
//
//        CrLowerUpdate = inValues(CrLowerUpdate, 0, 255);
//        CrUpperUpdate = inValues(CrUpperUpdate, 0, 255);
//        CbLowerUpdate = inValues(CbLowerUpdate, 0, 255);
//        CbUpperUpdate = inValues(CbUpperUpdate, 0, 255);
//
//        myPipeline.ConfigureScalarLower(0.0, CrLowerUpdate, CbLowerUpdate);
//        myPipeline.ConfigureScalarUpper(255.0, CrUpperUpdate, CbUpperUpdate);
//
//        telemetry.addData("lowerCr ", (int)CrLowerUpdate);
//        telemetry.addData("lowerCb ", (int)CbLowerUpdate);
//        telemetry.addData("UpperCr ", (int)CrUpperUpdate);
//        telemetry.addData("UpperCb ", (int)CbUpperUpdate);
//    }
//    public Double inValues(double value, double min, double max){
//        if(value < min){ value = min; }
//        if(value > max){ value = max; }
//        return value;
//    }
//    public void AUTONOMOUS_A(){
//        telemetry.addLine("Autonomous A");
//    }
//    public void AUTONOMOUS_B(){
//        telemetry.addLine("Autonomous B");
//    }
//    public void AUTONOMOUS_C(){
//        telemetry.addLine("Autonomous C");
//    }
//}