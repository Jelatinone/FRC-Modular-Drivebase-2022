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
    private final SwerveSubsystem Parent_Subsystem;

    //Primitives
    private boolean Command_Complete;

    //Constructors
    public AutonomousDriveCommand(SwerveSubsystem Parent)
    {
        //Define Instances
        Parent_Subsystem = Parent;
        Command_Complete = false;
        //Add Command To Subsystem
        addRequirements(Parent_Subsystem);
    }

    //Initialize Command
    @Override
    public void initialize() {}

    //Execute Command
    @Override
    public void execute() {}

    //End Command
    @Override
    public void end(boolean interrupted) {if(Objects.equals(interrupted,true)){Command_Complete = true;}}

    //Check Command Complete
    @Override
    public boolean isFinished() {return Command_Complete;}
}

