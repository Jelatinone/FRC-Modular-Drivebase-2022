//Root Package
package frc.robot;

//Libraries
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

//Robot Class
public class Robot extends TimedRobot 
{
  //Instance Variables
  private Command M_AutoCommand;
  private RobotContainer M_robotContainer;

  //Initialize Robot
  @Override
  public void robotInit() {M_robotContainer = new RobotContainer();}

  //Periodically Called
  @Override
  public void robotPeriodic() {CommandScheduler.getInstance().run();}

  //Intialize Disabled Mode
  @Override
  public void disabledInit() {CommandScheduler.getInstance().cancelAll();}

  //Periodically Called Disabled
  @Override
  public void disabledPeriodic() {CommandScheduler.getInstance().cancelAll();}

  //Initialize Autonomous
  @Override
  public void autonomousInit() 
  {
    M_AutoCommand = M_robotContainer.getAutonomousCommand();
    if (M_AutoCommand != null) {M_AutoCommand.schedule();}
  }

  //Periodically Called Autonomous
  @Override
  public void autonomousPeriodic() 
  {
    M_AutoCommand = M_robotContainer.getAutonomousCommand();
    if (M_AutoCommand != null) {M_AutoCommand.schedule();}
  }

  //Initialize Teleoperated
  @Override
  public void teleopInit() 
  {
    if (M_AutoCommand != null) {M_AutoCommand.cancel();}
  }

  //Periodically Called Teleoperated
  @Override
  public void teleopPeriodic() {}

  //Initialize Test
  @Override
  public void testInit() {CommandScheduler.getInstance().cancelAll();}

  //Periodically Called Test
  @Override
  public void testPeriodic() {}

  //Initialize Simulation
  @Override
  public void simulationInit() {}

  //Periodically Called Simulation
  @Override
  public void simulationPeriodic() {}
}
