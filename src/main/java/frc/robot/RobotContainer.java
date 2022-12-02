//Root Package
package frc.robot;

//Local
import frc.robot.commands.AutonomousDriveCommand;
import frc.robot.commands.TeleoperatedDriveCommand;
import frc.robot.subsystems.SwerveSubsystem;
//Libraries
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton; 
import edu.wpi.first.wpilibj2.command.InstantCommand;
import com.ctre.phoenix.sensors.Pigeon2;
import java.lang.NullPointerException;
import java.util.Objects;
//Container Class
public class RobotContainer
{
  //INSTANCE VARIABLES
  //Subsystems
  private SwerveSubsystem M_Drive;
  //Controllers
  private XboxController M_Controller;
  //Buttons
  private JoystickButton Controller_A;
  private JoystickButton Controller_B;
  //Gyroscopes
  private Pigeon2 M_Gyro;

  //Constructor
  public RobotContainer() 
  {
      //Controllers
      for(int i = 0; i <= 20 && Objects.equals(M_Controller,null); i++)
      {
        try{M_Controller = new XboxController(i);}
        catch(NullPointerException x) {M_Controller = null; System.out.println("Error: XboxController Not Found"); System.exit(0);}
      }
      
      //Buttons
      try{Controller_A = new JoystickButton(M_Controller, XboxController.Button.kA.value);}
      catch(NullPointerException x) {System.out.println("Error: XboxController A Button Not Found"); System.exit(0);}
      try{Controller_B = new JoystickButton(M_Controller, XboxController.Button.kB.value);}
      catch(NullPointerException x) {System.out.println("Error: XboxController B Button Not Found"); System.exit(0);}
      //Gyroscopes
      try{M_Gyro = new Pigeon2(4);}
      catch(NullPointerException x) {System.out.println("Error: Gyroscope Not Found"); System.exit(0);}
      //Subsystems
      M_Drive = new SwerveSubsystem(M_Gyro);  
      //Set Default
      M_Drive.setDefaultCommand(new TeleoperatedDriveCommand(
      M_Drive,
      () -> M_Controller.getLeftX(),
      () -> M_Controller.getLeftY(),
      () -> M_Controller.getRightX(),
      M_Gyro));     
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
  public SwerveSubsystem getDrive(){return M_Drive;}
  //Return Autonomous Command
  public Command getAutonomousCommand(){return new AutonomousDriveCommand(M_Drive);}
  //Return Controller
  public XboxController getController(){return M_Controller;}
  //Return Gyroscope
  public Pigeon2 getGyro() {return M_Gyro;}
}