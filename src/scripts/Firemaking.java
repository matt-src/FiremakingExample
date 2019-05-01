package scripts;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
@Script.Manifest(name = "test", description = "test script")

public class Firemaking extends PollingScript<ClientContext> {
    private List<Task> taskList = new ArrayList<>();

    @Override
    public void start(){
        taskList.addAll(Arrays.asList(new LightTask(ctx), new MoveOffTask(ctx)));
    }

    @Override
    public void poll() {
        for (Task task : taskList) {
            if (task.activate()) {
                System.out.println("Activating task " + task.toString());
                task.execute();
            }
        }
    }
}
