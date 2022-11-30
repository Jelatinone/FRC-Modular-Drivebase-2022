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

  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  //Initialize Robot
  @Override
  public void robotInit() {

    m_robotContainer = new RobotContainer();
  }

  //Periodically Called
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  //Intialize Disabled Mode
  @Override
  public void disabledInit() {}

  //Periodically Called Disabled
  @Override
  public void disabledPeriodic() {}

  //Initialize Autonomous
  @Override
  public void autonomousInit() 
  {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    if (m_autonomousCommand != null) 
    {
      m_autonomousCommand.schedule();
    }
  }

  //Periodically Called Autonomous
  @Override
  public void autonomousPeriodic() {}

  //Initialize Teleoperated
  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  //Periodically Called Teleoperated
  @Override
  public void teleopPeriodic() {}

  //Initialize Test
  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

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
