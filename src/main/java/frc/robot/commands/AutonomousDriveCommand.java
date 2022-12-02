//Root Package
package frc.robot.commands;

//Local
import frc.robot.subsystems.SwerveSubsystem;

//Libraries
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.Objects;

public class AutonomousDriveCommand extends CommandBase
{
    //Suppress Warnings
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    //Instance Variables
    private boolean Command_Complete = false;
    private final SwerveSubsystem Parent_Subsystem;

    //Constructors
    public AutonomousDriveCommand(SwerveSubsystem Parent)
    {
        //Define Instances
        //Parent Subsystem
        Parent_Subsystem = Parent;
        //Add Command To Parent Subsystem
        addRequirements(Parent_Subsystem);
    }
    //Initialize Command
    @Override
    public void initialize() {}
    //Execute Command
    @Override
    public void execute() 
    {
        //Perform Circles
        //K-Motors
        (Parent_Subsystem.getKRotate()[0]).set(1);
        (Parent_Subsystem.getKDrive()[0]).set(0.5);
        //N-Motors
        (Parent_Subsystem.getNDrives()[2]).setInverted(true);
        (Parent_Subsystem.getNDrives()[2]).set(1);
        (Parent_Subsystem.getNRotates()[2]).setInverted(true);
        (Parent_Subsystem.getNRotates()[2]).set(0.5);
        Command_Complete = true;
    }
    //End Command
    @Override
    public void end(boolean interrupted) {if(Objects.equals(interrupted,true)){Command_Complete = true;}}
    //Check Command Complete
    @Override
    public boolean isFinished() {return Command_Complete;}
}

