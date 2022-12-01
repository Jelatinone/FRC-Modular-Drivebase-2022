//Root Package
package frc.robot.commands;

//Libraries
import frc.robot.subsystems.SwerveSubsystem;
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
        //Autonomous Code
    }
    //End Command
    @Override
    public void end(boolean interrupted) {if(Objects.equals(interrupted,true)){Command_Complete = true;}}
    //Check Command Complete
    @Override
    public boolean isFinished() {return Command_Complete;}
}

