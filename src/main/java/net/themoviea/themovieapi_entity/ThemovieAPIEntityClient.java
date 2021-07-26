package net.themoviea.themovieapi_entity;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.themoviea.themovieapi_entity.client.render.TestEntityModelRenderer;

public class ThemovieAPIEntityClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(ThemovieAPIEntity.TEST_ENTITY, (dispatcher, context) -> {
			return new TestEntityModelRenderer(dispatcher);
		});
	}
}
