//Root Package
package frc.robot.commands;

//Libraries
import frc.robot.subsystems.SwerveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveCommand extends CommandBase
{
  //Suppress Warnings
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})


  //Instance Variables
  private final SwerveSubsystem Parent_Subsystem;


  //Constructors
  public DriveCommand(SwerveSubsystem Parent)
  {
    Parent_Subsystem = Parent;
    addRequirements(Parent_Subsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}

