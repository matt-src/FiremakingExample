package scripts;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.Players;

public class LightTask extends Task<ClientContext> {
    public LightTask(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        if(ctx.inventory.select().name("Logs").isEmpty()) return false;
        if(ctx.players.local().inMotion()) return false;
        if(ctx.objects.select().name("Fire").nearest().poll().tile().equals(ctx.players.local().tile())){
            System.out.println("Returned false because we're on top of a fire");
            return false;
        }
        if(ctx.players.local().animation() > -1) return false;

        Tile leftTile = ctx.players.local().tile().derive(-1, 0);
        return ctx.movement.reachable(ctx.players.local(), leftTile);
    }

    @Override
    public void execute() {
        if(ctx.inventory.selectedItemIndex() > -1){
            ctx.movement.step(ctx.players.local().tile());
        }
        if(ctx.inventory.select().name("Tinderbox").poll().interact("Use")){
            if(ctx.inventory.selectedItem().name().equals("Tinderbox")){
                Tile lastTile = ctx.players.local().tile();
                if(ctx.inventory.select().name("Logs").poll().interact("Use")) {
                    Condition.wait(() -> !ctx.players.local().tile().equals(lastTile), 250, 40);
                }
            }
        }

    }
}
