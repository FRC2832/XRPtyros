package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.DriveTrain;

public class DriveDistance extends Command {
    DriveTrain drive;
    double startLeftDist = 0.0;
    double startRightDist = 0.0;
    double distance = 0;

    public DriveDistance(DriveTrain drive, double inches) {
        this.drive = drive;
        this.distance = inches;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        startLeftDist = drive.getLeftWheelDistance();
        startRightDist = drive.getRightWheelDistance();
    }
    
    @Override
    public void execute() {
        //calculate wheel difference
        var diff = (drive.getLeftWheelDistance() - startLeftDist) - (drive.getRightWheelDistance() - startRightDist);
        diff = diff * 0.17;
        var power = 0.6 * Math.signum(distance);
        drive.TankDrive(power, power + diff);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(drive.getLeftWheelDistance() - startLeftDist) > Math.abs(distance);
    }

    @Override
    public void end(boolean interrupted) {
        drive.ArcadeDrive(0, 0);
    }
}