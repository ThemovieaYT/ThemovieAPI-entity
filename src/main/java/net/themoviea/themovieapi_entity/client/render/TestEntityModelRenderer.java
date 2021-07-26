package net.themoviea.themovieapi_entity.client.render;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.themoviea.themovieapi_entity.entity.TestEntity;

public class TestEntityModelRenderer extends MobEntityRenderer<TestEntity, TestEntityModel> {

	public TestEntityModelRenderer(EntityRenderDispatcher entityRenderDispatcher) {
		super(entityRenderDispatcher, new TestEntityModel(), 0.5f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Identifier getTexture(TestEntity entity) {
		return new Identifier("themovieapientity", "textures/entity/testentitymodel.png");
	}
}
