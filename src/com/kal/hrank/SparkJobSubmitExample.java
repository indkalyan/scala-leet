package com.kal.leet.com.kal.hrank;

import org.apache.spark.launcher.SparkAppHandle;
import org.apache.spark.launcher.SparkLauncher;
import org.apache.spark.launcher.SparkAppHandle.*;

public class SparkJobSubmitExample {
    public static void main(String[] args) throws Exception {
        // Configure Spark application
        SparkLauncher sparkLauncher = new SparkLauncher()
                .setAppName("SparkJobSubmitExample")
                .setMaster("spark://<spark-cluster-url>")
                .setAppResource("/path/to/spark/job.jar")
                .setMainClass("com.example.SparkJob")
                .addAppArgs("<app-arguments>")
                .setSparkHome("<spark-home>");

        // Submit Spark job
        SparkAppHandle handle = sparkLauncher.startApplication();

        // Monitor job status
        handle.addListener(new SparkAppHandle.Listener() {
            @Override
            public void stateChanged(SparkAppHandle handle) {
                System.out.println("Job state changed: " + handle.getState());
            }

            @Override
            public void infoChanged(SparkAppHandle handle) {
                // Handle info change if needed
            }
        });

        // Wait for job to finish
        handle.waitFor();

        // Retrieve job exit code and collect results if applicable
        int exitCode = handle.getState().ordinal();
        if (exitCode == State.FINISHED.ordinal()) {
            // Retrieve job results
            // ...
        } else {
            System.out.println("Job did not finish successfully. Exit code: " + exitCode);
        }
    }
}
