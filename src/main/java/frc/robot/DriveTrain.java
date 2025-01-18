package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.xrp.XRPGyro;
import edu.wpi.first.wpilibj.xrp.XRPMotor;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase{
    DifferentialDrive drive;
    XRPMotor leftDrive;
    XRPMotor rightDrive;
    AnalogInput leftLine;
    AnalogInput rightLine;
    AnalogInput distance;
    AnalogInput batteryVoltage;
    XRPGyro gyro;
    Encoder leftDistance, rightDistance;

    public DriveTrain() {
        super();
        rightDrive = new XRPMotor(1);
        rightDrive.setInverted(true);
        drive = new DifferentialDrive(leftDrive, rightDrive);
        
        leftLine = new AnalogInput(0);
        rightLine = new AnalogInput(1);
        distance = new AnalogInput(2);
        batteryVoltage = new AnalogInput(3);

        leftDistance = new Encoder(4, 5);
        //7.5" per revolution, which has 585 pulses
        leftDistance.setDistancePerPulse(7.5/585);
        rightDistance = new Encoder(6, 7);
        //7.5" per revolution, which has 585 pulses
        rightDistance.setDistancePerPulse(7.5/585);

        gyro = new XRPGyro();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Left Line", leftLine.getVoltage());
        SmartDashboard.putNumber("Right Line", rightLine.getVoltage());
        SmartDashboard.putNumber("Distance", distance.getVoltage());
        SmartDashboard.putNumber("Battery Voltage", batteryVoltage.getVoltage());
        SmartDashboard.putNumber("Left Wheel Distance", leftDistance.getDistance());
        SmartDashboard.putNumber("Right Wheel Distance", rightDistance.getDistance());
        SmartDashboard.putNumber("Gyro Yaw X", gyro.getAngleX());
        SmartDashboard.putNumber("Gyro Yaw Y", gyro.getAngleY());
        SmartDashboard.putNumber("Gyro Yaw Z", gyro.getAngleZ());

    }

    public void ArcadeDrive(double speed, double turn) {
        drive.arcadeDrive(speed, turn);
    }

    public void TankDrive(double left, double right) {
        drive.tankDrive(left, right);
    }

    public Command controllerDrive(DoubleSupplier speed, DoubleSupplier turn) {
        return run(() -> {
            ArcadeDrive(-speed.getAsDouble(), -turn.getAsDouble());
        });
    }

    int countsSeen = 0;
    public Command lineFollow() {
        Preferences.initDouble("PIDF", 0.1);
        try (PIDController pid = new PIDController(0.4, 0.0, 0)) {
            return run(() -> {
                if (leftLine.getVoltage() > 4 && rightLine.getVoltage() > 4) {
                    countsSeen++;
                } else {
                    countsSeen = 0;
                }
                var f = Preferences.getDouble("PIDF", 0.1);
                double diff = leftLine.getVoltage() - rightLine.getVoltage();
                double turn = pid.calculate(diff);
                ArcadeDrive(0.55, -turn);
            }).until(() -> countsSeen > 4)
            .finallyDo(() -> drive.arcadeDrive(0, 0));
        }
    }


    int counts = 0;
    public Command turnToLine() {
        return //runOnce(() -> {counts = 0;})
            run(() -> {
            ArcadeDrive(0., 0.6);
            if(leftLine.getVoltage() > 4.35) {
                counts += 1;
            }
        }).until(() -> rightLine.getVoltage() > 4.35);
    }


    public double distanceSensor() {
        return distance.getVoltage();
    }

    public double getAngle() {
        return gyro.getAngle();
    }

    public double getLeftWheelDistance() {
        return leftDistance.getDistance();
    }

    public double getRightWheelDistance() {
        return rightDistance.getDistance();
    }
}
