package cyano.basemetals.integration;

import com.google.common.collect.Lists;
import cyano.basemetals.util.AnnotationChecker;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.List;

public enum IntegrationManager {
    INSTANCE;

    private List<IIntegration> integrations = Lists.newArrayList();

    public void preInit(FMLPreInitializationEvent event) {
        AnnotationChecker.getInstances(event.getAsmData(), BaseMetalsPlugin.class, IIntegration.class).stream()
                .forEachOrdered(integration -> {
                    Class<? extends IIntegration> integrationClass = integration.getClass();
                    BaseMetalsPlugin plugin = integrationClass.getAnnotation(BaseMetalsPlugin.class);
                    if (Loader.isModLoaded(plugin.value())) {
                        integrations.add(integration);
                        integration.init();
                    }
                });
    }
}