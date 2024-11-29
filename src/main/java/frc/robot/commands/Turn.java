package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.DriveTrain;

public class Turn extends Command {
    DriveTrain drive;
    double startAngle = 0.0;
    double requestAngle = 0;
    boolean finished = false;
    int counts = 0;

    public Turn(DriveTrain drive, double angle) {
        this.drive = drive;
        this.requestAngle = angle;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        startAngle = drive.getAngle();
        finished = false;
        counts = 0;
    }
    
    @Override
    public void execute() {
        drive.ArcadeDrive(0, Math.signum(requestAngle) * 0.6);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(startAngle - drive.getAngle()) > Math.abs(requestAngle);
    }

    @Override
    public void end(boolean interrupted) {
        drive.ArcadeDrive(0, 0);
    }
}