//Root Package
package frc.robot;

//Libraries
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.AutonomousDriveCommand;
import frc.robot.subsystems.SwerveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

//Container Class
public class RobotContainer
{
  //Subsystems
  private SwerveSubsystem M_Drive;
  //Controllers
  private final XboxController M_Controller;
  //Buttons
  private JoystickButton Controller_A;
  private JoystickButton Controller_B;

  //Constructor
  public RobotContainer() 
  {
    //Subsystems
    M_Drive = new SwerveSubSystem();
    //Controllers
    M_Controller = new XboxController(0);
    //Buttons
    Controller_A = new JoystickButton(M_Controller, XboxController.Button.kA.value);
    Controller_B = new JoystickButton(M_Controller, XboxController.Button.kB.value); 
    //Set Defaults
    M_Drive.setDefaultCommand(new TeleoperatedDriveCommand(M_Drive,M_Controller.getLeftX,M_Controller.getLeftY,M_Controller.getRightX));
    //Configure Bindings
    configureButtonBindings();
  }
  //Config Bindings
  private void configureButtonBindings() 
  {
    //When A Pressed, Increment Rotational Face.
    Controller_A.whenPressed(new InstantCommand(() -> {M_Drive.IncrementRotationalFace();},M_Drive));
    //When B Pressed, Decrement Rotational Face.
    Controller_B.whenPressed(new InstantCommand(() -> {M_Drive.DecrementRotationalFace();},M_Drive));
  }
  //ACESSORS

  //Return Drive
  public SwerveSubsystem getDrive() {return M_Drive;}

  //Return Autonomous Command
  public Command getAutonomousCommand(){return new AutonomousDriveCommand(M_Drive);}
}