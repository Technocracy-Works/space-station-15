package org.technocracy.spacestation.registry.components;

public record ToolQuality(String name) {
    public static final ToolQuality SCREWING = new ToolQuality("Screwing");
    public static final ToolQuality WELDING = new ToolQuality("Welding");
    public static final ToolQuality IGNITION = new ToolQuality("Ignition");
    public static final ToolQuality PRYING = new ToolQuality("Prying");
    public static final ToolQuality[] ALL = { SCREWING, WELDING, IGNITION, PRYING };
}
