package scripts;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class MoveOffTask extends Task<ClientContext> {
    public MoveOffTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.objects.select().name("Fire").nearest().poll().tile().equals(ctx.players.local().tile());
    }

    @Override
    public void execute() {
        Tile targetTile = ctx.players.local().tile().derive(Random.nextInt(-1, 1), Random.nextInt(-1, 1));
        if(ctx.players.local().tile() != targetTile) {
            ctx.movement.step(targetTile);
            Condition.sleep(Random.nextInt(400, 1000));
        }
    }
}
