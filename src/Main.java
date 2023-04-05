import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.highgui.HighGui;

public class Main {
    public static void main(String[] args) {
        // Load OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Create video capture object
        VideoCapture capture = new VideoCapture(0);

        // Create face detection classifier object
        CascadeClassifier faceDetector = new CascadeClassifier();
        faceDetector.load("haarcascade_frontalface_default.xml");

        // Loop through frames from camera
        Mat frame = new Mat();
        MatOfRect faceDetections = new MatOfRect();
        while (capture.read(frame)) {
            // Perform face detection on current frame
            faceDetector.detectMultiScale(frame, faceDetections);

            // Draw rectangles around detected faces
            for (Rect rect : faceDetections.toArray()) {
                Imgproc.rectangle(frame, rect.tl(), rect.br(), new Scalar(0, 255, 0), 3);
            }

            // Display frame with face detections
            HighGui.imshow("Face Detection", frame);

            // Exit loop if user presses "q" key
            if (HighGui.waitKey(1) == 'q') {
                break;
            }
        }

        // Release video capture object and close display window
        capture.release();
        HighGui.destroyAllWindows();

    }
}
