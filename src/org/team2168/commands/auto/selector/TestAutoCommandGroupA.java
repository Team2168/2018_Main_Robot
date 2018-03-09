package org.team2168.commands.auto.selector;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestAutoCommandGroupA extends CommandGroup {

    public TestAutoCommandGroupA() {
        addSequential(new TestCommand());
    }
}
