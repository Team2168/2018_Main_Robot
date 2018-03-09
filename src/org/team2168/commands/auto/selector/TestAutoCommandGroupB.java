package org.team2168.commands.auto.selector;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestAutoCommandGroupB extends CommandGroup {

    public TestAutoCommandGroupB() {
        addSequential(new TestCommandB());
    }
}
