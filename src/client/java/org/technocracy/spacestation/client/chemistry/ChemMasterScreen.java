package org.technocracy.spacestation.client.chemistry;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import org.technocracy.spacestation.chemistry.ChemData;
import org.technocracy.spacestation.chemistry.ChemMasterBlockEntity;
import org.technocracy.spacestation.chemistry.ChemMasterScreenHandler;
import org.technocracy.spacestation.chemistry.ModComponents;
import org.technocracy.spacestation.network.ModPackets;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChemMasterScreen extends HandledScreen<ChemMasterScreenHandler> {

    private static final int BG_WIDTH = 280;
    private static final int BG_HEIGHT = 310;

    // Left panel — grinding + container slots (side by side)
    private static final int SLOT_PANEL_LEFT = 6;
    private static final int SLOT_PANEL_TOP = 20;
    private static final int SLOT_PANEL_WIDTH = 90;
    private static final int SLOT_PANEL_HEIGHT = 90;
    private static final int GRIND_SLOT_X = 15;
    private static final int GRIND_SLOT_Y = 30;
    private static final int CONTAINER_SLOT_X = 55;
    private static final int CONTAINER_SLOT_Y = 30;

    // Right panels — scrollable chemical lists
    private static final int CHEM_PANEL_LEFT = 100;
    private static final int CHEM_PANEL_WIDTH = BG_WIDTH - CHEM_PANEL_LEFT - 6;
    private static final int MASTER_PANEL_TOP = 20;
    private static final int MASTER_LIST_TOP = 30;
    private static final int MASTER_LIST_HEIGHT = 90;
    private static final int CONTAINER_PANEL_TOP = 122;
    private static final int CONTAINER_LIST_TOP = 132;
    private static final int CONTAINER_LIST_HEIGHT = 90;

    private static final int ROW_HEIGHT = 18;
    private static final int MASTER_ROWS_VISIBLE = MASTER_LIST_HEIGHT / ROW_HEIGHT;
    private static final int CONTAINER_ROWS_VISIBLE = CONTAINER_LIST_HEIGHT / ROW_HEIGHT;

    private static final int PLAYER_INV_TOP = 226;

    private int masterScrollOffset = 0;
    private int containerScrollOffset = 0;

    private static final double[] AMOUNTS = {1, 5, 10, 25, 50, 100};
    private static final String[] LABELS = {"1", "5", "10", "25", "50", "All"};

    private List<Map.Entry<String, Double>> masterChems = new ArrayList<>();
    private List<Map.Entry<String, Double>> containerChems = new ArrayList<>();

    private String selectedMasterChem = null;
    private String selectedContainerChem = null;

    private float grindProgress = 0;

    public ChemMasterScreen(ChemMasterScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = BG_WIDTH;
        this.backgroundHeight = BG_HEIGHT;
    }

    @Override
    protected void init() {
        super.init();
        this.titleX = 8;
        this.titleY = 6;
        this.playerInventoryTitleX = 8;
        this.playerInventoryTitleY = PLAYER_INV_TOP - 12;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        updateChemLists();
        clampScrollOffsets();
        super.render(context, mouseX, mouseY, delta);
        renderGrindProgress(context);
        renderTransferButtons(context, mouseX, mouseY);
    }

    private void updateChemLists() {
        ChemMasterBlockEntity entity = handler.entity;
        if (entity == null) {
            masterChems = List.of();
            containerChems = List.of();
            return;
        }

        masterChems = new ArrayList<>(entity.getMasterChemicals().entrySet());
        masterChems.sort(Map.Entry.comparingByKey());

        var containerStack = entity.getContainerStack();
        ChemData data = containerStack.isEmpty() ? null : containerStack.get(ModComponents.CHEM_DATA);
        containerChems = data == null ? new ArrayList<>() : new ArrayList<>(data.chemicals().entrySet());
        containerChems.sort(Map.Entry.comparingByKey());
    }

    private void clampScrollOffsets() {
        masterScrollOffset = clampScroll(masterScrollOffset, masterChems.size(), MASTER_ROWS_VISIBLE);
        containerScrollOffset = clampScroll(containerScrollOffset, containerChems.size(), CONTAINER_ROWS_VISIBLE);
    }

    private static int clampScroll(int offset, int itemCount, int rowsVisible) {
        int maxOffset = Math.max(0, itemCount - rowsVisible);
        return Math.max(0, Math.min(offset, maxOffset));
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = this.x;
        int y = this.y;

        context.fill(x, y, x + BG_WIDTH, y + BG_HEIGHT, 0xFF2B2B2B);
        context.fill(x + 1, y + 1, x + BG_WIDTH - 1, y + BG_HEIGHT - 1, 0xFF3C3C3C);

        context.fill(x, y, x + BG_WIDTH, y + 16, 0xFF1A1A1A);
        context.drawHorizontalLine(x, x + BG_WIDTH, y + 16, 0xFF555555);

        // Left — grinding + flask slots
        context.fill(x + SLOT_PANEL_LEFT, y + SLOT_PANEL_TOP,
                x + SLOT_PANEL_LEFT + SLOT_PANEL_WIDTH, y + SLOT_PANEL_TOP + SLOT_PANEL_HEIGHT, 0xFF252525);
        context.drawBorder(x + SLOT_PANEL_LEFT, y + SLOT_PANEL_TOP, SLOT_PANEL_WIDTH, SLOT_PANEL_HEIGHT, 0xFF555555);

        context.fill(x + GRIND_SLOT_X, y + GRIND_SLOT_Y, x + GRIND_SLOT_X + 36, y + GRIND_SLOT_Y + 36, 0xFF1A1A1A);
        context.drawBorder(x + GRIND_SLOT_X, y + GRIND_SLOT_Y, 36, 36, 0xFF777777);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("gui.spacestation.chem_master.grinding"),
                x + GRIND_SLOT_X + 18, y + GRIND_SLOT_Y - 6, 0xAAAAAA);

        context.fill(x + CONTAINER_SLOT_X, y + CONTAINER_SLOT_Y,
                x + CONTAINER_SLOT_X + 36, y + CONTAINER_SLOT_Y + 36, 0xFF1A1A1A);
        context.drawBorder(x + CONTAINER_SLOT_X, y + CONTAINER_SLOT_Y, 36, 36, 0xFF777777);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("gui.spacestation.chem_master.flask"),
                x + CONTAINER_SLOT_X + 18, y + CONTAINER_SLOT_Y - 6, 0xAAAAAA);

        context.fill(x + GRIND_SLOT_X, y + 72, x + CONTAINER_SLOT_X + 36, y + 82, 0xFF1A1A1A);
        context.drawBorder(x + GRIND_SLOT_X, y + 72, CONTAINER_SLOT_X + 36 - GRIND_SLOT_X, 10, 0xFF555555);

        // Right — master storage list
        int masterPanelH = MASTER_LIST_TOP - MASTER_PANEL_TOP + MASTER_LIST_HEIGHT + 4;
        context.fill(x + CHEM_PANEL_LEFT, y + MASTER_PANEL_TOP,
                x + CHEM_PANEL_LEFT + CHEM_PANEL_WIDTH, y + MASTER_PANEL_TOP + masterPanelH, 0xFF252525);
        context.drawBorder(x + CHEM_PANEL_LEFT, y + MASTER_PANEL_TOP, CHEM_PANEL_WIDTH, masterPanelH, 0xFF555555);
        context.drawTextWithShadow(this.textRenderer, Text.translatable("gui.spacestation.chem_master.master_storage"),
                x + CHEM_PANEL_LEFT + 4, y + MASTER_PANEL_TOP + 4, 0xFFDDAA00);

        // Right — container contents list
        int containerPanelH = CONTAINER_LIST_TOP - CONTAINER_PANEL_TOP + CONTAINER_LIST_HEIGHT + 4;
        context.fill(x + CHEM_PANEL_LEFT, y + CONTAINER_PANEL_TOP,
                x + CHEM_PANEL_LEFT + CHEM_PANEL_WIDTH, y + CONTAINER_PANEL_TOP + containerPanelH, 0xFF252525);
        context.drawBorder(x + CHEM_PANEL_LEFT, y + CONTAINER_PANEL_TOP, CHEM_PANEL_WIDTH, containerPanelH, 0xFF555555);
        context.drawTextWithShadow(this.textRenderer, Text.translatable("gui.spacestation.chem_master.container"),
                x + CHEM_PANEL_LEFT + 4, y + CONTAINER_PANEL_TOP + 4, 0xFF55AADD);

        var containerStack = handler.entity != null ? handler.entity.getContainerStack() : null;
        if (containerStack != null && !containerStack.isEmpty()) {
            ChemData data = containerStack.get(ModComponents.CHEM_DATA);
            if (data != null) {
                String cap = String.format("%.1f/%.0f u", data.totalVolume(), data.capacity());
                context.drawTextWithShadow(this.textRenderer, Text.literal(cap),
                        x + CHEM_PANEL_LEFT + CHEM_PANEL_WIDTH - 4 - this.textRenderer.getWidth(cap),
                        y + CONTAINER_PANEL_TOP + 4, 0xFF888888);
            }
        }

        context.drawHorizontalLine(x, x + BG_WIDTH, y + PLAYER_INV_TOP - 2, 0xFF555555);

        renderChemList(context, mouseX, mouseY,
                x + CHEM_PANEL_LEFT + 1, y + MASTER_LIST_TOP,
                CHEM_PANEL_WIDTH - 10, MASTER_LIST_HEIGHT,
                masterChems, masterScrollOffset, MASTER_ROWS_VISIBLE,
                selectedMasterChem, 0xFF4A6FA5);

        renderChemList(context, mouseX, mouseY,
                x + CHEM_PANEL_LEFT + 1, y + CONTAINER_LIST_TOP,
                CHEM_PANEL_WIDTH - 10, CONTAINER_LIST_HEIGHT,
                containerChems, containerScrollOffset, CONTAINER_ROWS_VISIBLE,
                selectedContainerChem, 0xFF4A8A6F);

        renderScrollBar(context, x + CHEM_PANEL_LEFT + CHEM_PANEL_WIDTH - 7, y + MASTER_LIST_TOP,
                MASTER_LIST_HEIGHT, masterChems.size(), masterScrollOffset, MASTER_ROWS_VISIBLE);
        renderScrollBar(context, x + CHEM_PANEL_LEFT + CHEM_PANEL_WIDTH - 7, y + CONTAINER_LIST_TOP,
                CONTAINER_LIST_HEIGHT, containerChems.size(), containerScrollOffset, CONTAINER_ROWS_VISIBLE);

        // Since we draw our own GUI background, we also need to draw the "slot backplates"
        // for the player inventory area (otherwise items look like they are floating on top).
        renderPlayerInventorySlotBackplates(context);
    }

    private void renderPlayerInventorySlotBackplates(DrawContext context) {
        int x = this.x;
        int y = this.y;

        // Main inventory (3 rows)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                // Vanilla slot items render at (slotX + 1, slotY + 1); shift the backplate
                // slightly up-left so the item appears centered.
                int sx = x + 8 + col * 18 - 1;
                int sy = y + PLAYER_INV_TOP + row * 18 - 1;
                context.fill(sx, sy, sx + 18, sy + 18, 0xFF1A1A1A);
                context.drawBorder(sx, sy, 18, 18, 0xFF555555);
            }
        }

        // Hotbar (1 row)
        int hotbarY = y + PLAYER_INV_TOP + 58 - 1;
        for (int col = 0; col < 9; col++) {
            int sx = x + 8 + col * 18 - 1;
            context.fill(sx, hotbarY, sx + 18, hotbarY + 18, 0xFF1A1A1A);
            context.drawBorder(sx, hotbarY, 18, 18, 0xFF555555);
        }
    }

    private void renderChemList(DrawContext context, int mouseX, int mouseY,
                                int panelX, int panelY, int panelW, int panelH,
                                List<Map.Entry<String, Double>> chems, int scrollOffset, int rowsVisible,
                                String selectedChem, int selectedColor) {
        context.enableScissor(panelX, panelY, panelX + panelW, panelY + panelH);

        for (int i = 0; i < rowsVisible; i++) {
            int idx = i + scrollOffset;
            if (idx >= chems.size()) {
                break;
            }

            Map.Entry<String, Double> entry = chems.get(idx);
            int rowY = panelY + i * ROW_HEIGHT;
            boolean hovered = mouseX >= panelX && mouseX < panelX + panelW
                    && mouseY >= rowY && mouseY < rowY + ROW_HEIGHT;
            boolean selected = entry.getKey().equals(selectedChem);

            if (selected) {
                context.fill(panelX, rowY, panelX + panelW, rowY + ROW_HEIGHT - 1, selectedColor);
            } else if (hovered) {
                context.fill(panelX, rowY, panelX + panelW, rowY + ROW_HEIGHT - 1, 0xFF404040);
            }

            context.fill(panelX + 2, rowY + 4, panelX + 8, rowY + ROW_HEIGHT - 4, ChemColors.get(entry.getKey()));

            context.drawTextWithShadow(this.textRenderer,
                    Text.translatableWithFallback("chem.spacestation." + entry.getKey(), entry.getKey()),
                    panelX + 12, rowY + 5, 0xFFFFFF);

            String amountStr = String.format("%.2f u", entry.getValue());
            context.drawTextWithShadow(this.textRenderer, amountStr,
                    panelX + panelW - this.textRenderer.getWidth(amountStr) - 2, rowY + 5, 0xFFDDAA00);
        }

        context.disableScissor();
    }

    private void renderGrindProgress(DrawContext context) {
        if (handler.entity != null && !handler.entity.slots.get(0).isEmpty()) {
            grindProgress = (grindProgress + 0.02f) % 1.0f;
        } else {
            grindProgress = 0;
        }

        int barX = x + GRIND_SLOT_X + 1;
        int barY = y + 73;
        int barMaxW = CONTAINER_SLOT_X + 36 - GRIND_SLOT_X - 2;
        int barW = (int) (barMaxW * grindProgress);

        context.fill(barX, barY, barX + barW, barY + 8, 0xFF44AA44);
        if (barW > 0) {
            context.fill(barX + barW - 2, barY, barX + barW, barY + 8, 0xFF88FF88);
        }
    }

    private void renderTransferButtons(DrawContext context, int mouseX, int mouseY) {
        if (selectedMasterChem == null && selectedContainerChem == null) {
            return;
        }

        int btnY = y + 88;
        int btnX = x + 8;

        String label = selectedMasterChem != null
                ? "-> " + selectedMasterChem
                : "<- " + selectedContainerChem;
        context.drawTextWithShadow(this.textRenderer, label, btnX, btnY - 10, 0xFFFFFF);

        for (int i = 0; i < LABELS.length; i++) {
            int bx = btnX + i * 14;
            boolean hovered = mouseX >= bx && mouseX < bx + 13
                    && mouseY >= btnY && mouseY < btnY + 12;

            context.fill(bx, btnY, bx + 13, btnY + 12, hovered ? 0xFF5A8FC0 : 0xFF3A5F80);
            context.drawBorder(bx, btnY, 13, 12, 0xFF7AAAE0);
            context.drawCenteredTextWithShadow(this.textRenderer, LABELS[i], bx + 6, btnY + 2, 0xFFFFFF);
        }
    }

    private void renderScrollBar(DrawContext context, int barX, int barY, int height,
                                 int totalItems, int offset, int rowsVisible) {
        if (totalItems <= rowsVisible) {
            return;
        }

        context.fill(barX, barY, barX + 4, barY + height, 0xFF1A1A1A);

        float ratio = (float) rowsVisible / totalItems;
        int thumbH = Math.max(10, (int) (height * ratio));
        int maxOffset = totalItems - rowsVisible;
        int thumbY = barY + (maxOffset == 0 ? 0 : (int) ((height - thumbH) * ((float) offset / maxOffset)));

        context.fill(barX, thumbY, barX + 4, thumbY + thumbH, 0xFF888888);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int panelX = x + CHEM_PANEL_LEFT + 1;
        int panelW = CHEM_PANEL_WIDTH - 10;

        if (trySelectChem(mouseX, mouseY, panelX, y + MASTER_LIST_TOP, panelW, MASTER_LIST_HEIGHT,
                masterChems, masterScrollOffset, MASTER_ROWS_VISIBLE, true)) {
            return true;
        }

        if (trySelectChem(mouseX, mouseY, panelX, y + CONTAINER_LIST_TOP, panelW, CONTAINER_LIST_HEIGHT,
                containerChems, containerScrollOffset, CONTAINER_ROWS_VISIBLE, false)) {
            return true;
        }

        if (selectedMasterChem != null || selectedContainerChem != null) {
            int btnY = y + 88;
            int btnX = x + 8;
            for (int i = 0; i < LABELS.length; i++) {
                int bx = btnX + i * 14;
                if (mouseX >= bx && mouseX < bx + 13 && mouseY >= btnY && mouseY < btnY + 12) {
                    sendTransfer(AMOUNTS[i]);
                    return true;
                }
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    private boolean trySelectChem(double mouseX, double mouseY, int panelX, int panelY, int panelW, int panelH,
                                  List<Map.Entry<String, Double>> chems, int scrollOffset, int rowsVisible,
                                  boolean master) {
        if (mouseX < panelX || mouseX >= panelX + panelW || mouseY < panelY || mouseY >= panelY + panelH) {
            return false;
        }

        int row = (int) ((mouseY - panelY) / ROW_HEIGHT);
        if (row < 0 || row >= rowsVisible) {
            return false;
        }

        int idx = row + scrollOffset;
        if (idx >= chems.size()) {
            return false;
        }

        String chem = chems.get(idx).getKey();
        if (master) {
            selectedMasterChem = chem;
            selectedContainerChem = null;
        } else {
            selectedContainerChem = chem;
            selectedMasterChem = null;
        }
        return true;
    }

    private void sendTransfer(double amount) {
        if (handler.entity == null) {
            return;
        }

        if (selectedMasterChem != null) {
            if (amount >= AMOUNTS[AMOUNTS.length - 1]) {
                amount = handler.entity.getMasterChemicals().getOrDefault(selectedMasterChem, 0.0);
            }
            ClientPlayNetworking.send(new ModPackets.ChemMovePayload(selectedMasterChem, amount, true));
        } else if (selectedContainerChem != null) {
            if (amount >= AMOUNTS[AMOUNTS.length - 1]) {
                var data = handler.entity.getContainerStack().get(ModComponents.CHEM_DATA);
                amount = data != null ? data.chemicals().getOrDefault(selectedContainerChem, 0.0) : 0;
            }
            ClientPlayNetworking.send(new ModPackets.ChemMovePayload(selectedContainerChem, amount, false));
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        int scrollDelta = verticalAmount > 0 ? -1 : verticalAmount < 0 ? 1 : 0;
        if (scrollDelta == 0) {
            return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
        }

        int listX = x + CHEM_PANEL_LEFT;
        int listW = CHEM_PANEL_WIDTH;

        if (mouseX >= listX && mouseX < listX + listW
                && mouseY >= y + MASTER_LIST_TOP && mouseY < y + MASTER_LIST_TOP + MASTER_LIST_HEIGHT) {
            masterScrollOffset = clampScroll(masterScrollOffset + scrollDelta, masterChems.size(), MASTER_ROWS_VISIBLE);
            return true;
        }

        if (mouseX >= listX && mouseX < listX + listW
                && mouseY >= y + CONTAINER_LIST_TOP && mouseY < y + CONTAINER_LIST_TOP + CONTAINER_LIST_HEIGHT) {
            containerScrollOffset = clampScroll(containerScrollOffset + scrollDelta, containerChems.size(), CONTAINER_ROWS_VISIBLE);
            return true;
        }

        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }
}
