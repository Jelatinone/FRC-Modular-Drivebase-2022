//Root Package
package frc.robot;

//Libraries
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.SwerveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer
{
  //Instance Variables

  //Subsytem
  private final SwerveSubsystem M_Drive = new SwerveSubsystem();
  private final DriveCommand M_Drive_Command = new DriveCommand(M_Drive);

  //Constructor
  public RobotContainer() 
  {
    configureButtonBindings();
  }

  //Config Bindings
  private void configureButtonBindings() {}

  //Return the Subsystem
  public SwerveSubsystem getDrive() 
  {
    return M_Drive;
  }
}